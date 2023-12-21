package org.bank.globalutils.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bank.globalutils.accountmanagementservice.utils.enums.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UserAccountDto {

    private String number;

    private UUID userId;

    private AccountType type;

    private BigDecimal balance;

    private Boolean active;
}
