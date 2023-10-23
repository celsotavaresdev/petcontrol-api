package com.ongpatinhasquebrilham.petcontrol.util;

public class StringUtils {

    public static String camelCaseToSnakeCase(String str) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";

        return str
                .replaceAll(regex, replacement)
                .toLowerCase();
    }
}
