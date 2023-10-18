package org.bank.accountmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bank.accountmanagementservice.utils.enums.PaymentSystem;

import java.time.LocalDate;

@Getter
@Setter
public class CardResponseDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long number;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userAccountId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate expiredDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate releaseDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer cvv;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private PaymentSystem paymentSystem;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean blocked;
}
