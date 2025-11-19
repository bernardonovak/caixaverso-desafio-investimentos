package br.bnovak.caixaverso.desafio.investimentos.Entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "LOG_SERVICOS")
public class LogServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERVICO")
    private String servico;

    @Column(name = "TEMPO_RESPOSTA_MS")
    private Long tempoRespostaMs;

    @Column(name = "DATA_HORA")
    private Instant dataHora;

    @Column(name = "PATH")
    private String path;

    public LogServico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Long getTempoRespostaMs() {
        return tempoRespostaMs;
    }

    public void setTempoRespostaMs(Long tempoRespostaMs) {
        this.tempoRespostaMs = tempoRespostaMs;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
