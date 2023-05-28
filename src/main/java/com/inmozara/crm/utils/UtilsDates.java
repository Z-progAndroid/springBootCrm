package com.inmozara.crm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilsDates {
    private static final String FORMAT_DATE = "dd-MM-yyyy HH:mm:ss";

    public static String parseDate(Date date, String format) {
        return new SimpleDateFormat(format == null || format.isEmpty() ? FORMAT_DATE : format)
                .format(new Date());
    }

    public static String now() {
        return new SimpleDateFormat(FORMAT_DATE).format(new Date());
    }

    public static String parseToFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaInicioStr = sdf.format(date);
        Date fechaInicio = null;
        try {
            fechaInicio = sdf.parse(fechaInicioStr);

            // Establecer horas, minutos, segundos y milisegundos a cero
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicio);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return String.valueOf(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
