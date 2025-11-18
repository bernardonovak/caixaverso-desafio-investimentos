package br.bnovak.caixaverso.desafio.investimentos.Repositories;

import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Investimento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InvestimentoRepository implements PanacheRepository<Investimento> {

    public List<Investimento> buscarPorCliente(Cliente cliente) {
        return find("cliente = ?1", cliente).list();
    }
}
