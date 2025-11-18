package br.bnovak.caixaverso.desafio.investimentos.Enum;

import java.util.Arrays;

public enum PerfilRisco {

    BAIXO("Baixo", "Conservador", "Perfil voltado à segurança, com foco na preservação do capital e baixo nível de risco."),
    MODERADO("Médio", "Moderado", "Perfil equilibrado entre segurança e rentabilidade."),
    ALTO("Alto", "Agressivo","Perfil arrojado, com foco em alta rentabilidade e maior tolerância ao risco.");

    private final String risco;
    private final String perfil;
    private final String descricao;

    PerfilRisco(String risco, String perfil, String descricao) {
        this.risco = risco;
        this.perfil = perfil;
        this.descricao = descricao;
    }

    public String getRisco() {
        return risco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPerfil() {
        return perfil;
    }

    public static PerfilRisco buscarPorRisco(String risco) {
        return Arrays.stream(PerfilRisco.values())
                .filter(r -> r.getRisco().equalsIgnoreCase(risco))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Risco inválido!"));
    }

    public static PerfilRisco buscarPorPerfil(String perfil) {
        return Arrays.stream(PerfilRisco.values())
                .filter(r -> r.getPerfil().equalsIgnoreCase(perfil))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Perfil inválido!"));
    }
}
