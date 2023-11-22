package org.bank.accountmanagementservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bank.accountmanagementservice.utils.Credential;

import java.util.List;

@Getter
@Setter
@ToString
public class KeycloakUser {

    private String firstName;

    private String lastName;

    private String username;

    private List<Credential> credentials;

    private Boolean enabled;

    private String email;

    private Boolean emailVerified;

}