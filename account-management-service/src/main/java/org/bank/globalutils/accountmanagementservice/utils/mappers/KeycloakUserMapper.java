package org.bank.globalutils.accountmanagementservice.utils.mappers;

import org.bank.globalutils.accountmanagementservice.dto.UserRequestDto;
import org.bank.globalutils.accountmanagementservice.models.KeycloakUser;
import org.bank.globalutils.accountmanagementservice.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface KeycloakUserMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    User toUser(KeycloakUser keycloakUser);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    KeycloakUser toKeycloakUser(User user);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    KeycloakUser toKeycloakUser(UserRequestDto user);
}
