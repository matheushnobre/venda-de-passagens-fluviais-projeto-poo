package com.vendalancha.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConversorData {
    
    public static LocalDateTime converterParaLocalDateTime(String str_data, String str_hora) {
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate data = LocalDate.parse(str_data, formatterData);
        LocalTime hora = LocalTime.parse(str_hora, formatterHora);

        return LocalDateTime.of(data, hora);
    }
    
    public static String converterParaStringSQL(LocalDateTime horario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM--dd HH:mm:ss");
        return horario.format(formatter);
    }
}
