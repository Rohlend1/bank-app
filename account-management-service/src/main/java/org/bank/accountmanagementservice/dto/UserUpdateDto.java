package org.bank.accountmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserUpdateDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String firstName;

    private UUID uniqueUserId;

    private String lastName;

    private String patronymic;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Long> userAccountsIds;

    private LocalDate birthDate;

    private String phoneNumber;
}
