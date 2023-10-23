package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountNumberRequestDto {

    private UUID accountNumber;
}
