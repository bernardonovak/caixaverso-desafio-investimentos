package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.ProdutoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Dto.ProdutoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ProdutoMapper mapper;

    public List<ProdutoResponse> buscarTodos(){
        List<Produto> produtos = produtoRepository.findAll().list();
        return mapper.toListDTO(produtos);
    }

    public ProdutoResponse buscarPorID(Integer idProduto) throws NaoEncontradoException {
        Produto produto = obterProdutoPorID(idProduto);
        return mapper.toDTO(produto);
    }

    public Risco calcularRiscoPorPerfil(Perfil perfil) throws NaoEncontradoException {
        if(perfil == null){
            throw new NaoEncontradoException("Perfil Inválido!");
        }
        switch (perfil){
            case Perfil.CONSERVADOR -> {
                return Risco.BAIXO;
            }
            case Perfil.MODERADO -> {
                return Risco.MEDIO;
            }
            case Perfil.AGRESSIVO -> {
                return Risco.ALTO;
            }
            default -> {
                throw new NaoEncontradoException("Perfil Inválido!");
            }
        }
    }

    public ProdutoResponse buscarProdutoAdequado(TipoProduto tipoProduto, Risco risco) throws NaoEncontradoException{
        Produto produto = obterProdutoValidado(tipoProduto, risco);
        return mapper.toDTO(produto);
    }

    public List<ProdutoResponse> recomendarProdutos(String strPerfil) throws NaoEncontradoException{
        Perfil perfil = Perfil.buscarPorPerfil(strPerfil);
        List<ProdutoResponse> produtos = buscarTodos();
        if(produtos.isEmpty()){
            throw new NaoEncontradoException("Nenhum produto recomendado no momento para o perfil.");
        }

        Comparator<ProdutoResponse> porRentabilidadeDesc = Comparator.comparing(ProdutoResponse::getRentabilidade).reversed();
        switch (perfil){
            case Perfil.CONSERVADOR -> {
                //buscar por Liquidez Alta e baixa movimentação
                return recomendarProdutosPerfilConservador(produtos, porRentabilidadeDesc);
            }
            case Perfil.MODERADO -> {
                //buscar equilibrio entre liquidez e rentabilidade
                return recomendarProdutosPerfilModerado(produtos, porRentabilidadeDesc);
            }
            case Perfil.AGRESSIVO -> {
                //busca por alta rentabilidade, maior risco
                return recomendarProdutosPerfilAgressivo(produtos, porRentabilidadeDesc);
            }
            default -> throw new NaoEncontradoException("Erro ao encontrar Risco.");
        }
    }

    private List<ProdutoResponse> recomendarProdutosPerfilConservador(List<ProdutoResponse> produtos, Comparator<ProdutoResponse> ordem){
        return produtos.stream()
                .filter(p -> p.getRentabilidade().compareTo(Perfil.CONSERVADOR.getParametroRentabilidade()) <= 0)
                .filter(p -> Risco.buscarPorNome(p.getRisco()) == Risco.BAIXO)
                .sorted(ordem)
                .toList();
    }

    private List<ProdutoResponse> recomendarProdutosPerfilModerado(List<ProdutoResponse> produtos, Comparator<ProdutoResponse> ordem){
        return produtos.stream()
                .filter(p -> p.getRentabilidade().compareTo(Perfil.MODERADO.getParametroRentabilidade()) <= 0)
                .filter(p -> Risco.buscarPorNome(p.getRisco()) != Risco.ALTO)
                .sorted(ordem)
                .toList();
    }

    private List<ProdutoResponse> recomendarProdutosPerfilAgressivo(List<ProdutoResponse> produtos, Comparator<ProdutoResponse> ordem){
        return produtos.stream()
                .filter(p -> p.getRentabilidade().compareTo(Perfil.AGRESSIVO.getParametroRentabilidade()) >= 0)
                .sorted(ordem)
                .toList();
    }

    private Produto obterProdutoPorID(Integer idProduto) throws NaoEncontradoException{
        return produtoRepository.findByIdOptional(Long.valueOf(idProduto)).orElseThrow(()-> new NaoEncontradoException("Produto não encontrado"));
    }

    private Produto obterProdutoValidado(TipoProduto tipoProduto, Risco risco) throws NaoEncontradoException{
        List<Produto> produtos = produtoRepository.buscarPorTipoERisco(tipoProduto, risco);
        return produtos.stream()
                .max(Comparator.comparing(Produto::getRentabilidade))
                .orElseThrow(() -> new NaoEncontradoException("Não foi possível encontrar um produto disponível de acordo com o perfil do CLiente e tipo de investimento informado."));
    }

}
