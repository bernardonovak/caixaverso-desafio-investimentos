package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoAutorizadoException;
import br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces.AuthAPI;
import br.bnovak.caixaverso.desafio.investimentos.Services.AuthService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AuthResource implements AuthAPI {

    @Inject
    AuthService authService;

    @Override
    public Response buscarToken(@Context HttpHeaders headers) throws NaoAutorizadoException {
        String canal = headers.getHeaderString("canal");
        String chave = headers.getHeaderString("chave-secreta");
        return ApiResponse.ok(authService.gerarToken(canal, chave));
    }

}
