package br.bnovak.caixaverso.desafio.investimentos.Resources.Interfaces;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ProdutoResponse;
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

@Path("/produtos-recomendados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "ProdutosRecomendados", description = "Endpoint que retorna produtos recomendados")
public interface ProdutosRecomendadosAPI {

    @Operation(description = "Retorna produtos recomendados de acordo com perfil", operationId = "ProdutosRecomendadosAPI#ProdutosRecomendados", summary = "Retorna produtos recomendados de acordo com perfil")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponse.class, type = SchemaType.OBJECT)), description = "Consulta dos produtos recomendados realizada com sucesso.")
    @APIResponse(responseCode = "404", ref = "notFound", description = "Nenhum produto encontrado para o perfil.")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    @Path("{perfil}")
    Response buscarUm(@PathParam("perfil") final String perfil) throws NaoEncontradoException;
}
