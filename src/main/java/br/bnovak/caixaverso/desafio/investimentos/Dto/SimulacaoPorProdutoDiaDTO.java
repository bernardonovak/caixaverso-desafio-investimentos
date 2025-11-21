package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        description = "Objeto de Resposta da quantidade de Simulações realizadas por produto e dia",
        name = "SimulacaoPorProdutoDia",
        type = SchemaType.OBJECT
)
public final class SimulacaoPorProdutoDiaDTO {

    @Schema(
            description = "Nome do produto",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private final String produto;

    @Schema(
            description = "Data da simulação de investimento",
            implementation = LocalDate.class,
            type = SchemaType.STRING
    )
    private final LocalDate data;

    @Schema(
            description = "Quantidade de simulações do Produto por dia",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer quantidadeSimulacoes;

    @Schema(
            description = "Média do valor Final após investimento",
            implementation = BigDecimal.class
    )
    private final BigDecimal mediaValorFinal;

    public SimulacaoPorProdutoDiaDTO(String produto, LocalDate data, Integer quantidadeSimulacoes, BigDecimal mediaValorFinal) {
        this.produto = produto;
        this.data = data;
        this.quantidadeSimulacoes = quantidadeSimulacoes;
        this.mediaValorFinal = mediaValorFinal;
    }

    public String getProduto() {
        return produto;
    }

    public LocalDate getData() {
        return data;
    }

    public Integer getQuantidadeSimulacoes() {
        return quantidadeSimulacoes;
    }

    public BigDecimal getMediaValorFinal() {
        return mediaValorFinal;
    }
}
