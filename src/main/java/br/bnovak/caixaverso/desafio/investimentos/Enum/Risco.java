package br.bnovak.caixaverso.desafio.investimentos.Enum;

public enum Risco {

    BAIXO("Baixo"),
    MODERADO("Moderado"),
    ALTO("Alto");

    private final String descricao;

    Risco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
