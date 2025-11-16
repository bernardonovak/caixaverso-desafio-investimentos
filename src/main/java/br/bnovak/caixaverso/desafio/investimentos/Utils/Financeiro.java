package br.bnovak.caixaverso.desafio.investimentos.Utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Financeiro {

    public static BigDecimal calcularTaxaEfetivaMensal(BigDecimal taxaAnual){
        BigDecimal taxaAnualDecimal = taxaAnual.divide(BigDecimal.valueOf(100));

        BigDecimal base = BigDecimal.ONE.add(taxaAnualDecimal);
        BigDecimal expoente = BigDecimal.valueOf(1.0 / 12.0);
        double resultadoPotencia = Math.pow(base.doubleValue(), expoente.doubleValue());
        BigDecimal taxaMensalDecimal = BigDecimal.valueOf(resultadoPotencia).subtract(BigDecimal.ONE);

        return taxaMensalDecimal.setScale(6, RoundingMode.HALF_UP);
    }

    public static BigDecimal calcularValorFinal(BigDecimal valorInicial, Integer prazoMeses, BigDecimal taxaEfetivaMensal){
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        BigDecimal fator = BigDecimal.ONE.add(taxaEfetivaMensal, mc);
        BigDecimal montante = valorInicial.multiply(fator.pow(prazoMeses, mc), mc);

        return montante.setScale(2, RoundingMode.HALF_UP);
    }
}
