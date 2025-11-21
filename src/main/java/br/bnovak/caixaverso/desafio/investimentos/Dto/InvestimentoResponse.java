package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Schema(
        description = "Objeto de Resposta da API de investimentos",
        name = "investimentosResponse",
        type = SchemaType.OBJECT
)
public final class InvestimentoResponse {

    @Schema(
            description = "ID do investimento",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer id;

    @Schema(
            description = "Tipo do Investimento",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private final String tipo;

    @Schema(
            description = "Valor atual do investimento",
            implementation = BigDecimal.class
    )
    private final BigDecimal valor;

    @Schema(
            description = "Renatabilidade efetiva",
            implementation = BigDecimal.class
    )
    private final BigDecimal rentabilidade;

    @Schema(
            description = "Data e hora do investimento",
            implementation = Instant.class,
            type = SchemaType.STRING
    )
    private final LocalDate data;

    public InvestimentoResponse(Integer id, String tipo, BigDecimal valor, BigDecimal rentabilidade, LocalDate data) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.rentabilidade = rentabilidade;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public LocalDate getData() {
        return data;
    }
}
