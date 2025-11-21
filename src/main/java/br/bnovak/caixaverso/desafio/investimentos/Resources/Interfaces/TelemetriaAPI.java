package br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces;

import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaResponse;
import io.quarkus.security.Authenticated;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Authenticated
@Path("/telemetria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Telemetria", description = "Endpoint que exibe o volume e a média de tempo de resposta dos endpoints requisitados por Perído")
public interface TelemetriaAPI {

    @Operation(description = "Recupera o volume e o tempo médio de resposta dos endpoints", operationId = "TelemetriaAPI#buscarPorPeriodo", summary = "Recupera o volume e o tempo médio de resposta dos endpoints")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TelemetriaResponse.class, type = SchemaType.OBJECT)), description = "Consulta de produto para investimento realizado com sucesso")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    Response buscarPorPeriodo();
//    Response buscarPorPeriodo(@HeaderParam("inicio") String inicio, @HeaderParam("fim") String fim);
}
