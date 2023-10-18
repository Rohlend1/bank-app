package org.bank.accountmanagementservice.utils.mappers;

import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.bank.accountmanagementservice.models.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserAccountMapper {

    @Mapping(source = "userId", target = "user.id")
    UserAccount toEntity(UserAccountDto dto);
    @Mapping(source = "user.id", target = "userId")
    UserAccountDto toDto(UserAccount userAccount);
    List<UserAccountDto> toDto(List<UserAccount> userAccounts);
}
