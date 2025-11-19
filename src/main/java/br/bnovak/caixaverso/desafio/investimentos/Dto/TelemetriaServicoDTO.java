package br.bnovak.caixaverso.desafio.investimentos.Dto;


public class TelemetriaServicoDTO {

    private String nome;

    private Long quantidadeChamadas;

    private Double mediaTempoRespostaMs;

    public TelemetriaServicoDTO() {
    }

    public TelemetriaServicoDTO(String nome, Long quantidadeChamadas, Double mediaTempoRespostaMs) {
        this.nome = nome;
        this.quantidadeChamadas = quantidadeChamadas;
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQuantidadeChamadas() {
        return quantidadeChamadas;
    }

    public void setQuantidadeChamadas(Long quantidadeChamadas) {
        this.quantidadeChamadas = quantidadeChamadas;
    }

    public Double getMediaTempoRespostaMs() {
        return mediaTempoRespostaMs;
    }

    public void setMediaTempoRespostaMs(Double mediaTempoRespostaMs) {
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }
}
