package br.bnovak.caixaverso.desafio.investimentos.Repositories;

import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.PerfilRisco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public Optional<Produto> buscarPorTipoERisco(TipoProduto tipo, PerfilRisco perfilRisco) {
        return find("tipoProduto = ?1 and perfilRisco = ?2", tipo, perfilRisco).singleResultOptional();
    }

    public List<Produto> buscarPorRiscoOrdenadoPorRenatabilidade(PerfilRisco perfilRisco) {
        return find("perfilRisco = ?1 ORDER BY rentabilidade DESC", perfilRisco).list();
    }
}
