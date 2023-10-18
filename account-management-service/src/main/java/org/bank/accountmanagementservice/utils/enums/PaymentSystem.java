package org.bank.accountmanagementservice.utils.enums;

import lombok.Getter;

@Getter
public enum PaymentSystem {

    VISA("4"), MASTERCARD("5"), MIR("2");

    private String bin;

    PaymentSystem(String bin) {
    }
}
