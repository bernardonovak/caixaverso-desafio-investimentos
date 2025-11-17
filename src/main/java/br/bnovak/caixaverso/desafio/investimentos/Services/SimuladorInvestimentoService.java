package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Dto.*;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Utils.Financeiro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@ApplicationScoped
public class SimuladorInvestimentoService {

    @Inject
    ProdutoService produtoService;

    @Inject
    ClienteService clienteService;

    public SimularInvestimentoResponse simularInvestimento(SimularInvestimentoRequest request) throws NaoEncontradoException {
        //buscar Cliente para obter o perfil de risco do cliente
        ClienteResponse cliente = clienteService.buscarPorID(request.getClienteId());
        System.out.println(cliente.getPerfil());
        Risco perfil = Risco.buscarPorNome(cliente.getPerfil());
        ProdutoResponse produtoValidado = produtoService.buscarProdutoAdequado(request.getTipoProduto(), perfil);

        ResultadoSimulacaoResponse simulacao = simularResultadoInvestimento(request, produtoValidado);

        SimularInvestimentoResponse simulacaoResponse = new SimularInvestimentoResponse(produtoValidado, simulacao);

        //criar metodo privado para gravar na tabela de simulacoes realizadas
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
}
