package org.bank.accountmanagementservice.utils.errors;

public class InvalidCardNumber extends RuntimeException{

    public InvalidCardNumber() {
        super("Invalid card number");
    }

    public InvalidCardNumber(String message) {
        super(message);
    }
}
