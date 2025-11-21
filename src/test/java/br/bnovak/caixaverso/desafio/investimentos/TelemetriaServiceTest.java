package br.bnovak.caixaverso.desafio.investimentos;

import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaResponse;
import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaServicoDTO;
import br.bnovak.caixaverso.desafio.investimentos.Entities.LogServico;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.LogServicoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Services.Telemetria.TelemetriaService;
import br.bnovak.caixaverso.desafio.investimentos.Utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TelemetriaServiceTest {

    @Mock
    LogServicoRepository repository;

    @InjectMocks
    TelemetriaService service;

    @Test
    void deveRetornarResumoDeTelemetriaPorPeriodo() {
        Instant agora = Instant.now();
        Instant antes = agora.minusSeconds(3600);
        List<LogServico> logs = getMockLogServicos(agora, antes);

        Mockito.when(repository.buscarResumoPorPeriodo()).thenReturn(logs);

        TelemetriaResponse resultado = service.buscarPorPeriodo();


        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(Utils.converteInstantParaData(antes), resultado.getInicio());
        Assertions.assertEquals(Utils.converteInstantParaData(agora), resultado.getFim());
        Assertions.assertEquals(2, resultado.getServicos().size());

        TelemetriaServicoDTO simulacoes = resultado.getServicos().stream()
                .filter(s -> s.getNome().equals("simulacoes"))
                .findFirst().orElseThrow();
        Assertions.assertEquals(3L, simulacoes.getQuantidadeChamadas());
        Assertions.assertEquals(150.0, simulacoes.getMediaTempoRespostaMs());

        TelemetriaServicoDTO investimentos = resultado.getServicos().stream()
                .filter(s -> s.getNome().equals("investimentos"))
                .findFirst().orElseThrow();
        Assertions.assertEquals(2L, investimentos.getQuantidadeChamadas());
        Assertions.assertEquals(85.0, investimentos.getMediaTempoRespostaMs());
    }

    private List<LogServico> getMockLogServicos(Instant agora, Instant antes){
        return List.of(
                new LogServico(1L, "simulacoes", 120L, antes, "/simulacoes"),
                new LogServico(2L, "simulacoes", 180L, antes, "/simulacoes"),
                new LogServico(3L, "simulacoes", 150L, agora, "/simulacoes"),
                new LogServico(4L, "investimentos", 90L, antes, "/investimentos/2"),
                new LogServico(5L, "investimentos", 80L, agora, "/investimentos/1")
        );
    }
}
