package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
        description = "Objeto de Resposta do resumo da telemetria",
        name = "telemetriaServicoDTO",
        type = SchemaType.OBJECT
)
public final class TelemetriaServicoDTO {

    @Schema(
            description = "Nome do endpoint chamado",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private final String nome;

    @Schema(
            description = "Quantidade de chamdas realizadas",
            implementation = Long.class,
            type = SchemaType.INTEGER
    )
    private final Long quantidadeChamadas;

    @Schema(
            description = "Tempo média de resposta",
            implementation = Double.class,
            type = SchemaType.NUMBER
    )
    private final Double mediaTempoRespostaMs;

    public TelemetriaServicoDTO(String nome, Long quantidadeChamadas, Double mediaTempoRespostaMs) {
        this.nome = nome;
        this.quantidadeChamadas = quantidadeChamadas;
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }

    public String getNome() {
        return nome;
    }

    public Long getQuantidadeChamadas() {
        return quantidadeChamadas;
    }

    public Double getMediaTempoRespostaMs() {
        return mediaTempoRespostaMs;
    }
}
