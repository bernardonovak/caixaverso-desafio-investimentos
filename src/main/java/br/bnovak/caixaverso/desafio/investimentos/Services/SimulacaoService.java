package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Dto.*;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Simulacao;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.SimulacaoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.SimulacaoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Utils.Financeiro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class SimulacaoService {

    @Inject
    SimulacaoRepository repository;

    @Inject
    ProdutoService produtoService;

    @Inject
    ClienteService clienteService;

    @Inject
    SimulacaoMapper mapper;

    public SimularInvestimentoResponse simularInvestimento(SimularInvestimentoRequest request) throws NaoEncontradoException {
        //buscar Cliente para obter o perfil de risco do cliente
        ClienteResponse cliente = clienteService.buscarPorID(request.getClienteId());
        Risco perfil = Risco.buscarPorNome(cliente.getPerfil());
        ProdutoResponse produtoValidado = produtoService.buscarProdutoAdequado(request.getTipoProduto(), perfil);

        ResultadoSimulacaoResponse simulacao = simularResultadoInvestimento(request, produtoValidado);

        SimularInvestimentoResponse simulacaoResponse = new SimularInvestimentoResponse(produtoValidado, simulacao);

        //criar metodo privado para gravar na tabela de simulacoes realizadas
        gravarSimulacao(request, simulacaoResponse);
        return simulacaoResponse;
    }

    private ResultadoSimulacaoResponse simularResultadoInvestimento(SimularInvestimentoRequest request, ProdutoResponse p){
        BigDecimal valorInicial = request.getValor();
        Integer prazoMeses = request.getPrazoMeses();
        BigDecimal taxaEfetivaAnual = p.getRentabilidade();

        BigDecimal taxaEfetivaMensal = Financeiro.calcularTaxaEfetivaMensal(taxaEfetivaAnual);
        BigDecimal valorFinal = Financeiro.calcularValorFinal(valorInicial, prazoMeses, taxaEfetivaMensal);

        return new ResultadoSimulacaoResponse(valorFinal, taxaEfetivaAnual, prazoMeses);
    }

    @Transactional
    public void gravarSimulacao(SimularInvestimentoRequest request, SimularInvestimentoResponse response){
        Simulacao simulacao = mapper.toEntity(request, response);
        simulacao.setCliente(new Cliente(request.getClienteId()));
        simulacao.setProduto(new Produto(response.getProdutoValidado().getId()));
        repository.persist(simulacao);
    }

    public List<SimulacaoResponse> buscarTodos(){
        List<Simulacao> simulacoes = repository.findAll().list();
        return mapper.toListDTO(simulacoes);
    }
}
