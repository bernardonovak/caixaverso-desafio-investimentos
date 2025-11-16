package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Schema(
        description = "Objeto de Resposta de uma solicitação de simulação de investimento",
        name = "resultadoSimulacaoResponse",
        type = SchemaType.OBJECT
)
public class SimularInvestimentoResponse {

    @Schema(
            description = "Objeto do Produto validado",
            implementation = ProdutoResponse.class,
            type = SchemaType.OBJECT
    )
    private ProdutoResponse produtoValidado;

    @Schema(
            description = "Objeto do Resultado da simulação",
            implementation = ResultadoSimulacaoResponse.class,
            type = SchemaType.OBJECT
    )
    private ResultadoSimulacaoResponse resultadoSimulacao;

    @Schema(
            description = "Data e hora da simulação de investimento",
            implementation = Instant.class,
            type = SchemaType.STRING
    )
    private Instant dataSimulacao;

    public SimularInvestimentoResponse(ProdutoResponse produtoValidado, ResultadoSimulacaoResponse resultadoSimulacao) {
        this.produtoValidado = produtoValidado;
        this.resultadoSimulacao = resultadoSimulacao;
        this.dataSimulacao = Instant.now();
    }

    public ProdutoResponse getProdutoValidado() {
        return produtoValidado;
    }

    public void setProdutoValidado(ProdutoResponse produtoValidado) {
        this.produtoValidado = produtoValidado;
    }

    public ResultadoSimulacaoResponse getResultadoSimulacao() {
        return resultadoSimulacao;
    }

    public void setResultadoSimulacao(ResultadoSimulacaoResponse resultadoSimulacao) {
        this.resultadoSimulacao = resultadoSimulacao;
    }

    public Instant getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(Instant dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}
