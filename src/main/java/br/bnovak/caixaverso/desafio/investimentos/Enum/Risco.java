package br.bnovak.caixaverso.desafio.investimentos.Enum;

import java.util.Arrays;

public enum Risco {

    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private String nome;

    Risco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static Risco buscarPorNome(String nome) {
        return Arrays.stream(Risco.values())
                .filter(r -> r.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Risco inválido!"));
    }
}
