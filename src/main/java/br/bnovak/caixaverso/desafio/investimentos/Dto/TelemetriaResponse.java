package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(
        description = "Objeto de Resposta da API de telemetria",
        name = "telemetriaResponse",
        type = SchemaType.OBJECT
)
public class TelemetriaResponse {

    @Schema(
            description = "Lista do resumo dos endpoints com nome, quantidade e média do tempo de resposta.",
            implementation = List.class,
            type = SchemaType.ARRAY
    )
    private List<TelemetriaServicoDTO> servicos;

    @Schema(
            description = "Data inicial do período",
            implementation = LocalDate.class,
            type = SchemaType.STRING
    )
    private LocalDate inicio;

    @Schema(
            description = "Data final do período",
            implementation = LocalDate.class,
            type = SchemaType.STRING
    )
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
