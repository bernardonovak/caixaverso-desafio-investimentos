package br.bnovak.caixaverso.desafio.investimentos.Utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate ConverteInstantParaData(Instant data){
        return data.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
