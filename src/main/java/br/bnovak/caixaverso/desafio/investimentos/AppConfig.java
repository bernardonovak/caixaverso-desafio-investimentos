package br.bnovak.caixaverso.desafio.investimentos;

import br.bnovak.caixaverso.desafio.investimentos.ExceptionsMappers.ApiError;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@OpenAPIDefinition(
		info = @Info(
				title = "Quarkus - JPA com Panache Quickstart", 
				version = "1.0.0", 
				description = "API de exemplo para uso do ORM Panache no Quarkus", 
				contact = @Contact(
						name = "Suart", 
						url = ""
						)
				),
		components = @Components(
	            responses = {
	            		@APIResponse(
		    	                name = "noContent",
		    	                responseCode = "204",
		    	                description = "No Content",
		    	                content = @Content(mediaType = MediaType.APPLICATION_JSON)
		    	            ),
	            		@APIResponse(
		    	                name = "illegalRequest",
		    	                responseCode = "400",
		    	                description = "Illegal request",
		    	                content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "apiError"))
		    	            ),
	    	            @APIResponse(
	    	                name = "notFound",
	    	                responseCode = "404",
	    	                description = "Object Not found",
	    	                content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "apiError"))
	    	            ),
	    	            @APIResponse(
	    	                name = "internalError",
	    	                responseCode = "500",
	    	                description = "Internal Server Error",
	    	                content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "apiError"))
	    	            ),
	            		@APIResponse(
	    	                name = "serviceUnavailable",
	    	                responseCode = "503",
	    	                description = "Service Unavailable",
	    	                content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "apiError"))
	    	            )
	    	        },
        		schemas = {
        				@Schema(
        					description = "Objeto retorno em caso de erros na requisição", 
        					name = "apiError", 
        					type = SchemaType.OBJECT, 
        					implementation = ApiError.class
        				)
    	        }
		)
	)
 
@ApplicationPath("/v1")
public class AppConfig extends Application {

}
