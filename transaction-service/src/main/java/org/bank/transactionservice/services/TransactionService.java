package org.bank.transactionservice.services;

import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.models.Transaction;

import java.util.List;

public interface TransactionService {

    void transferMoney(TransferMoneyDto dto);

    List<Transaction> findAll();
}
