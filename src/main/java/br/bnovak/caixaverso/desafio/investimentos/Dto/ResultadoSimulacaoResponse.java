package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
        description = "Objeto de Resposta do Resultado de Simulação para Investimento",
        name = "resultadoSimulacao",
        type = SchemaType.OBJECT
)
public class ResultadoSimulacaoResponse {

    @Schema(
            description = "Valor final após prazo do investimento",
            implementation = BigDecimal.class
    )
    private BigDecimal valorFinal;

    @Schema(
            description = "Renatabilidade efetiva",
            implementation = BigDecimal.class
    )
    private BigDecimal rentabilidadeEfetiva;

    @Schema(
            description = "Prazo em meses do investimento",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer prazoMeses;

    public ResultadoSimulacaoResponse() {
    }

    public ResultadoSimulacaoResponse(BigDecimal valorFinal, BigDecimal rentabilidadeEfetiva, Integer prazoMeses) {
        this.valorFinal = valorFinal;
        this.rentabilidadeEfetiva = rentabilidadeEfetiva;
        this.prazoMeses = prazoMeses;
    }

    public ResultadoSimulacaoResponse(ResultadoSimulacaoResponse original) {
        if(original != null){
            this.valorFinal = original.valorFinal;
            this.rentabilidadeEfetiva = original.rentabilidadeEfetiva;
            this.prazoMeses = original.prazoMeses;
        }
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

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setRentabilidadeEfetiva(BigDecimal rentabilidadeEfetiva) {
        this.rentabilidadeEfetiva = rentabilidadeEfetiva;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }
}
