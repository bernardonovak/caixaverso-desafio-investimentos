package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.ProdutosRecomendadosAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class ProdutosRecomendadosResource implements ProdutosRecomendadosAPI {

    @Inject
    ProdutoService service;

    @Override
    public Response buscarUm(String perfil) throws NaoEncontradoException {
        return ApiResponse.ok(service.buscarProdutosRecomendados(perfil));
    }
}
