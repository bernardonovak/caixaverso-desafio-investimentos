package br.bnovak.caixaverso.desafio.investimentos;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ProdutoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.ProdutoMapper;
import br.bnovak.caixaverso.desafio.investimentos.Mappers.ProdutoMapperImpl;
import br.bnovak.caixaverso.desafio.investimentos.Repositories.ProdutoRepository;
import br.bnovak.caixaverso.desafio.investimentos.Services.ProdutoService;
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
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    ProdutoRepository repository;

    @Spy
    ProdutoMapper mapper = new ProdutoMapperImpl();

    @InjectMocks
    ProdutoService service;

    @Test
    void deveRetornarListaProdutosValido(){
        PanacheQuery<Produto> queryMock = Mockito.mock(PanacheQuery.class);
        List<Produto> produtos = getMockListaProdutos();
        Mockito.when(queryMock.list()).thenReturn(produtos);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ProdutoResponse> resultado = service.buscarTodos();

        Mockito.verify(mapper).toListDTO(produtos);
        Assertions.assertEquals(3, resultado.size());
    }

    @Test
    void deveRetornarListaProdutosVazia(){
        PanacheQuery<Produto> queryMock = Mockito.mock(PanacheQuery.class);
        List<Produto> produtos = List.of();
        Mockito.when(queryMock.list()).thenReturn(produtos);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ProdutoResponse> resultado = service.buscarTodos();

        Mockito.verify(mapper).toListDTO(produtos);
        Assertions.assertEquals(0, resultado.size());
    }

    @Test
    void deveRetornarProdutoPorId() throws NaoEncontradoException {
        Produto produto = getMockProdutoRiscoAltoValido();
        Integer id = produto.getId();
        Mockito.when(repository.findByIdOptional(Long.valueOf(id))).thenReturn(Optional.of(produto));

        ProdutoResponse resultado = service.buscarPorID(id);

        Mockito.verify(mapper).toDTO(produto);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produto.getId(), resultado.getId());
        Assertions.assertEquals(produto.getNome(), resultado.getNome());
        Assertions.assertEquals(produto.getTipoProduto().getNome(), resultado.getTipo());
        Assertions.assertEquals(produto.getRentabilidade(), resultado.getRentabilidade());
        Assertions.assertEquals(produto.getRisco().getNome(), resultado.getRisco());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExiste() {
        Integer id = 99;
        Mockito.when(repository.findByIdOptional(Long.valueOf(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.buscarPorID(id);
        });
    }

    @Test
    void deveRetornarRiscoBaixoParaPerfilConservador() throws NaoEncontradoException{
        Perfil perfil = Perfil.CONSERVADOR;

        Risco resultado = service.calcularRiscoPorPerfil(perfil);

        Assertions.assertEquals(Risco.BAIXO, resultado);
    }

    @Test
    void deveRetornarRiscoMedioParaPerfilModerado() throws NaoEncontradoException{
        Perfil perfil = Perfil.MODERADO;

        Risco resultado = service.calcularRiscoPorPerfil(perfil);

        Assertions.assertEquals(Risco.MEDIO, resultado);
    }

    @Test
    void deveRetornarRiscoAltoParaPerfilAgressivo() throws NaoEncontradoException{
        Perfil perfil = Perfil.AGRESSIVO;

        Risco resultado = service.calcularRiscoPorPerfil(perfil);

        Assertions.assertEquals(Risco.ALTO, resultado);
    }

    @Test
    void deveLancaoExcessaoQuandoPerfilNaoEncontrado() throws NaoEncontradoException{
        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.calcularRiscoPorPerfil(null);
        });
    }

    @Test
    void deveRetornarProdutoAdequadoValido() throws NaoEncontradoException {
        TipoProduto tipo = TipoProduto.FUNDO;
        Risco risco = Risco.MEDIO;
        Produto produto = getMockProdutoRiscoMedioValido();
        Mockito.when(repository.buscarPorTipoERisco(tipo, risco)).thenReturn(List.of(produto));

        ProdutoResponse resultado = service.buscarProdutoAdequado(tipo, risco);

        Mockito.verify(mapper).toDTO(produto);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(produto.getId(), resultado.getId());
        Assertions.assertEquals(produto.getNome(), resultado.getNome());
        Assertions.assertEquals(produto.getTipoProduto().getNome(), resultado.getTipo());
        Assertions.assertEquals(produto.getRentabilidade(), resultado.getRentabilidade());
        Assertions.assertEquals(produto.getRisco().getNome(), resultado.getRisco());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        TipoProduto tipo = TipoProduto.CDB;
        Risco risco = Risco.ALTO;

        List<Produto> produtos = List.of();

        Mockito.when(repository.buscarPorTipoERisco(tipo, risco)).thenReturn(produtos);

        NaoEncontradoException ex = Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.buscarProdutoAdequado(tipo, risco);
        });
    }

    @Test
    void deveRecomendarProdutosParaPerfilConservador() throws NaoEncontradoException {
        PanacheQuery<Produto> queryMock = Mockito.mock(PanacheQuery.class);
        List<Produto> produtos = getMockListaProdutos();
        Mockito.when(queryMock.list()).thenReturn(produtos);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ProdutoResponse> recomendados = service.recomendarProdutos(Perfil.CONSERVADOR.getPerfil());

        Assertions.assertFalse(recomendados.isEmpty());
        Assertions.assertTrue(recomendados.stream().allMatch(p ->
                p.getRentabilidade().compareTo(Perfil.CONSERVADOR.getParametroRentabilidade()) <= 0 &&
                        Risco.buscarPorNome(p.getRisco()) == Risco.BAIXO
        ));
    }

    @Test
    void deveRecomendarProdutosParaPerfilModerado() throws NaoEncontradoException {
        PanacheQuery<Produto> queryMock = Mockito.mock(PanacheQuery.class);
        List<Produto> produtos = getMockListaProdutos();
        Mockito.when(queryMock.list()).thenReturn(produtos);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ProdutoResponse> recomendados = service.recomendarProdutos(Perfil.MODERADO.getPerfil());

        Assertions.assertFalse(recomendados.isEmpty());
        Assertions.assertTrue(recomendados.stream().allMatch(p ->
                p.getRentabilidade().compareTo(Perfil.MODERADO.getParametroRentabilidade()) <= 0 &&
                        Risco.buscarPorNome(p.getRisco()) != Risco.ALTO
        ));
    }

    @Test
    void deveRecomendarProdutosParaPerfilAgressivo() throws NaoEncontradoException {
        PanacheQuery<Produto> queryMock = Mockito.mock(PanacheQuery.class);
        List<Produto> produtos = getMockListaProdutos();
        Mockito.when(queryMock.list()).thenReturn(produtos);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        List<ProdutoResponse> recomendados = service.recomendarProdutos(Perfil.AGRESSIVO.getPerfil());

        Assertions.assertFalse(recomendados.isEmpty());
        Assertions.assertTrue(recomendados.stream().allMatch(p ->
                p.getRentabilidade().compareTo(Perfil.AGRESSIVO.getParametroRentabilidade()) >= 0
        ));
    }

    @Test
    void deveLancarExcecaoParaPerfilInvalido() {
        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.recomendarProdutos(null);
        });
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarProduto() {
        PanacheQuery<Produto> queryMock = Mockito.mock(PanacheQuery.class);
        List<Produto> produtos = List.of();
        Mockito.when(queryMock.list()).thenReturn(produtos);
        Mockito.when(repository.findAll()).thenReturn(queryMock);

        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.recomendarProdutos(Perfil.AGRESSIVO.getPerfil());
        });
    }

    private List<Produto> getMockListaProdutos(){
        Produto produto1 = getMockProdutoRiscoBaixoValido();
        Produto produto2 = getMockProdutoRiscoMedioValido();
        Produto produto3 = getMockProdutoRiscoAltoValido();

        return List.of(produto1, produto2, produto3);
    }

    private Produto getMockProdutoRiscoBaixoValido(){
        return new Produto(1, "CDB Caixa 2027", TipoProduto.CDB, new BigDecimal("0.12"), Risco.BAIXO);
    }

    private Produto getMockProdutoRiscoMedioValido(){
        return new Produto(2, "CDB Moderado 2026", TipoProduto.CDB, new BigDecimal("0.14"), Risco.MEDIO);
    }

    private Produto getMockProdutoRiscoAltoValido(){
        return new Produto(3, "Fundo XPTO", TipoProduto.FUNDO, new BigDecimal("0.18"), Risco.ALTO);
    }

}
