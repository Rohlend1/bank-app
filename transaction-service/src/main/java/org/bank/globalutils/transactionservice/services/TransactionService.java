package org.bank.globalutils.transactionservice.services;

import org.bank.globalutils.transactionservice.dto.TransferMoneyDto;
import org.bank.globalutils.transactionservice.models.Transaction;
import org.bank.globalutils.response.ResponseMessage;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    ResponseMessage transferMoney(TransferMoneyDto dto);

    List<Transaction> findAllByAccountNumber(UUID accountNumber);
}
