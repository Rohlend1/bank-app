package org.bank.transactionservice.utils.mappers;

import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.models.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    Transaction toEntity(TransferMoneyDto dto);
}
