package br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces;

import br.bnovak.caixaverso.desafio.investimentos.Dto.InvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
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
@Path("/investimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "investimentos", description = "Endpoint que retorna os investimentos realizados.")
public interface InvestimentosAPI {

    @Operation(description = "Retorna os investimentos do cliente por tipo", operationId = "investimentosAPI#buscarPorProdutoEDia", summary = "Retorna qunatidade de simulações realizadas por produto e dia")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvestimentoResponse.class, type = SchemaType.OBJECT)), description = "Investimentos retornados com sucesso.")
    @APIResponse(responseCode = "404", ref = "notFound", description = "Nenhum investimento encontrado.")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    @Path("{clienteId}")
    Response buscarInvestimentosCliente(@PathParam("clienteId") final Integer clienteId) throws NaoEncontradoException;

}
