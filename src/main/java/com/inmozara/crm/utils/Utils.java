package com.inmozara.crm.utils;

public class Utils {

    public static boolean isNullOrEmpty(String cadena) {
        return cadena == null || cadena.isEmpty() || cadena.equals("null");
    }
    public static boolean isNotNullOrEmpty(String cadena) {
        return cadena != null && !cadena.isEmpty() && !cadena.equals("null");
    }
}
