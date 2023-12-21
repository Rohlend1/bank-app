package org.bank.globalutils.accountmanagementservice.utils;

import org.bank.globalutils.accountmanagementservice.utils.enums.PaymentSystem;

import java.util.Random;

public class CardNumberGenerator {

    public static String generateCardNumber(PaymentSystem type) {
        String bin = type.getBin();
        Random random = new Random();
        StringBuilder sb = new StringBuilder(bin);
        while (sb.length() < 15) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        String number = sb.toString();
        int checksum = calculateLuhnChecksum(number);
        return number + checksum;
    }

    public static boolean validateCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            return false;
        }
        char[] digits = cardNumber.toCharArray();
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            int digit = Character.getNumericValue(digits[i]);
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }

    private static int calculateLuhnChecksum(String number) {
        if (number == null || number.length() != 15) {
            throw new IllegalArgumentException("Invalid card number");
        }
        char[] digits = number.toCharArray();
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            int digit = Character.getNumericValue(digits[i]);
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return (sum * 9) % 10;
    }
}
