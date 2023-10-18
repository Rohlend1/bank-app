package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bank.accountmanagementservice.utils.enums.AccountType;

import java.math.BigDecimal;

@Getter
@Setter
public class UserAccountDto {

    private String number;

    private Long userId;

    private AccountType type;

    private BigDecimal balance;

    private Boolean active;
}
