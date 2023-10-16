package org.bank.transactionservice.services;

import org.bank.transactionservice.dto.TransferMoneyDto;

public interface TransactionService {

    void transferMoney(TransferMoneyDto dto);
}
