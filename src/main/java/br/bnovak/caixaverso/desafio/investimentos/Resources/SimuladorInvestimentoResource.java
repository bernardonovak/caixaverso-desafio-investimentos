package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Services.SimulacaoService;
import br.bnovak.caixaverso.desafio.investimentos.Dto.SimularInvestimentoRequest;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.SimuladorInvestimentoAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@ApplicationScoped
public class SimuladorInvestimentoResource implements SimuladorInvestimentoAPI {

    @Inject
    SimulacaoService service;

    @Override
    public Response simularInvestimento(SimularInvestimentoRequest request, UriInfo uriInfo) throws NaoEncontradoException {
        return ApiResponse.ok(service.simularInvestimento(request));
    }
}
