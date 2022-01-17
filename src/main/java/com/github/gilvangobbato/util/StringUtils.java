package com.github.gilvangobbato.util;

public class StringUtils {

    /**
     * Verify if de string is only numbers
     *
     * @param value
     * @return
     */
    public static boolean isOnlyNumbers(String value) {
        return value.matches("^[0-9]*$");
    }

}
