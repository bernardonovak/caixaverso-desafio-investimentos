package br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoAutorizadoException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/token")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Token", description = "Endpoint para retornar token válido. Somente endoints de produtos serão autenticados")
public interface AuthAPI {

    @Operation(description = "Retorna token com validade de 30 minutos", operationId = "TokenAPI#gerarToken", summary = "Retorna token com validade de 30 minutos")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class, type = SchemaType.OBJECT)), description = "Token gerado com sucesso")
    @POST
    public Response buscarToken(@Context HttpHeaders headers) throws NaoAutorizadoException;
}
