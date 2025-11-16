package br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces;

import br.bnovak.caixaverso.desafio.investimentos.Dto.SimularInvestimentoRequest;
import br.bnovak.caixaverso.desafio.investimentos.Dto.SimularInvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/simular-investimento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "simular-investimento", description = "Endpoint que realiza a simulação de um determinado tipo de investimento para um Cliente de acordo com seu perfil de risco")
public interface SimuladorInvestimentoAPI {

    @Operation(description = "Retorna produto de acordo com o perfil do cliente e a simulação do invetimento", operationId = "SimuladorInvestimentoAPI#simularInvestimento", summary = "Retorna produto de acordo com o perfil do cliente e a simulação do invetimento")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SimularInvestimentoResponse.class, type = SchemaType.OBJECT)), description = "Simulação realizada com sucesso.")
    @APIResponse(responseCode = "404", ref = "notFound")
    @POST
    public Response simularInvestimento(final SimularInvestimentoRequest request, @Context UriInfo uriInfo) throws NaoEncontradoException;

}
