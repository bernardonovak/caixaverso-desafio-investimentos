package br.bnovak.caixaverso.desafio.investimentos.Mappers;

import br.bnovak.caixaverso.desafio.investimentos.Dto.ClienteResponse;
import br.bnovak.caixaverso.desafio.investimentos.Entities.Cliente;
import br.bnovak.caixaverso.desafio.investimentos.Enum.Risco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface ClienteMapper {

    @Mapping(source = "perfil", target = "perfil", qualifiedByName = "mapNome")
    @Mapping(source = "perfil", target = "descricao", qualifiedByName = "mapDescricao")
    ClienteResponse toDTO(Cliente cliente);

    List<ClienteResponse> toListDTO(List<Cliente> clientes);

    @Named("mapNome")
    default String mapNome(Risco perfil) {
        return perfil != null ? perfil.getNome() : null;
    }

    @Named("mapDescricao")
    default String mapDescricao(Risco perfil) {
        return perfil != null ? perfil.getDescricao() : null;
    }


}
