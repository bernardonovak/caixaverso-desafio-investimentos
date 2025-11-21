package br.bnovak.caixaverso.desafio.investimentos;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class simulacoesPorDiaResourceIT {

    private String token;

    private String getToken(){
        if(token == null){
            token = given()
                    .header("canal", "caixaverso")
                    .header("chave-secreta","LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQ21GbGN6STFOaTFqZEhJQUFBQUdZbU55ZVhCMEFBQUFHQUFBQUJENWt1QnpxUgpZUGNQWmlmZ3YzUC9TTkFBQUFHQUFBQUFFQUFBQXpBQUFBQzNOemFDMWxaREkxTlRFNUFBQUFJQjhEeUZ5dlVtMFdWL3p3CkJzUVpnTUM1bUhRZ05KOVlVVGpFVW4rZENDWWpBQUFBb0dhVXRwUEV2cXRvMWpqVkxqNGd2OHppL1RzRTFMbHJVSGhpYkMKMU1ISjVaTGtKa3g4VG1WUi95R2NSNGxUSWlMS0xJYVJrQ0V3MDR3TVI1emtCblVsNVpwbzRsT3JlNTIrZmdGR0xrandqSApKMWxZZzFVd3BNOUl2aENRUHp6WHhKNnJoL1FIdlNMcmRXaGxvek0yNm5EdnZ3R0w2TlZVdWZjN1RJNSt2b1MzcnhoL2lDCmQrYWprWlViVVh1WE51VG5RTWhnak1YTnYyM1pWcnAzVU9qRWM9Ci0tLS0tRU5EIE9QRU5TU0ggUFJJVkFURSBLRVktLS0tLQo=")
                    .contentType(ContentType.JSON)
                    .when().post("/v1/token")
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("access_token");
        }
        return token;
    }

    @Test
    public void deveRetornarSucessoEndpointSimularInvestimento(){
        Map<String, Object> body = new HashMap<>();
        body.put("clienteId", "2");
        body.put("valor", "10000.00");
        body.put("prazoMeses", "12");
        body.put("tipoProduto", "CDB");

        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/v1/simular-investimento")
                .then().statusCode(200);

    }

    @Test
    public void deveRetornarSucessoEndpointSimulacoesPorProdutoDia(){
        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .when().get("/v1/simulacoes/por-produto-dia")
                .then().statusCode(200);

    }

    @Test
    public void deveRetornarSucessoEndpointSimulacoes(){
        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .when().get("/v1/simulacoes")
                .then().statusCode(200);
    }

    @Test
    public void deveRetornarSucessoEndpointTelemetria(){
        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .when().get("/v1/telemetria")
                .then().statusCode(200);
    }

    @Test
    public void deveRetornarSucessoEndpointPerfilRisco(){
        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .when().get("/v1/perfil-risco/1")
                .then().statusCode(200);
    }

    @Test
    public void deveRetornarSucessoEndpointProdutosRecomendados(){
        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .when().get("/v1/produtos-recomendados/Conservador")
                .then().statusCode(200);
    }

    @Test
    public void deveRetornarSucessoEndpointInvestimentos(){
        given()
                .header("Authorization", "Bearer "+getToken())
                .contentType(ContentType.JSON)
                .when().get("/v1/investimentos/3")
                .then().statusCode(200);
    }

    @Test
    public void validaAutorizacaoEndpointSimularInvestimento(){
        Map<String, Object> body = new HashMap<>();
        body.put("clienteId", "2");
        body.put("valor", "10000.00");
        body.put("prazoMeses", "12");
        body.put("tipoProduto", "CDB");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/v1/simular-investimento")
                .then().statusCode(401);

    }

    @Test
    public void validaAutorizacaoEndpointSimulacoesPorProdutoDia(){
        given()
                .contentType(ContentType.JSON)
                .when().get("/v1/simulacoes/por-produto-dia")
                .then().statusCode(401);

    }

    @Test
    public void validaAutorizacaoEndpointSimulacoes(){
        given()
                .contentType(ContentType.JSON)
                .when().get("/v1/simulacoes")
                .then().statusCode(401);
    }

    @Test
    public void validaAutorizacaoEndpointTelemetria(){
        given()
                .contentType(ContentType.JSON)
                .when().get("/v1/telemetria")
                .then().statusCode(401);
    }

    @Test
    public void validaAutorizacaoEndpointPerfilRisco(){
        given()
                .contentType(ContentType.JSON)
                .when().get("/v1/perfil-risco/1")
                .then().statusCode(401);
    }

    @Test
    public void validaAutorizacaoEndpointProdutosRecomendados(){
        given()
                .contentType(ContentType.JSON)
                .when().get("/v1/produtos-recomendados/Conservador")
                .then().statusCode(401);
    }

    @Test
    public void validaAutorizacaoEndpointInvestimentos(){
        given()
                .contentType(ContentType.JSON)
                .when().get("/v1/investimentos/3")
                .then().statusCode(401);
    }
}
