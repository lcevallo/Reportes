package com.alphacell.util;

import com.alphacell.model.financiero.FechasCorte;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.IntStream;

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

    public static List<FechasCorte> FechasCortes(Date fecha,Integer noSemana, Integer semanas)
    {
        List<FechasCorte> fechas_retorno= new ArrayList<>();
        LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();

        LocalDateTime xmas = LocalDateTime.of(year, month, day, 0, 0);


        IntStream.range(0,semanas)
                .forEach(x-> {
                    LocalDateTime newYearsDay = xmas.plusWeeks(x);
                    Date out = Date.from(newYearsDay.atZone(ZoneId.systemDefault()).toInstant());

                    fechas_retorno.add(new FechasCorte(out,(noSemana+x),x));
                });

        return fechas_retorno;
    }
}
