package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
        description = "Objeto de Resposta do Produto para Investimento",
        name = "produtoResponse",
        type = SchemaType.OBJECT
)
public class ProdutoResponse {

    @Schema(
            description = "ID do produto",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer id;

    @Schema(
            description = "Nome do produto",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String nome;

    @Schema(
            description = "Tipo do Investimento",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String tipo;

    @Schema(
            description = "Renatabilidade efetiva",
            implementation = BigDecimal.class
    )
    private BigDecimal rentabilidade;

    @Schema(
            description = "Risco do Investimento",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String risco;

    public ProdutoResponse(Integer id, String nome, String tipo, BigDecimal rentabilidade, String risco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }
}
