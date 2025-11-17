package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ClienteResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.ClienteMapper;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository repository;

    @Inject
    ClienteMapper mapper;

    public ClienteResponse buscarPorID(Integer id) throws NaoEncontradoException {
        Cliente cliente = obterClientePorID(id);
        return mapper.toDTO(cliente);
    }

    private Cliente obterClientePorID(Integer id) throws NaoEncontradoException {
        return repository.findByIdOptional(Long.valueOf(id)).orElseThrow(()-> new NaoEncontradoException("Cliente não encontrado"));
    }

    public List<ClienteResponse> buscarTodos(){
        List<Cliente> clientes = repository.findAll().list();
        return mapper.toListDTO(clientes);
    }
}
