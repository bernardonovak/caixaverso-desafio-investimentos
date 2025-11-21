package br.bnovak.caixaverso.desafio.investimentos.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "SIMULACAO")
public class Simulacao {

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

    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;

    @Column(name = "PRAZO_MESES")
    private Integer prazoMeses;

    @Column(name = "DATA_SIMULACAO")
    private Instant dataSimulacao;

    public Simulacao() {
    }

    public Simulacao(BigDecimal valorInvestido, BigDecimal valorFinal, Integer prazoMeses, Instant dataSimulacao) {
        this.valorInvestido = valorInvestido;
        this.valorFinal = valorFinal;
        this.prazoMeses = prazoMeses;
        this.dataSimulacao = dataSimulacao;
    }

    public Simulacao(Cliente cliente, Produto produto, BigDecimal valorInvestido, BigDecimal valorFinal, Integer prazoMeses, Instant dataSimulacao) {
        this.cliente = criaCopia(cliente);
        this.produto = criaCopia(produto);
        this.valorInvestido = valorInvestido;
        this.valorFinal = valorFinal;
        this.prazoMeses = prazoMeses;
        this.dataSimulacao = dataSimulacao;
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

    private Cliente criaCopia(Cliente cliente){
        return cliente != null ? new Cliente(cliente) : null;
    }

    private Produto criaCopia(Produto produto){
        return produto != null ? new Produto(produto) : null;
    }
}
