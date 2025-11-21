package br.bnovak.caixaverso.desafio.investimentos.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "INVESTIMENTO")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", nullable = false)
    private Produto produto;

    @Column(name = "VALOR_INVESTIDO")
    private BigDecimal valorInvestido;

    @Column(name = "VALOR_ATUAL")
    private BigDecimal valorAtual;

    @Column(name = "PRAZO_MESES")
    private Integer prazoMeses;

    @Column(name = "DATA_INVESTIMENTO")
    private Instant dataInvestimento;

    public Investimento() {
    }

    public Investimento(Cliente cliente, Produto produto, BigDecimal valorInvestido, BigDecimal valorAtual, Integer prazoMeses, Instant dataInvestimento) {
        this.cliente = criaCopia(cliente);
        this.produto = criaCopia(produto);
        this.valorInvestido = valorInvestido;
        this.valorAtual = valorAtual;
        this.prazoMeses = prazoMeses;
        this.dataInvestimento = dataInvestimento;
    }

    public Investimento(Integer id, Cliente cliente, Produto produto, BigDecimal valorInvestido, BigDecimal valorAtual, Integer prazoMeses, Instant dataInvestimento) {
        this.id = id;
        this.cliente = criaCopia(cliente);
        this.produto = criaCopia(produto);
        this.valorInvestido = valorInvestido;
        this.valorAtual = valorAtual;
        this.prazoMeses = prazoMeses;
        this.dataInvestimento = dataInvestimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return criaCopia(cliente);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = criaCopia(cliente);
    }

    public Produto getProduto() {
        return criaCopia(produto);
    }

    public void setProduto(Produto produto) {
        this.produto = criaCopia(produto);
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public Instant getDataInvestimento() {
        return dataInvestimento;
    }

    public void setDataInvestimento(Instant dataInvestimento) {
        this.dataInvestimento = dataInvestimento;
    }

    private Cliente criaCopia(Cliente cliente){
        return cliente != null ? new Cliente(cliente) : null;
    }

    private Produto criaCopia(Produto produto){
        return produto != null ? new Produto(produto) : null;
    }
}
