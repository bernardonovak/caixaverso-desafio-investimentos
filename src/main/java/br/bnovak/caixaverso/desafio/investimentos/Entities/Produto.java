package br.bnovak.caixaverso.desafio.investimentos.Entities;


import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO_PRODUTO")
    private TipoProduto tipoProduto;

    @Column(name = "RENTABILIDADE")
    private BigDecimal rentabilidade;

    @Column(name = "RISCO")
    private Risco risco;

    public Produto() {
    }

    public Produto(String nome, TipoProduto tipoProduto, BigDecimal rentabilidade, Risco risco) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
    }

    public Produto(Integer id, String nome, TipoProduto tipoProduto, BigDecimal rentabilidade, Risco risco) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
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

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }
}
