package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Schema(
        description = "Objeto de Resposta de uma solicitação de simulação de investimento",
        name = "resultadoSimulacaoResponse",
        type = SchemaType.OBJECT
)
public final class SimularInvestimentoResponse {

    @Schema(
            description = "Objeto do Produto validado",
            implementation = ProdutoResponse.class,
            type = SchemaType.OBJECT
    )
    private final ProdutoResponse produtoValidado;

    @Schema(
            description = "Objeto do Resultado da simulação",
            implementation = ResultadoSimulacaoResponse.class,
            type = SchemaType.OBJECT
    )
    private final ResultadoSimulacaoResponse resultadoSimulacao;

    @Schema(
            description = "Data e hora da simulação de investimento",
            implementation = Instant.class,
            type = SchemaType.STRING
    )
    private Instant dataSimulacao;

    public SimularInvestimentoResponse(ProdutoResponse produtoValidado, ResultadoSimulacaoResponse resultadoSimulacao) {
        this.produtoValidado = produtoValidado == null ? null : new ProdutoResponse(produtoValidado);
        this.resultadoSimulacao = resultadoSimulacao == null ? null : new ResultadoSimulacaoResponse(resultadoSimulacao);
        this.dataSimulacao = Instant.now();
    }

    public ProdutoResponse getProdutoValidado() {
        return produtoValidado == null ? null : new ProdutoResponse(produtoValidado);
    }

    public ResultadoSimulacaoResponse getResultadoSimulacao() {
        return resultadoSimulacao == null ? null : new ResultadoSimulacaoResponse(resultadoSimulacao);
    }

    public Instant getDataSimulacao() {
        return dataSimulacao;
    }
}
