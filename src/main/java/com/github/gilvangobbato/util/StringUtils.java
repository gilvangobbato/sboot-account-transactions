package com.github.gilvangobbato.util;

public class StringUtils {

    public static boolean isOnlyNumbers(String value) {
        return value.matches("^[0-9]*$");
    }

}
