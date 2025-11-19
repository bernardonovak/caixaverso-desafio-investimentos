package br.bnovak.caixaverso.desafio.investimentos.Repositories;

import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public Optional<Produto> buscarPorTipoERisco(TipoProduto tipo, Risco risco) {
        return find("tipoProduto = ?1 and risco = ?2 ORDER BY rentabilidade DESC", tipo, risco).singleResultOptional();
    }

    public List<Produto> buscarPorRiscoOrdenadoPorRenatabilidade(Risco risco) {
        return find("risco = ?1 ORDER BY rentabilidade DESC", risco).list();
    }
}
