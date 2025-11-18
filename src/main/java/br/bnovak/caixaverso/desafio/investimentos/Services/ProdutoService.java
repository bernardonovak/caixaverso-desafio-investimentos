package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Mappers.ProdutoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Dto.ProdutoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.PerfilRisco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

//    @Inject
//    SimulacaoService simulacaoService;

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

    public ProdutoResponse buscarProdutoAdequado(TipoProduto tipoProduto, PerfilRisco perfilRisco) throws NaoEncontradoException{
        Produto produto = obterProdutoValidado(tipoProduto, perfilRisco);
        return mapper.toDTO(produto);
    }

    public List<ProdutoResponse> buscarProdutosRecomendados(String strPerfil) throws NaoEncontradoException{
        //Procura perfil pelo nome
        PerfilRisco perfilRisco = PerfilRisco.buscarPorPerfil(strPerfil);

        //SimulacaoResponse simulacoes = simulacaoService.buscarTodos().stream().filter(simulacao -> simulacao.getProduto().equalsIgnoreCase(perfilRisco.getRisco()));
        //lista produtos por perfil
        return null;
    }

    private Produto obterProdutoPorID(Integer idProduto) throws NaoEncontradoException{
        return produtoRepository.findByIdOptional(Long.valueOf(idProduto)).orElseThrow(()-> new NaoEncontradoException("Produto não encontrado"));
    }

    private Produto obterProdutoValidado(TipoProduto tipoProduto, PerfilRisco perfilRisco) throws NaoEncontradoException{
        return produtoRepository.buscarPorTipoERisco(tipoProduto, perfilRisco).orElseThrow(()-> new NaoEncontradoException("Não foi possível encontrar um produto adequado de acordo com o perfil de risco do CLiente e tipo de investimento informado."));
    }

}
