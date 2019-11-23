package de.o0o0o0.utils;

public class StringUtils {
    public static boolean hasLength(CharSequence string) {
        return (string != null && string.length() > 0);
    }
    public static boolean hasLength(String string) {
        return hasLength((CharSequence) string);
    }

    public static boolean hasText(String string) {
        return hasText((CharSequence) string);
    }

    public static boolean hasText(CharSequence sequence) {
        if(!hasLength(sequence)) {
            return false;
        }
        int strLen = sequence.length();
        for (int i = 0; i <strLen ; i++) {
            if (!Character.isWhitespace(sequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
