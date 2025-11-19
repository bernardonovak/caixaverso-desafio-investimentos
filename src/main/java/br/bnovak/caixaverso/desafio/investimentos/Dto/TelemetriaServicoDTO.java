package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
        description = "Objeto de Resposta do resumo da telemetria",
        name = "telemetriaServicoDTO",
        type = SchemaType.OBJECT
)
public class TelemetriaServicoDTO {

    @Schema(
            description = "Nome do endpoint chamado",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String nome;

    @Schema(
            description = "Quantidade de chamdas realizadas",
            implementation = Long.class,
            type = SchemaType.INTEGER
    )
    private Long quantidadeChamadas;

    @Schema(
            description = "Tempo média de resposta",
            implementation = Double.class,
            type = SchemaType.NUMBER
    )
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
