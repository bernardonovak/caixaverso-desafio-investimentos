package br.bnovak.caixaverso.desafio.investimentos.Resources;

import br.bnovak.caixaverso.desafio.investimentos.ExceptionsMappers.ApiError;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

public class ApiResponse {

	public static Response ok(final Object entity) {
		return Response.ok(entity).type(MediaType.APPLICATION_JSON).build();
	}

	public static Response created(final Object entity, UriInfo uriInfo) {
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(entity.toString());
		return Response.created(uriBuilder.build()).build();
	}

	public static Response toResponse(final Response.Status status, final Object entity) {
		return Response.status(status).entity(entity).type(MediaType.APPLICATION_JSON).build();
	}

	public static Response badRequest(final Object error) {
		return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(error).build();
	}

	public static Response notFound(String message) {
		ApiError error = new ApiError(Response.Status.NOT_FOUND.getStatusCode(), message);
		return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

	public static Response noContent() {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	public static Response unavailable(final Object error) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_JSON).entity(error)
				.build();
	}

	public static Response unauthorized(String message) {
		ApiError error = new ApiError(Response.Status.UNAUTHORIZED.getStatusCode(), message);
		return Response.status(Response.Status.UNAUTHORIZED).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

	public static Response forbidden(String message) {
		ApiError error = new ApiError(Response.Status.FORBIDDEN.getStatusCode(), message);
		return Response.status(Response.Status.FORBIDDEN).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

	public static Response internalError(final Object error) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(error)
				.build();
	}

}
