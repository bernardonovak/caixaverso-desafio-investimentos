package br.bnovak.caixaverso.desafio.investimentos.Repositories;

import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaResponse;
import br.bnovak.caixaverso.desafio.investimentos.Dto.TelemetriaServicoDTO;
import br.bnovak.caixaverso.desafio.investimentos.Entities.LogServico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LogServicoRepository implements PanacheRepository<LogServico> {

    public List<LogServico> buscarResumoPorPeriodo(){
        return find("ORDER BY id DESC").list();
    }

//    public List<TelemetriaServicoDTO> buscarResumoPorPeriodo(){
//        return getEntityManager()
//                .createNativeQuery("""
//                SELECT
//                    servico AS nome,
//                    COUNT(id) AS quantidadeChamadas,
//                    AVG(tempo_resposta_ms) AS mediaTempoRespostasMs
//                FROM log_servicos
//                GROUP BY servico
//            """, "TelemetriaServicoDTO")
//                        .getResultList();
//    }
}
