package org.bank.transactionservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferMoneyDto {

    private String senderAccountNumber;

    private String receiverAccountNumber;

    private BigDecimal amount;

    private String description;
}
