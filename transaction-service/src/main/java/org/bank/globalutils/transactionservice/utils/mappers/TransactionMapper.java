package org.bank.globalutils.transactionservice.utils.mappers;

import org.bank.globalutils.transactionservice.dto.TransferMoneyDto;
import org.bank.globalutils.transactionservice.models.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    Transaction toEntity(TransferMoneyDto dto);
}
