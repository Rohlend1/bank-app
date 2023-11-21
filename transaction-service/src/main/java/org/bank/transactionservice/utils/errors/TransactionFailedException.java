package org.bank.transactionservice.utils.errors;

public class TransactionFailedException extends RuntimeException{

    public TransactionFailedException() {
        super();
    }

    public TransactionFailedException(String message) {
        super(message);
    }
}
