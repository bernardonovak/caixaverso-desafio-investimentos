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

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Produtos", description = "Endpoint que representa os produtos disponíveis para investimento")
public interface ProdutoAPI {

    @Operation(description = "Retorna todos os produtos para invetimento", operationId = "ProdutosAPI#buscarTodos", summary = "Retorna todos os produtos para invetimento")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponse.class, type = SchemaType.OBJECT)), description = "Consulta de produtos para empréstimo realizada com sucesso")
    @GET
    public Response buscarTodos();

    @Operation(description = "Recupera um produto para investimento por ID", operationId = "ProdutosAPI#buscarUm", summary = "Recupera um produto para investimento por ID")
    @APIResponse(name = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponse.class, type = SchemaType.OBJECT)), description = "Consulta de produto para empréstimo realizado com sucesso")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    @Path("{ID}")
    public Response buscarUm(@PathParam("ID") final Integer idProduto) throws NaoEncontradoException;
}
