package br.bnovak.caixaverso.desafio.investimentos;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ClienteResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.ClienteMapper;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.ClienteMapperImpl;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ClienteRepository;
import br.bnovak.caixaverso.desafio.investimentos.Services.ClienteService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    ClienteRepository repository;

    @Spy
    ClienteMapper mapper = new ClienteMapperImpl();

    @InjectMocks
    ClienteService service;

    @Test
    void deveRetornarClientePorId() throws NaoEncontradoException{
        Cliente cliente = getMockClientePerfilConservadorValido();
        Integer id = cliente.getId();
        Mockito.when(repository.findByIdOptional(Long.valueOf(id))).thenReturn(Optional.of(cliente));

        ClienteResponse resultado = service.buscarPorID(id);

        Mockito.verify(mapper).toDTO(cliente);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(cliente.getPerfil().getPerfil(), resultado.getPerfil());
        Assertions.assertEquals(cliente.getPontuacao(), resultado.getPontuacao());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExiste() {
        Integer id = 99;
        Mockito.when(repository.findByIdOptional(Long.valueOf(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.buscarPorID(id);
        });
    }

    @Test
    void deveRetornarListaDeClientes() {
        PanacheQuery<Cliente> queryMock = Mockito.mock(PanacheQuery.class);
        List<Cliente> clientes = getMockListaClientes();
        Mockito.when(queryMock.list()).thenReturn(clientes);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ClienteResponse> resultado = service.buscarTodos();

        Mockito.verify(mapper).toListDTO(clientes);
        Assertions.assertEquals(3, resultado.size());
        Assertions.assertEquals(Perfil.CONSERVADOR.getPerfil(), resultado.get(0).getPerfil());
    }

    @Test
    void deveRetornarListaVazia() {
        PanacheQuery<Cliente> queryMock = Mockito.mock(PanacheQuery.class);
        List<Cliente> clientes = List.of();
        Mockito.when(queryMock.list()).thenReturn(clientes);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ClienteResponse> resultado = service.buscarTodos();

        Mockito.verify(mapper).toListDTO(clientes);
        Assertions.assertEquals(0, resultado.size());
    }


    private Cliente getMockClientePerfilConservadorValido(){
        return new Cliente(1, Perfil.CONSERVADOR, 10);
    }

    private Cliente getMockClientePerfilModeradoValido(){
        return new Cliente(2, Perfil.MODERADO, 50);
    }

    private Cliente getMockClientePerfilAgressivoValido(){
        return new Cliente(3, Perfil.AGRESSIVO, 90);
    }

    private List<Cliente> getMockListaClientes(){
        Cliente cliente1 = getMockClientePerfilConservadorValido();
        Cliente cliente2 = getMockClientePerfilModeradoValido();
        Cliente cliente3 = getMockClientePerfilAgressivoValido();

        return List.of(cliente1, cliente2, cliente3);
    }
}
