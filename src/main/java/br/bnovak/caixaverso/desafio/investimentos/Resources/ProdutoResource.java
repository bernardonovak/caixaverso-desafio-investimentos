package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.ProdutoAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ProdutoResource implements ProdutoAPI {

    @Inject
    ProdutoService service;

    @Override
    public Response buscarTodos() {
        return ApiResponse.ok(service.buscarTodos());
    }

    @Override
    public Response buscarUm(Integer idProduto) throws NaoEncontradoException {
        return ApiResponse.ok(service.buscarPorID(idProduto));
    }
}
