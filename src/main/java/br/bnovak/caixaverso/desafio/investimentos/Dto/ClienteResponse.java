package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
        description = "Objeto de Resposta do Perfil do Cliente",
        name = "clientePerfilResponse",
        type = SchemaType.OBJECT
)
public final class ClienteResponse {

    @Schema(
            description = "ID do cliente",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer id;

    @Schema(
            description = "Perfil",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private final String perfil;

    @Schema(
            description = "Pontuação",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private final Integer pontuacao;

    @Schema(
            description = "Descrição do Perfil",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private final String descricao;


    public ClienteResponse(Integer id, String perfil, Integer pontuacao, String descricao) {
        this.id = id;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getPerfil() {
        return perfil;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public String getDescricao() {
        return descricao;
    }
}
