package com.iceb.library.utils;

public final class DataMaskingUtils {

    private DataMaskingUtils() {
    }

    public static String maskEmail(String email) {
        if (email == null || email.isBlank()) {
            return email;
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0) {
            return maskAllButFirst(email);
        }

        String localPart = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        String maskedLocal = maskAllButFirst(localPart);

        int dotIndex = domain.lastIndexOf('.');
        if (dotIndex <= 0) {
            return maskedLocal + "@" + maskAllButFirst(domain);
        }

        String domainName = domain.substring(0, dotIndex);
        String tld = domain.substring(dotIndex);
        return maskedLocal + "@" + maskAllButFirst(domainName) + tld;
    }

    public static String maskPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            return phone;
        }

        long digitCount = phone.chars().filter(Character::isDigit).count();
        if (digitCount <= 4) {
            return phone.replaceAll("\\d", "*");
        }

        int digitsToMask = (int) digitCount - 4;
        StringBuilder masked = new StringBuilder(phone.length());
        int maskedDigits = 0;

        for (char character : phone.toCharArray()) {
            if (Character.isDigit(character)) {
                if (maskedDigits < digitsToMask) {
                    masked.append('*');
                    maskedDigits++;
                } else {
                    masked.append(character);
                }
            } else {
                masked.append(character);
            }
        }

        return masked.toString();
    }

    private static String maskAllButFirst(String value) {
        if (value.length() <= 1) {
            return value;
        }
        return value.charAt(0) + "*".repeat(value.length() - 1);
    }
}
