package org.bank.globalutils.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String patronymic;

    private String phoneNumber;

    private LocalDate birthDate;
}
