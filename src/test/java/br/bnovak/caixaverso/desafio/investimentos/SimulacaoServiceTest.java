package br.bnovak.caixaverso.desafio.investimentos;

import br.bnovak.caixaverso.desafio.investimentos.Dto.*;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Simulacao;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.SimulacaoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.SimulacaoMapperImpl;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ClienteRepository;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ProdutoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.SimulacaoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Services.ClienteService;
import br.bnovak.caixaverso.desafio.investimentos.Services.ProdutoService;
import br.bnovak.caixaverso.desafio.investimentos.Services.SimulacaoService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SimulacaoServiceTest {

    @Mock
    SimulacaoRepository repository;

    @Mock
    ClienteService clienteService;

    @Mock
    ProdutoService produtoService;

    @Spy
    SimulacaoMapper mapper = new SimulacaoMapperImpl();

    @InjectMocks
    SimulacaoService service;

    @Test
    void deveSimularInvestimentoComSucesso() throws NaoEncontradoException {
        // Dados de entrada
        SimularInvestimentoRequest request = getSimulacaoRequestValido();
        // Mocks
        ClienteResponse cliente = getMockCliente();
        ProdutoResponse produto = getMockProduto();
        Mockito.when(clienteService.buscarPorID(request.getClienteId())).thenReturn(cliente);
        Mockito.when(produtoService.calcularRiscoPorPerfil(Mockito.any(Perfil.class))).thenReturn(Risco.MEDIO);
        Mockito.when(produtoService.buscarProdutoAdequado(Mockito.any(TipoProduto.class), Mockito.any(Risco.class))).thenReturn(produto);
        //Mock da persistencia
        Mockito.doNothing().when(repository).persist(Mockito.any(Simulacao.class));

        SimularInvestimentoResponse resultado = service.simularInvestimento(request);

        Assertions.assertNotNull(resultado);
        Assertions.assertInstanceOf(ProdutoResponse.class, resultado.getProdutoValidado());
        Assertions.assertEquals(produto.getId(), resultado.getProdutoValidado().getId());
        Assertions.assertEquals(produto.getNome(), resultado.getProdutoValidado().getNome());
        Assertions.assertEquals(produto.getTipo(), resultado.getProdutoValidado().getTipo());
        Assertions.assertEquals(produto.getRentabilidade(), resultado.getProdutoValidado().getRentabilidade());
        Assertions.assertEquals(produto.getRisco(), resultado.getProdutoValidado().getRisco());
        Assertions.assertInstanceOf(ResultadoSimulacaoResponse.class, resultado.getResultadoSimulacao());
        Assertions.assertEquals(produto.getRentabilidade(), resultado.getResultadoSimulacao().getRentabilidadeEfetiva());
        Assertions.assertEquals(request.getPrazoMeses(), resultado.getResultadoSimulacao().getPrazoMeses());
        Assertions.assertNotNull(resultado.getDataSimulacao());

        Mockito.verify(repository).persist(Mockito.any(Simulacao.class));
    }

    @Test
    void deveLancarExcecaoSeClienteNaoEncontrado() throws NaoEncontradoException {
        SimularInvestimentoRequest request = getSimulacaoRequestValido();

        Mockito.when(clienteService.buscarPorID(request.getClienteId()))
                .thenThrow(new NaoEncontradoException("Cliente não encontrado"));

        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.simularInvestimento(request);
        });
    }

    @Test
    void deveLancarExcecaoSeProdutoNaoEncontrado() throws NaoEncontradoException {
        SimularInvestimentoRequest request = getSimulacaoRequestValido();

        ClienteResponse cliente = getMockCliente();

        Mockito.when(clienteService.buscarPorID(cliente.getId())).thenReturn(cliente);
        Mockito.when(produtoService.calcularRiscoPorPerfil(Mockito.any(Perfil.class))).thenReturn(Risco.ALTO);
        Mockito.when(produtoService.buscarProdutoAdequado(Mockito.any(TipoProduto.class), Mockito.any(Risco.class)))
                .thenThrow(new NaoEncontradoException("Produto não encontrado"));

        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.simularInvestimento(request);
        });
    }

    @Test
    void devePersistirSimulacaoCorretamente() {
        // Dados simulados
        SimularInvestimentoRequest request = getSimulacaoRequestValido();
        ProdutoResponse produto = getMockProduto();
        ResultadoSimulacaoResponse resultado = getMockResultadoSimulacao();
        SimularInvestimentoResponse response = new SimularInvestimentoResponse(produto, resultado);
        // Captura do objeto persistido
        ArgumentCaptor<Simulacao> captor = ArgumentCaptor.forClass(Simulacao.class);
        // Simula persistência
        Mockito.doNothing().when(repository).persist(Mockito.any(Simulacao.class));

        service.gravarSimulacao(request, response);

        // Verifica persistência
        Mockito.verify(repository).persist(captor.capture());
        Simulacao simulacaoPersistida = captor.getValue();

        // Asserts
        Assertions.assertEquals(request.getClienteId(), simulacaoPersistida.getCliente().getId());
        Assertions.assertEquals(produto.getId(), simulacaoPersistida.getProduto().getId());
        Assertions.assertEquals(response.getResultadoSimulacao().getValorFinal(), simulacaoPersistida.getValorFinal());
        Assertions.assertEquals(response.getResultadoSimulacao().getPrazoMeses(), simulacaoPersistida.getPrazoMeses());
        Assertions.assertNotNull(simulacaoPersistida.getDataSimulacao());
    }

    @Test
    void deveRetornarListaDeSimulacoes() {
        PanacheQuery<Simulacao> queryMock = Mockito.mock(PanacheQuery.class);
        List<Simulacao> simulacoes = getMockListaSimulacao();
        Mockito.when(queryMock.list()).thenReturn(simulacoes);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<SimulacaoResponse> resultado = service.buscarTodos();

        Mockito.verify(mapper).toListDTO(simulacoes);
        Assertions.assertEquals(3, resultado.size());
    }

    @Test
    void deveRetornarListaVazia() {
        PanacheQuery<Simulacao> queryMock = Mockito.mock(PanacheQuery.class);
        List<Simulacao> simulacoes = List.of();
        Mockito.when(queryMock.list()).thenReturn(simulacoes);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<SimulacaoResponse> resultado = service.buscarTodos();

        Mockito.verify(mapper).toListDTO(simulacoes);
        Assertions.assertEquals(0, resultado.size());
    }

    @Test
    void deveAgruparSimulacoesPorProdutoEDiaComMediaCorreta() {
        PanacheQuery<Simulacao> queryMock = Mockito.mock(PanacheQuery.class);
        List<Simulacao> simulacoes = getMockListaSimulacao();
        Mockito.when(queryMock.list()).thenReturn(simulacoes);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<SimulacaoPorProdutoDiaDTO> resultado = service.buscarPorProdutoEDia();

        Assertions.assertEquals(2, resultado.size());

        SimulacaoPorProdutoDiaDTO produto1 = resultado.stream()
                .filter(r -> r.getProduto().equals("CDB Moderado 2026"))
                .findFirst().orElseThrow();

        Assertions.assertEquals(2, produto1.getQuantidadeSimulacoes());
        Assertions.assertEquals(new BigDecimal("1050.00"), produto1.getMediaValorFinal());

        SimulacaoPorProdutoDiaDTO produto2 = resultado.stream()
                .filter(r -> r.getProduto().equals("Fundo Moderado"))
                .findFirst().orElseThrow();

        Assertions.assertEquals(1, produto2.getQuantidadeSimulacoes());
        Assertions.assertEquals(new BigDecimal("2000.00"), produto2.getMediaValorFinal());
    }


    private SimularInvestimentoRequest getSimulacaoRequestValido(){
        return new SimularInvestimentoRequest(1, new BigDecimal("1000.00"), 12, TipoProduto.CDB);
    }

    private ClienteResponse getMockCliente(){
        return new ClienteResponse(1, Perfil.MODERADO.getPerfil(), 50, Perfil.MODERADO.getDescricao());
    }

    private ProdutoResponse getMockProduto(){
        return new ProdutoResponse(2, "CDB Moderado 2026", TipoProduto.CDB.getNome(), new BigDecimal("0.14"), Risco.MEDIO.getNome());
    }

    private ResultadoSimulacaoResponse getMockResultadoSimulacao(){
        return new ResultadoSimulacaoResponse(new BigDecimal("1120.00"), new BigDecimal("0.14"), 12);
    }

    private List<Simulacao> getMockListaSimulacao(){
        Simulacao s1 = new Simulacao(new Cliente(1), new Produto(1,"CDB Moderado 2026", TipoProduto.CDB, new BigDecimal("0.14"), Risco.MEDIO), new BigDecimal(10000), new BigDecimal(1000), 12, Instant.parse("2025-10-30T19:41:40.806260100Z"));
        Simulacao s2 = new Simulacao(new Cliente(1), new Produto(1,"CDB Moderado 2026", TipoProduto.CDB, new BigDecimal("0.14"), Risco.MEDIO), new BigDecimal(20000), new BigDecimal(1100), 12, Instant.parse("2025-10-30T19:45:40.806260100Z"));
        Simulacao s3 = new Simulacao(new Cliente(1), new Produto(2,"Fundo Moderado", TipoProduto.FUNDO, new BigDecimal("0.15"), Risco.MEDIO), new BigDecimal(30000), new BigDecimal(2000), 12, Instant.parse("2025-10-30T19:50:40.806260100Z"));

        return List.of(s1, s2, s3);
    }

}
