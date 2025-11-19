package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.PerfilRiscoAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.ClienteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PerfilRiscoResource implements PerfilRiscoAPI {

    @Inject
    ClienteService service;

    @Override
    public Response buscarPerfilCliente(Integer clienteId) throws NaoEncontradoException {
        return ApiResponse.ok(service.buscarPorID(clienteId));
    }

    @Override
    public Response buscarTodos() {
        return ApiResponse.ok(service.buscarTodos());
    }
}
