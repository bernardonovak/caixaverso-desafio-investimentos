package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(
        description = "Objeto de Resposta de uma simulação realizada.",
        name = "SimulacaoResponse",
        type = SchemaType.OBJECT
)
public final class SimulacaoResponse {

    @Schema(
            description = "ID da Simulação",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer id;

    @Schema(
            description = "ID do cliente",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer clienteId;

    @Schema(
            description = "Nome do produto",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private final String produto;

    @Schema(
            description = "Valor inicial de investimento",
            implementation = BigDecimal.class
    )
    private final BigDecimal valorInvestido;

    @Schema(
            description = "Valor final após prazo do investimento",
            implementation = BigDecimal.class
    )
    private final BigDecimal valorFinal;

    @Schema(
            description = "Prazo em meses do investimento",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer prazoMeses;

    @Schema(
            description = "Data e hora da simulação de investimento",
            implementation = Instant.class,
            type = SchemaType.STRING
    )
    private final Instant dataSimulacao;


    public SimulacaoResponse(Integer id, Integer clienteId, String produto, BigDecimal valorInvestido, BigDecimal valorFinal, Integer prazoMeses, Instant dataSimulacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.produto = produto;
        this.valorInvestido = valorInvestido;
        this.valorFinal = valorFinal;
        this.prazoMeses = prazoMeses;
        this.dataSimulacao = dataSimulacao;
    }

    public Integer getId() {
        return id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public String getProduto() {
        return produto;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public Instant getDataSimulacao() {
        return dataSimulacao;
    }
}
