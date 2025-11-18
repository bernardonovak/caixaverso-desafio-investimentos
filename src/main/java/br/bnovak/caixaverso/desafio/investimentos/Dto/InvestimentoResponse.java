package br.bnovak.caixaverso.desafio.investimentos.Dto;

import java.math.BigDecimal;

public class InvestimentoResponse {

    private Integer id;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal rentabilidade;
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
