package de.o0o0o0.utils;

public class Assert {
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
