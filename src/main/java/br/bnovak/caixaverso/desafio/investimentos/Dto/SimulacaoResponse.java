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
public class SimulacaoResponse {

    @Schema(
            description = "ID da Simulação",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer id;

    @Schema(
            description = "ID do cliente",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer clienteId;

    @Schema(
            description = "Nome do produto",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String produto;

    @Schema(
            description = "Valor inicial de investimento",
            implementation = BigDecimal.class
    )
    private BigDecimal valorInvestido;

    @Schema(
            description = "Valor final após prazo do investimento",
            implementation = BigDecimal.class
    )
    private BigDecimal valorFinal;

    @Schema(
            description = "Prazo em meses do investimento",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer prazoMeses;

    @Schema(
            description = "Data e hora da simulação de investimento",
            implementation = Instant.class,
            type = SchemaType.STRING
    )
    private Instant dataSimulacao;


    public SimulacaoResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public Instant getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(Instant dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}
