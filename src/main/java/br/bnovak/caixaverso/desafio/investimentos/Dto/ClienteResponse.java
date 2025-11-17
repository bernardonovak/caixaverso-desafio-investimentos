package br.bnovak.caixaverso.desafio.investimentos.Dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
        description = "Objeto de Resposta do Perfil do Cliente",
        name = "clientePerfilResponse",
        type = SchemaType.OBJECT
)
public class ClienteResponse {

    @Schema(
            description = "ID do cliente",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer id;

    @Schema(
            description = "Perfil",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String perfil;

    @Schema(
            description = "Pontuação",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer pontuacao;

//    @Schema(
//            description = "Descrição do Perfil",
//            implementation = String.class,
//            type = SchemaType.STRING
//    )
//    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
}
