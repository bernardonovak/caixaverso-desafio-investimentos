package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.SimulacoesAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class SimulacoesResource implements SimulacoesAPI {

    @Inject
    SimulacaoService service;

    @Override
    public Response buscarTodos() {
        return ApiResponse.ok(service.buscarTodos());
    }
}
