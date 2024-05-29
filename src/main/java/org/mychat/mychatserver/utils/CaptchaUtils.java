package org.mychat.mychatserver.utils;

import java.security.SecureRandom;

public class CaptchaUtils {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String DIGITS = "0123456789";

    public static String generateCaptcha(int length) {
        StringBuilder captcha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            captcha.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        return captcha.toString();
    }
}
