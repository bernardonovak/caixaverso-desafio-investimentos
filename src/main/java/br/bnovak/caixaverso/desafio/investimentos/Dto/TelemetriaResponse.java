package br.bnovak.caixaverso.desafio.investimentos.Dto;

import java.time.LocalDate;
import java.util.List;

public class TelemetriaResponse {

    private List<TelemetriaServicoDTO> servicos;

    private LocalDate inicio;

    private LocalDate fim;

    public TelemetriaResponse() {
    }

    public TelemetriaResponse(List<TelemetriaServicoDTO> servicos, LocalDate inicio, LocalDate fim) {
        this.servicos = servicos;
        this.inicio = inicio;
        this.fim = fim;
    }

    public List<TelemetriaServicoDTO> getServicos() {
        return servicos;
    }

    public void setServicos(List<TelemetriaServicoDTO> servicos) {
        this.servicos = servicos;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }
}
