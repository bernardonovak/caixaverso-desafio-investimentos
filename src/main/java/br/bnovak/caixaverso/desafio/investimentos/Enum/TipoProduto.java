package br.bnovak.caixaverso.desafio.investimentos.Enum;

public enum TipoProduto {

    CDB("CDB"),
    FUNDO("Fundo"),
    FUNDO_MULTIMERCADO("Fundo Multimercado");

    private final String nome;

    TipoProduto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
