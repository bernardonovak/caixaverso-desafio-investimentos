package br.bnovak.caixaverso.desafio.investimentos.Enum;

import java.util.Arrays;

public enum Risco {

    BAIXO("Baixo"),
    MODERADO("Médio"),
    ALTO("Alto");

    private final String risco;

    Risco(String risco) {
        this.risco = risco;
    }

    public String getRisco() {
        return risco;
    }

    public static Risco buscarPorNome(String risco) {
        return Arrays.stream(Risco.values())
                .filter(r -> r.getRisco().equalsIgnoreCase(risco))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Risco inválido!"));
    }
}
