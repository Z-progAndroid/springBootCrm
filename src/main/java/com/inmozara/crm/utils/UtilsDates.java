package com.inmozara.crm.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilsDates {
    private static final String FORMAT_DATE = "dd-MM-yyyy HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER_DD_MM_YYYY_HH_MM_SS = DateTimeFormatter.ofPattern(UtilsDates.FORMAT_DATE);

    public static String now() {
        return new SimpleDateFormat(FORMAT_DATE).format(new Date());
    }
}
