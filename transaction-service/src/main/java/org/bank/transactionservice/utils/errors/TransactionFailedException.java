package org.bank.transactionservice.utils.errors;

public class TransactionFailedException extends RuntimeException{

    public TransactionFailedException() {
        super("There are insufficient funds on the balance or an error when entering the account number.");
    }
}
