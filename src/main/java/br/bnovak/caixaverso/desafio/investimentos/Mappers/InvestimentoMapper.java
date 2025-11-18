package br.bnovak.caixaverso.desafio.investimentos.Mappers;

import br.bnovak.caixaverso.desafio.investimentos.Dto.InvestimentoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Investimento;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Utils.Utils;
import org.hibernate.dialect.function.StringFunction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "jakarta")
public interface InvestimentoMapper {

    @Mapping(source = "produto", target = "tipo", qualifiedByName = "mapTipoProduto")
    @Mapping(source = "valorAtual", target = "valor")
    @Mapping(source = "produto", target = "rentabilidade", qualifiedByName = "mapRentabilidade")
    @Mapping(source = "dataInvestimento", target = "data", qualifiedByName = "mapData")
    InvestimentoResponse toDTO(Investimento investimento);

    List<InvestimentoResponse> toListDTO(List<Investimento> investimentos);

    @Named("mapTipoProduto")
    default String mapTipoProduto(Produto produto) {
        return produto != null ? produto.getTipoProduto().getNome() : null;
    }

    @Named("mapRentabilidade")
    default BigDecimal mapRentabilidade(Produto produto) {
        return produto != null ? produto.getRentabilidade() : null;
    }

    @Named("mapData")
    default String mapData(Instant dataInvestimento) {
        return dataInvestimento != null ? Utils.ConverteInstantParaData(dataInvestimento) : null;
    }
}
