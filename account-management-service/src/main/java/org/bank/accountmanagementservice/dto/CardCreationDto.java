package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bank.accountmanagementservice.utils.enums.PaymentSystem;


@Getter
@Setter
public class CardCreationDto {

    private Long userAccountId;

    private PaymentSystem paymentSystem;

    private Boolean blocked;
}
