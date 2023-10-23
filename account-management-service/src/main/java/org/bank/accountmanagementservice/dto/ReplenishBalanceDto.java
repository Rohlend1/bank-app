package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ReplenishBalanceDto {

    private BigDecimal amount;

    private UUID accountNumber;
}
