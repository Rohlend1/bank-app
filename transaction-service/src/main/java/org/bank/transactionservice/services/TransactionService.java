package org.bank.transactionservice.services;

import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.models.Transaction;
import org.bankApp.response.ResponseMessage;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    ResponseMessage transferMoney(TransferMoneyDto dto);

    List<Transaction> findAllByAccountNumber(UUID accountNumber);
}
