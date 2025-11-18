package br.bnovak.caixaverso.desafio.investimentos.Entities;

import br.bnovak.caixaverso.desafio.investimentos.Enum.PerfilRisco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

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
    private PerfilRisco perfilRisco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Simulacao> simulacoes;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(String nome, TipoProduto tipoProduto, BigDecimal rentabilidade, PerfilRisco perfilRisco) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.rentabilidade = rentabilidade;
        this.perfilRisco = perfilRisco;
    }

    public Produto(Integer id, String nome, TipoProduto tipoProduto, BigDecimal rentabilidade, PerfilRisco perfilRisco) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.rentabilidade = rentabilidade;
        this.perfilRisco = perfilRisco;
    }

    public Produto(Integer id, String nome, TipoProduto tipoProduto, BigDecimal rentabilidade, PerfilRisco perfilRisco, List<Simulacao> simulacoes) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.rentabilidade = rentabilidade;
        this.perfilRisco = perfilRisco;
        this.simulacoes = simulacoes;
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

    public PerfilRisco getRisco() {
        return perfilRisco;
    }

    public void setRisco(PerfilRisco perfilRisco) {
        this.perfilRisco = perfilRisco;
    }

    public List<Simulacao> getSimulacoes() {
        return simulacoes;
    }

    public void setSimulacoes(List<Simulacao> simulacoes) {
        this.simulacoes = simulacoes;
    }
}
