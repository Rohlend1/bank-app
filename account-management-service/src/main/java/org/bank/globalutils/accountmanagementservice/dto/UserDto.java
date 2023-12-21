package org.bank.globalutils.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private String firstName;

    private UUID uniqueUserId;

    private String lastName;

    private String patronymic;

    private Set<Long> userAccountsIds;

    private LocalDate birthDate;

    private String phoneNumber;
}
