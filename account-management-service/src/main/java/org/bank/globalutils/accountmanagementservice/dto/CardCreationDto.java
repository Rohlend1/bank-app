package org.bank.globalutils.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bank.globalutils.accountmanagementservice.utils.enums.PaymentSystem;

import java.util.UUID;


@Getter
@Setter
public class CardCreationDto {

    private UUID userAccountNumber;

    private PaymentSystem paymentSystem;

    private Boolean blocked;
}
