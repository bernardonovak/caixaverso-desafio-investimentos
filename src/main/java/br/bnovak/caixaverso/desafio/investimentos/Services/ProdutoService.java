package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Mappers.ProdutoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Dto.ProdutoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
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

    public ProdutoResponse buscarProdutoAdequado(TipoProduto tipoProduto, Risco risco) throws NaoEncontradoException{
        Produto produto = obterProdutoValidado(tipoProduto, risco);
        return mapper.toDTO(produto);
    }

    private Produto obterProdutoPorID(Integer idProduto) throws NaoEncontradoException{
        return produtoRepository.findByIdOptional(Long.valueOf(idProduto)).orElseThrow(()-> new NaoEncontradoException("Produto não encontrado"));
    }

    private Produto obterProdutoValidado(TipoProduto tipoProduto, Risco risco) throws NaoEncontradoException{
        return produtoRepository.buscarPorTipoERisco(tipoProduto, risco).orElseThrow(()-> new NaoEncontradoException("Não foi possível encontrar um produto adequado de acordo com o perfil de risco do CLiente e tipo de investimento informado."));
    }

}
