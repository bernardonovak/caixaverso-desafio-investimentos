package br.bnovak.caixaverso.desafio.investimentos.Utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Financeiro {

    private static final MathContext MC = new MathContext(15, RoundingMode.HALF_UP);

    public static BigDecimal calcularTaxaEfetivaMensal(BigDecimal taxaAnual) {
        BigDecimal base = BigDecimal.ONE.add(taxaAnual, MC);
        BigDecimal expoente = BigDecimal.ONE.divide(BigDecimal.valueOf(12), MC);
        BigDecimal potencia = bigDecimalPow(base, expoente, MC);

        return potencia.subtract(BigDecimal.ONE, MC)
                .setScale(6, RoundingMode.HALF_UP);
    }

    public static BigDecimal calcularValorFinal(BigDecimal valorInicial, Integer prazoMeses, BigDecimal taxaEfetivaMensal) {

        BigDecimal fator = BigDecimal.ONE.add(taxaEfetivaMensal, MC);

        BigDecimal montante = valorInicial.multiply(fator.pow(prazoMeses, MC), MC);

        return montante.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal bigDecimalPow(BigDecimal base, BigDecimal exponent, MathContext mc) {
        BigDecimal ln = BigDecimal.valueOf(Math.log(base.doubleValue()));
        BigDecimal mult = exponent.multiply(ln, mc);

        double result = Math.exp(mult.doubleValue());

        return new BigDecimal(result, mc);
    }
}
