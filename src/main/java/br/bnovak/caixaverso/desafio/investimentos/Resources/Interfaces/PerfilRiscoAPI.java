package br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ClienteResponse;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/perfil-risco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "PerfilRisco", description = "Endpoint que retorna perfil de risco")
public interface PerfilRiscoAPI {

    @Operation(description = "Recupera perfil do cliente pelo ID do cliente", operationId = "PerfilRiscoAPI#buscarPerfilCliente", summary = "Recupera perfil do cliente pelo ID do cliente")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class, type = SchemaType.OBJECT)), description = "Perfil retornado com sucesso")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    @Path("{clienteId}")
    public Response buscarPerfilCliente(@PathParam("clienteId") final Integer clienteId) throws NaoEncontradoException;

    @Operation(description = "Retorna todos os clientes com perfis de Risco", operationId = "PerfilRiscoAPI#buscarTodos", summary = "Retorna todos os clientes com perfis de Risco")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class, type = SchemaType.OBJECT)), description = "Consulta de clientes realizada com sucesso")
    @GET
    public Response buscarTodos();
}
