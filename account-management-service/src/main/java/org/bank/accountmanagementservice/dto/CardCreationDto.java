package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bank.accountmanagementservice.utils.enums.PaymentSystem;

import java.time.LocalDate;

@Getter
@Setter
public class CardCreationDto {

    private Long userAccountId;

    private LocalDate expiredDate;

    private LocalDate releaseDate;

    private PaymentSystem paymentSystem;

    private Boolean blocked;
}
