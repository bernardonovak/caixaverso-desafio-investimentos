package br.bnovak.caixaverso.desafio.investimentos.Enum;

import java.util.Arrays;

public enum Perfil {

    CONSERVADOR("Conservador", "Perfil voltado à segurança, com foco na preservação do capital e baixo nível de risco."),
    MODERADO("Moderado", "Perfil equilibrado entre segurança e rentabilidade."),
    AGRESSIVO("Agressivo","Perfil arrojado, com foco em alta rentabilidade e maior tolerância ao risco.");

    private final String perfil;
    private final String descricao;

    Perfil(String perfil, String descricao) {
        this.perfil = perfil;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPerfil() {
        return perfil;
    }

    public static Perfil buscarPorPerfil(String perfil) {
        return Arrays.stream(Perfil.values())
                .filter(r -> r.getPerfil().equalsIgnoreCase(perfil))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Perfil inválido!"));
    }
}
