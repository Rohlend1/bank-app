package org.bank.accountmanagementservice.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentSystem {

    VISA("4"), MASTERCARD("5"), MIR("2");

    private final String bin;

}
