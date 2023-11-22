package org.bank.accountmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private List<String> roles;

    private Boolean emailVerified;

    private String patronymic;

    private String phoneNumber;

    private LocalDate birthDate;
}
