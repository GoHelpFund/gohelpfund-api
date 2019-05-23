package com.gohelpfund.api.v1.donation_service.utils.helpcore.util;

public class ValidationUtils {

    private ValidationUtils() { }

    public static boolean isTransactionId(String stirng) {
        return stirng.matches("\\p{XDigit}{64}");
    }

    public static boolean isHexString(String string) {
        return string.matches("\\p{XDigit}+");
    }

    public static boolean isBase58(String string) {
        return string.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+");
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}
