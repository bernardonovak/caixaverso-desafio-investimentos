package br.bnovak.caixaverso.desafio.investimentos.Mappers;

import br.bnovak.caixaverso.desafio.investimentos.Dto.SimulacaoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Dto.SimularInvestimentoRequest;
import br.bnovak.caixaverso.desafio.investimentos.Dto.SimularInvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Simulacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface SimulacaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "produto", ignore = true)
    @Mapping(source = "request.valor", target = "valorInvestido")
    @Mapping(source = "response.resultadoSimulacao.valorFinal", target = "valorFinal")
    @Mapping(source = "response.resultadoSimulacao.prazoMeses", target = "prazoMeses")
    @Mapping(source = "response.dataSimulacao", target = "dataSimulacao")
    Simulacao toEntity(SimularInvestimentoRequest request, SimularInvestimentoResponse response);

    @Mapping(source = "cliente", target = "clienteId", qualifiedByName = "mapClienteId")
    @Mapping(source = "produto", target = "produto", qualifiedByName = "mapNomeProduto")
    SimulacaoResponse toDTO(Simulacao simulacao);

    List<SimulacaoResponse> toListDTO(List<Simulacao> simulacoes);

    @Named("mapClienteId")
    default Integer mapNome(Cliente cliente) {
        return cliente != null ? cliente.getId() : null;
    }

    @Named("mapNomeProduto")
    default String mapNome(Produto produto) {
        return produto != null ? produto.getNome() : null;
    }

}
