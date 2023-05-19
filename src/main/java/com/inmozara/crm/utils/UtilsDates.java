package com.inmozara.crm.utils;

import java.text.SimpleDateFormat;
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
}
