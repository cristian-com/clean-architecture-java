package com.cristian.accounts.common;

public class Strings {

    private Strings(){}

    public static void requiresNonBlank(final String s) {
        if (s != null && s.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isBlank(final String s) {
        return s == null || s.isBlank();
    }

    public static boolean isNonBlank(final String s) {
        return !isBlank(s);
    }

}
