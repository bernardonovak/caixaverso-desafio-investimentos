package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Dto.InvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Dto.SimulacaoPorProdutoDiaDTO;
import br.bnovak.caixaverso.desafio.investimentos.Dto.SimulacaoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Investimento;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.InvestimentoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.InvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class InvestimentoService {

    @Inject
    InvestimentoRepository repository;

    @Inject
    InvestimentoMapper mapper;

    @Inject
    ClienteService serviceCliente;

    public List<InvestimentoResponse> buscarInvestimentosCliente(Integer clienteId){
        List<Investimento> investimentos = repository.buscarPorCliente(new Cliente(clienteId));
        return mapper.toListDTO(investimentos);
    }
}
