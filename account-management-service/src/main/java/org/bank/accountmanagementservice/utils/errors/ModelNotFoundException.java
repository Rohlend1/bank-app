package org.bank.accountmanagementservice.utils.errors;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException() {
        super("Model wasn't found");
    }

    public ModelNotFoundException(String message) {
        super(message);
    }
}
