package br.bnovak.caixaverso.desafio.investimentos.ExceptionsMappers;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static br.bnovak.caixaverso.desafio.investimentos.Resources.ApiResponse.notFound;

@Provider
public class NaoEncontradoExceptionMapper implements ExceptionMapper<NaoEncontradoException> {

	@Override
	public Response toResponse(NaoEncontradoException exception) {
		return notFound(exception.getMessage());
	}

}
