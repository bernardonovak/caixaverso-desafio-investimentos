package br.bnovak.caixaverso.desafio.investimentos;

import br.bnovak.caixaverso.desafio.investimentos.Dto.InvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Investimento;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.InvestimentoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.InvestimentoMapperImpl;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.InvestimentoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Services.InvestimentoService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class InvestimentoServiceTest {

    @Mock
    InvestimentoRepository repository;

    @Spy
    InvestimentoMapper mapper = new InvestimentoMapperImpl();

    @InjectMocks
    InvestimentoService service;

    @Test
    void deveRetornarListaDeInvestimentosDoCliente() throws NaoEncontradoException {
        Cliente cliente = new Cliente(1);
        List<Investimento> investimentos = getMockListaInvestimentos();
        Mockito.when(repository.buscarPorCliente(Mockito.any(Cliente.class)))
                .thenReturn(investimentos);

        List<InvestimentoResponse> resultado = service.buscarInvestimentosCliente(cliente.getId());

        Mockito.verify(mapper).toListDTO(investimentos);
        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoTemInvestimentos() throws NaoEncontradoException {
        Cliente cliente = new Cliente(3);
        List<Investimento> investimentos = List.of();
        Mockito.when(repository.buscarPorCliente(Mockito.any(Cliente.class)))
                .thenReturn(investimentos);

        NaoEncontradoException retorno = Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.buscarInvestimentosCliente(cliente.getId());
        });
        Assertions.assertEquals("Nenhum investimento encontrado para o cliente.", retorno.getMessage());
    }

    private List<Investimento> getMockListaInvestimentos(){
        Cliente cliente = new Cliente(1, Perfil.CONSERVADOR, 10);
        Produto produto1 = new Produto(1, "CDB Caixa 2027", TipoProduto.CDB, new BigDecimal("0.12"), Risco.BAIXO);
        Produto produto2 = new Produto(2, "Fundo Moderado", TipoProduto.FUNDO, new BigDecimal("0.15"), Risco.MEDIO);

        Investimento i1 = new Investimento(1, cliente, produto1, new BigDecimal(10000), new BigDecimal(10100), 12, Instant.parse("2025-10-30T19:41:40.806260100Z"));
        Investimento i2 = new Investimento(2, cliente, produto2, new BigDecimal(10000), new BigDecimal(10250), 36, Instant.parse("2025-11-05T19:45:40.806260100Z"));

        return List.of(i1, i2);
    }

}
