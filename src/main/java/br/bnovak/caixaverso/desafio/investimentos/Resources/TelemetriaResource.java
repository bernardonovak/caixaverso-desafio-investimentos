package br.bnovak.caixaverso.desafio.investimentos.Resources;


import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.TelemetriaAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.Telemetria.TelemetriaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class TelemetriaResource implements TelemetriaAPI {

    @Inject
    TelemetriaService service;

    @Override
    public Response buscarPorPeriodo() {
        return ApiResponse.ok(service.buscarPorPeriodo());
    }
}
