package br.bnovak.caixaverso.desafio.investimentos.Enum;

import java.util.Arrays;

public enum Risco {

    BAIXO("Baixo", "Perfil voltado à segurança, com foco na preservação do capital e baixo nível de risco."),
    MODERADO("Moderado", "Perfil equilibrado entre segurança e rentabilidade."),
    ALTO("Alto", "Perfil arrojado, com foco em alta rentabilidade e maior tolerância ao risco.");

    private final String nome;
    private final String descricao;

    Risco(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Risco buscarPorNome(String nome) {
        return Arrays.stream(Risco.values())
                .filter(r -> r.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nome de risco inválido!"));
    }

}
