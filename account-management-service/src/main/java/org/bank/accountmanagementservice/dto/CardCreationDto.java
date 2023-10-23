package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bank.accountmanagementservice.utils.enums.PaymentSystem;

import java.util.UUID;


@Getter
@Setter
public class CardCreationDto {

    private UUID userAccountNumber;

    private PaymentSystem paymentSystem;

    private Boolean blocked;
}
