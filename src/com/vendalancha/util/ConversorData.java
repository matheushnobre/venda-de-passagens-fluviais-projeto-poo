package com.vendalancha.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConversorData {
    
    public static LocalDateTime strParaLocalDateTime(String str_data, String str_hora) {
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate data = LocalDate.parse(str_data, formatterData);
        LocalTime hora = LocalTime.parse(str_hora, formatterHora);

        return LocalDateTime.of(data, hora);
    }
    
    public static String localDateTimeParaStrSQL(LocalDateTime horario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return horario.format(formatter);
    }
    
    public static String strParaSQL(String str_data){
        DateTimeFormatter formatterDataLida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(str_data, formatterDataLida);
        DateTimeFormatter formatterDataSaida = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return data.format(formatterDataSaida);
    }
    
    public static LocalDateTime strDateTimeParaLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }
    
    public static String strDateTimeParaStrData(LocalDateTime dateTime) {
        DateTimeFormatter saida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(saida);
    }

    public static String strDateTimeParaStrHora(LocalDateTime dateTime) {
        DateTimeFormatter saida = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(saida);
    }

}
