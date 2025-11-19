package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.InvestimentosAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.InvestimentoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class InvestimentosResource implements InvestimentosAPI {

    @Inject
    InvestimentoService service;

    @Override
    public Response buscarInvestimentosCliente(Integer clienteId) throws NaoEncontradoException {
        return ApiResponse.ok(service.buscarInvestimentosCliente(clienteId));
    }
}
