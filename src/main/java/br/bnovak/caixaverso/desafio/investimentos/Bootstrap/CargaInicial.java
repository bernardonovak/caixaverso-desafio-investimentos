package br.bnovak.caixaverso.desafio.investimentos.Bootstrap;

import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Investimento;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Simulacao;
import br.bnovak.caixaverso.desafio.investimentos.Enum.PerfilRisco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ClienteRepository;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.InvestimentoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ProdutoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.SimulacaoRepository;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.math.BigDecimal;
import java.time.Instant;

@Startup
@ApplicationScoped
public class CargaInicial {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    SimulacaoRepository simulacaoRepository;

    @Inject
    InvestimentoRepository investimentoRepository;

    @ConfigProperty(name = "app.carregar-dados", defaultValue = "false")
    boolean carregarDados;

    @PostConstruct
    public void init(){
        if(carregarDados){
            carregarDadosProduto();
            carregarDadosCliente();
            carregarSimulacoes();
            carregarInvestimentos();
        }
    }

    @Transactional
    public void carregarDadosProduto(){
        if(produtoRepository.count() == 0){
            Produto p1 = new Produto("CDB Caixa 2026", TipoProduto.CDB, new BigDecimal("0.12"), PerfilRisco.BAIXO);
            Produto p2 = new Produto("CDB Moderado 2026", TipoProduto.CDB, new BigDecimal("0.14"), PerfilRisco.MODERADO);
            Produto p3 = new Produto("Fundo XPTO", TipoProduto.FUNDO, new BigDecimal("0.18"), PerfilRisco.ALTO);
            Produto p4 = new Produto("Fundo Moderado", TipoProduto.FUNDO, new BigDecimal("0.18"), PerfilRisco.MODERADO);

            produtoRepository.persist(p1);
            produtoRepository.persist(p2);
            produtoRepository.persist(p3);
            produtoRepository.persist(p4);
        }
    }

    @Transactional
    public void carregarDadosCliente(){
        if(clienteRepository.count() == 0){
            Cliente c1 = new Cliente(PerfilRisco.BAIXO, 10);
            Cliente c2 = new Cliente(PerfilRisco.MODERADO, 65);
            Cliente c3 = new Cliente(PerfilRisco.ALTO, 90);

            clienteRepository.persist(c1);
            clienteRepository.persist(c2);
            clienteRepository.persist(c3);
        }
    }

    @Transactional
    public void carregarSimulacoes(){
        if(simulacaoRepository.count() == 0){
            Simulacao s1 = new Simulacao(new Cliente(1), new Produto(1), new BigDecimal(10000), new BigDecimal(10100), 12, Instant.parse("2025-10-30T19:41:40.806260100Z"));
            Simulacao s2 = new Simulacao(new Cliente(1), new Produto(1), new BigDecimal(20000), new BigDecimal(20250), 12, Instant.parse("2025-10-30T19:45:40.806260100Z"));
            Simulacao s3 = new Simulacao(new Cliente(1), new Produto(1), new BigDecimal(30000), new BigDecimal(30360), 12, Instant.parse("2025-10-30T19:50:40.806260100Z"));
            Simulacao s4 = new Simulacao(new Cliente(1), new Produto(1), new BigDecimal(40000), new BigDecimal(40510), 12, Instant.parse("2025-10-30T19:55:40.806260100Z"));
            Simulacao s5 = new Simulacao(new Cliente(1), new Produto(1), new BigDecimal(50000), new BigDecimal(50880), 12, Instant.parse("2025-10-30T20:00:40.806260100Z"));

            Simulacao s6 = new Simulacao(new Cliente(2), new Produto(2), new BigDecimal(10000), new BigDecimal(10150), 12, Instant.parse("2025-10-30T19:41:40.806260100Z"));
            Simulacao s7 = new Simulacao(new Cliente(2), new Produto(2), new BigDecimal(20000), new BigDecimal(20350), 12, Instant.parse("2025-10-30T19:45:40.806260100Z"));
            Simulacao s8 = new Simulacao(new Cliente(2), new Produto(2), new BigDecimal(30000), new BigDecimal(30560), 12, Instant.parse("2025-10-30T19:50:40.806260100Z"));
            Simulacao s9 = new Simulacao(new Cliente(2), new Produto(2), new BigDecimal(40000), new BigDecimal(40910), 12, Instant.parse("2025-10-30T19:55:40.806260100Z"));

            Simulacao s10 = new Simulacao(new Cliente(2), new Produto(4), new BigDecimal(10000), new BigDecimal(10550), 12, Instant.parse("2025-11-05T19:41:40.806260100Z"));
            Simulacao s11 = new Simulacao(new Cliente(2), new Produto(4), new BigDecimal(20000), new BigDecimal(20950), 12, Instant.parse("2025-11-05T19:45:40.806260100Z"));
            Simulacao s12 = new Simulacao(new Cliente(2), new Produto(4), new BigDecimal(30000), new BigDecimal(31260), 12, Instant.parse("2025-11-05T19:50:40.806260100Z"));
            Simulacao s13 = new Simulacao(new Cliente(2), new Produto(4), new BigDecimal(40000), new BigDecimal(42910), 12, Instant.parse("2025-11-05T19:55:40.806260100Z"));

            Simulacao s14 = new Simulacao(new Cliente(2), new Produto(4), new BigDecimal(30000), new BigDecimal(31260), 12, Instant.parse("2025-11-10T19:50:40.806260100Z"));
            Simulacao s15 = new Simulacao(new Cliente(2), new Produto(4), new BigDecimal(40000), new BigDecimal(42910), 12, Instant.parse("2025-11-10T19:55:40.806260100Z"));

            simulacaoRepository.persist(s1);
            simulacaoRepository.persist(s2);
            simulacaoRepository.persist(s3);
            simulacaoRepository.persist(s4);
            simulacaoRepository.persist(s5);
            simulacaoRepository.persist(s6);
            simulacaoRepository.persist(s7);
            simulacaoRepository.persist(s8);
            simulacaoRepository.persist(s9);
            simulacaoRepository.persist(s10);
            simulacaoRepository.persist(s11);
            simulacaoRepository.persist(s12);
            simulacaoRepository.persist(s13);
            simulacaoRepository.persist(s14);
            simulacaoRepository.persist(s15);
        }
    }

    @Transactional
    public void carregarInvestimentos(){
        if(investimentoRepository.count() == 0){
            Investimento i1 = new Investimento(new Cliente(1), new Produto(1), new BigDecimal(10000), new BigDecimal(10100), 12, Instant.parse("2025-10-30T19:41:40.806260100Z"));

            Investimento i2 = new Investimento(new Cliente(3), new Produto(2), new BigDecimal(10000), new BigDecimal(10250), 36, Instant.parse("2025-11-05T19:45:40.806260100Z"));
            Investimento i3 = new Investimento(new Cliente(3), new Produto(3), new BigDecimal(20000), new BigDecimal(20880), 12, Instant.parse("2025-10-30T19:41:40.806260100Z"));

            investimentoRepository.persist(i1);
            investimentoRepository.persist(i2);
            investimentoRepository.persist(i3);
        }
    }
}
