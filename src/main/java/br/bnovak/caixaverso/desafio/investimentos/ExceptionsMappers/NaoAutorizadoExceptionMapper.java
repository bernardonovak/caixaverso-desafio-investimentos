package br.bnovak.caixaverso.desafio.investimentos.ExceptionsMappers;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoAutorizadoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static br.bnovak.caixaverso.desafio.investimentos.Resources.ApiResponse.unauthorized;

@Provider
public class NaoAutorizadoExceptionMapper implements ExceptionMapper<NaoAutorizadoException> {

	@Override
	public Response toResponse(NaoAutorizadoException exception) {
		return unauthorized(exception.getMessage());
	}

}
