package com.alphacell.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Created by luis.cevallos on 2/4/2016.
 */
public class ManejoFechas {

    public static int getNumeroSemana(Date fechaIni)
    {
        /*String input = dia.equalsIgnoreCase("")?dia:"20163003";

        String format = "yyyyMMdd";

        SimpleDateFormat df = new SimpleDateFormat(format);

        Date date = null;
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       */
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaIni);
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        return week;

    }

    public static List<Date> FechasExtremas(Date fecha)
    {
        List<Date> fechas_retorno= new ArrayList<>();

        LocalDate now = LocalDateTime.ofInstant(fecha.toInstant(), ZoneId.systemDefault()).toLocalDate();

        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        LocalDate startOfCurrentWeek = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

// determine last day of current week
        DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6); // or minus(1)
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

// Print the dates of the current week
        LocalDate printDate = startOfCurrentWeek;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd/MM/yyyy");
        for (int i=0; i < 5; i++) {
            // System.out.println(printDate.format(formatter));
            fechas_retorno.add(Date.from(printDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            printDate = printDate.plusDays(1);
        }
        return fechas_retorno;
    }
}
