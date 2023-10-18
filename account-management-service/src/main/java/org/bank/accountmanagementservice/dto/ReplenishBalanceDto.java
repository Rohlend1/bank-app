package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReplenishBalanceDto {

    private BigDecimal amount;

    private String accountNumber;
}
