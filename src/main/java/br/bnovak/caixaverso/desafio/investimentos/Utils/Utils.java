package br.bnovak.caixaverso.desafio.investimentos.Utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Utils {

    public static LocalDate converteInstantParaData(Instant data){
        return data.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
