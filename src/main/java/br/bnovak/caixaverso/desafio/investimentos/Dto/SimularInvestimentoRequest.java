package br.bnovak.caixaverso.desafio.investimentos.Dto;

import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
        description = "Objeto de Requisição para simulação de investimento",
        name = "simularInvestimento",
        type = SchemaType.OBJECT
)
public final class SimularInvestimentoRequest {

    @Schema(
            description = "ID do cliente",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer clienteId;

    @Schema(
            description = "Valor inicial de investimento",
            implementation = BigDecimal.class
    )
    private final BigDecimal valor;

    @Schema(
            description = "Prazo em meses que o investimento ficará rendendo",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer prazoMeses;

    @Schema(
            description = "Tipo do investimento",
            implementation = TipoProduto.class,
            type = SchemaType.STRING
    )
    private final TipoProduto tipoProduto;

    public SimularInvestimentoRequest(Integer clienteId, BigDecimal valor, Integer prazoMeses, TipoProduto tipoProduto) {
        this.clienteId = clienteId;
        this.valor = valor;
        this.prazoMeses = prazoMeses;
        this.tipoProduto = tipoProduto;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }
}
