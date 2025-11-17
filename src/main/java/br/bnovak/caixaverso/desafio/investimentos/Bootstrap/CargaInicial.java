package br.bnovak.caixaverso.desafio.investimentos.Bootstrap;

import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ClienteRepository;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ProdutoRepository;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.math.BigDecimal;
import java.util.List;

@Startup
@ApplicationScoped
public class CargaInicial {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @ConfigProperty(name = "app.carregar-dados", defaultValue = "false")
    boolean carregarDados;

    @PostConstruct
    public void init(){
        if(carregarDados){
            carregarDadosProduto();
            carregarDadosCliente();
        }
    }

    @Transactional
    public void carregarDadosProduto(){
        if(produtoRepository.count() == 0){
            Produto p1 = new Produto("CDB Caixa 2026", TipoProduto.CDB, new BigDecimal("0.12"), Risco.BAIXO);
            Produto p2 = new Produto("CDB Moderado 2026", TipoProduto.CDB, new BigDecimal("0.14"), Risco.MODERADO);
            Produto p3 = new Produto("Fundo XPTO", TipoProduto.FUNDO, new BigDecimal("0.18"), Risco.ALTO);
            Produto p4 = new Produto("Fundo Moderado", TipoProduto.FUNDO, new BigDecimal("0.18"), Risco.MODERADO);

            produtoRepository.persist(p1);
            produtoRepository.persist(p2);
            produtoRepository.persist(p3);
            produtoRepository.persist(p4);
        }
    }

    @Transactional
    public void carregarDadosCliente(){
        if(clienteRepository.count() == 0){
            Cliente c1 = new Cliente(Risco.BAIXO, 10);
            Cliente c2 = new Cliente(Risco.MODERADO, 65);
            Cliente c3 = new Cliente(Risco.ALTO, 90);

            clienteRepository.persist(c1);
            clienteRepository.persist(c2);
            clienteRepository.persist(c3);
        }
    }
}
