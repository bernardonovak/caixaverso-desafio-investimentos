package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Dto.InvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Investimento;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.InvestimentoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.InvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class InvestimentoService {

    @Inject
    InvestimentoRepository repository;

    @Inject
    InvestimentoMapper mapper;

    public List<InvestimentoResponse> buscarInvestimentosCliente(Integer clienteId) throws NaoEncontradoException{
        List<Investimento> investimentos = repository.buscarPorCliente(new Cliente(clienteId));
        if(investimentos.isEmpty()){
            throw new NaoEncontradoException("Nenhum investimento encontrado para o cliente.");
        }
        return mapper.toListDTO(investimentos);
    }
}
