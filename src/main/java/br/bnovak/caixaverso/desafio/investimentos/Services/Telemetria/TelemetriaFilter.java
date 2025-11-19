package br.bnovak.caixaverso.desafio.investimentos.Services.Telemetria;

import br.bnovak.caixaverso.desafio.investimentos.Entities.LogServico;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.LogServicoRepository;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@Provider
@Priority(1)
public class TelemetriaFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    LogServicoRepository logServicoRepository;

    private static final String START_TIME = "start-time";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setProperty(START_TIME, System.currentTimeMillis());
    }

    @Transactional
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Long startTime = (Long) requestContext.getProperty(START_TIME);
        long tempoRespostaMs = System.currentTimeMillis() - startTime;

        String rawPath = requestContext.getUriInfo().getPath();
        String nomeServico = retornarNomeServico(rawPath);

        // Ignora chamadas ao próprio serviço de telemetria
        if (nomeServico.equalsIgnoreCase("telemetria")) {
            return;
        }

        LogServico log = new LogServico();
        log.setServico(retornarNomeServico(nomeServico));
        log.setTempoRespostaMs(tempoRespostaMs);
        log.setDataHora(Instant.now());
        log.setPath(rawPath);

        logServicoRepository.persist(log);
    }

    private String retornarNomeServico(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        String[] partes = path.split("/");
        // Regra especial: se começa com "produtos-recomendados", retorna só ele ignorando argumentos
        if (partes.length > 0 && partes[0].equalsIgnoreCase("produtos-recomendados")) {
            return "produtos-recomendados";
        }
        return Arrays.stream(path.split("/"))
                .takeWhile(nome -> !nome.matches("\\d+") && !nome.matches("[0-9a-fA-F\\-]{36}"))
                .collect(Collectors.joining("/"));
    }
}
