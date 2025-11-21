package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
        description = "Objeto de Resposta do Resultado de Simulação para Investimento",
        name = "resultadoSimulacao",
        type = SchemaType.OBJECT
)
public final class ResultadoSimulacaoResponse {

    @Schema(
            description = "Valor final após prazo do investimento",
            implementation = BigDecimal.class
    )
    private final BigDecimal valorFinal;

    @Schema(
            description = "Renatabilidade efetiva",
            implementation = BigDecimal.class
    )
    private final BigDecimal rentabilidadeEfetiva;

    @Schema(
            description = "Prazo em meses do investimento",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer prazoMeses;

    public ResultadoSimulacaoResponse(BigDecimal valorFinal, BigDecimal rentabilidadeEfetiva, Integer prazoMeses) {
        this.valorFinal = valorFinal;
        this.rentabilidadeEfetiva = rentabilidadeEfetiva;
        this.prazoMeses = prazoMeses;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public BigDecimal getRentabilidadeEfetiva() {
        return rentabilidadeEfetiva;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

}
