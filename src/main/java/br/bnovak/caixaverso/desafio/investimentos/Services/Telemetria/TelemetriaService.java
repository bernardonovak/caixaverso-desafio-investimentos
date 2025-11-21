package br.bnovak.caixaverso.desafio.investimentos.Services.Telemetria;

import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaResponse;
import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaServicoDTO;
import br.bnovak.caixaverso.desafio.investimentos.Entities.LogServico;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.LogServicoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Utils.Utils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class TelemetriaService {

    @Inject
    LogServicoRepository repository;

    public TelemetriaResponse buscarPorPeriodo(){
        List<LogServico> logServicos = repository.buscarResumoPorPeriodo();

        // Calcula início e fim do período
        Optional<Instant> inicio = logServicos.stream()
                .map(LogServico::getDataHora)
                .min(Comparator.naturalOrder());
        Optional<Instant> fim = logServicos.stream()
                .map(LogServico::getDataHora)
                .max(Comparator.naturalOrder());

        List<TelemetriaServicoDTO> resumoServicos = logServicos.stream()
                .collect(Collectors.groupingBy(LogServico::getServico))
                .entrySet()
                .stream()
                .map(resumo -> {
                    String nome = resumo.getKey();
                    List<LogServico> chamadas = resumo.getValue();

                    Long quantidadeChamadas = chamadas.stream().count();
                    Double mediaTempoRespostaMs = chamadas.stream().mapToLong(LogServico::getTempoRespostaMs).average().orElse(0.0);
                    return new TelemetriaServicoDTO(nome, quantidadeChamadas, mediaTempoRespostaMs);
                })
                .toList();

        TelemetriaResponse telemetriaResponse = new TelemetriaResponse();
        telemetriaResponse.setServicos(resumoServicos);
        telemetriaResponse.setInicio(inicio.map(Utils::converteInstantParaData).orElse(null));
        telemetriaResponse.setFim(fim.map(Utils::converteInstantParaData).orElse(null));
        return telemetriaResponse;
    }
}
