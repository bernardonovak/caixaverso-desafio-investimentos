package br.bnovak.caixaverso.desafio.investimentos.Enum;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoEncontradoException;

import java.math.BigDecimal;
import java.util.Arrays;

public enum Perfil {

    CONSERVADOR("Conservador", "Perfil voltado à segurança, com foco na preservação do capital e baixo nível de risco.", BigDecimal.valueOf(0.12)),
    MODERADO("Moderado", "Perfil equilibrado entre segurança e rentabilidade.", BigDecimal.valueOf(0.15)),
    AGRESSIVO("Agressivo","Perfil arrojado, com foco em alta rentabilidade e maior tolerância ao risco.", BigDecimal.valueOf(0.15));

    private final String perfil;
    private final String descricao;
    private final BigDecimal parametroRentabilidade;

    Perfil(String perfil, String descricao, BigDecimal parametroRentabilidade) {
        this.perfil = perfil;
        this.descricao = descricao;
        this.parametroRentabilidade = parametroRentabilidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPerfil() {
        return perfil;
    }

    public BigDecimal getParametroRentabilidade() {
        return parametroRentabilidade;
    }

    public static Perfil buscarPorPerfil(String perfil) throws NaoEncontradoException {
        if(perfil == null){
            throw new NaoEncontradoException("Perfil Inválido!");
        }
        return Arrays.stream(Perfil.values())
                .filter(r -> r.getPerfil().equalsIgnoreCase(perfil))
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Perfil inválido!"));
    }
}
