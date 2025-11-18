package br.bnovak.caixaverso.desafio.investimentos.Utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String ConverteInstantParaData(Instant data){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return data.atZone(ZoneId.systemDefault()).format(formatter);
    }
}
