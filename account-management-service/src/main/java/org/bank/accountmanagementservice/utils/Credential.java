package org.bank.accountmanagementservice.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Credential {

    private String type;

    private String value;

    private Boolean temporary;
}
