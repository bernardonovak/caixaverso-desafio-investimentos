package br.bnovak.caixaverso.desafio.investimentos.Mappers;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ProdutoResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Produto;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import br.bnovak.caixaverso.desafio.investimentos.Enum.TipoProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface ProdutoMapper {

    @Mapping(source = "tipoProduto", target = "tipo")
    ProdutoResponse toDTO(Produto produto);

    List<ProdutoResponse> toListDTO(List<Produto> produtos);

    default String map(TipoProduto tipoProduto) {
        return tipoProduto != null ? tipoProduto.getNome() : null;
    }

    default String map(Risco risco) {
        return risco != null ? risco.getNome() : null;
    }

}
