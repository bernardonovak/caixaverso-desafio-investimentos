package br.bnovak.caixaverso.desafio.investimentos.Enum;

public enum TipoProduto {

    CDB("CDB"),
    FUNDO("Fundo"),
    FUNDO_MULTIMERCADO("Fundo Multimercado");

    private final String descricao;

    TipoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
