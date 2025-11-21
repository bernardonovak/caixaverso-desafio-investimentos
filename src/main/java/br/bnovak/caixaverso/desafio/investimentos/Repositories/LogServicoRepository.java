package br.bnovak.caixaverso.desafio.investimentos.Repositories;

import br.bnovak.caixaverso.desafio.investimentos.Entities.LogServico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LogServicoRepository implements PanacheRepository<LogServico> {

    public List<LogServico> buscarResumoPorPeriodo(){
        return find("ORDER BY id DESC").list();
    }
}
