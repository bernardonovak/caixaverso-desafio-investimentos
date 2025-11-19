package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Dto.*;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Simulacao;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.SimulacaoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.SimulacaoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Utils.Financeiro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        ClienteResponse cliente = clienteService.buscarPorID(request.getClienteId());
        Perfil perfil = Perfil.buscarPorPerfil(cliente.getPerfil());
//        busca produto com maior rentabilidade de acordo com o perfil calcularRiscoPorPerfil();
        Risco risco = produtoService.calcularRiscoPorPerfil(perfil);

        ProdutoResponse produtoValidado = produtoService.buscarProdutoAdequado(request.getTipoProduto(), risco);
        ResultadoSimulacaoResponse simulacao = simularResultadoInvestimento(request, produtoValidado);

        SimularInvestimentoResponse simulacaoResponse = new SimularInvestimentoResponse(produtoValidado, simulacao);

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

    public List<SimulacaoPorProdutoDiaDTO> buscarPorProdutoEDia(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        List<SimulacaoResponse> simulacoes = buscarTodos();

        return simulacoes.stream()
                .collect(Collectors.groupingBy(simulacao -> {
                    String produto = simulacao.getProduto();
                    LocalDate data = simulacao.getDataSimulacao()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return produto + "|" + data.format(formatter);
                }))
                .entrySet()
                .stream()
                .map(
                        resumo -> {
                            String[] chave = resumo.getKey().split("\\|");
                            String produto = chave[0];
                            LocalDate data = LocalDate.parse(chave[1]);
                            List<SimulacaoResponse> grupo = resumo.getValue();
                            Integer quantidade = grupo.size();
                            BigDecimal media = grupo.stream()
                                    .map(SimulacaoResponse::getValorFinal)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                                    .divide(BigDecimal.valueOf(quantidade), 2, RoundingMode.HALF_UP);
                            return new SimulacaoPorProdutoDiaDTO(produto, data, quantidade, media);
                        }
                ).toList();
    }
}
