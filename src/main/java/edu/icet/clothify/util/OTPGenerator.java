package edu.icet.clothify.util;

import java.security.SecureRandom;

public class OTPGenerator {
    private static OTPGenerator instance;

    private OTPGenerator() {
    }

    public static OTPGenerator getInstance() {
        return instance == null ? instance = new OTPGenerator() : instance;
    }

    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }
}
