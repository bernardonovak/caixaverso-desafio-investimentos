package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(
        description = "Objeto de Resposta da API de investimentos",
        name = "investimentosResponse",
        type = SchemaType.OBJECT
)
public class InvestimentoResponse {

    @Schema(
            description = "ID do investimento",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer id;

    @Schema(
            description = "Tipo do Investimento",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String tipo;

    @Schema(
            description = "Valor atual do investimento",
            implementation = BigDecimal.class
    )
    private BigDecimal valor;

    @Schema(
            description = "Renatabilidade efetiva",
            implementation = BigDecimal.class
    )
    private BigDecimal rentabilidade;

    @Schema(
            description = "Data e hora do investimento",
            implementation = Instant.class,
            type = SchemaType.STRING
    )
    private String data;

    public InvestimentoResponse() {
    }

    public InvestimentoResponse(Integer id, String tipo, BigDecimal valor, BigDecimal rentabilidade, String data) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.rentabilidade = rentabilidade;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
