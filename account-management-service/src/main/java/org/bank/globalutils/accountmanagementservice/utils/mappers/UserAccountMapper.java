package org.bank.globalutils.accountmanagementservice.utils.mappers;

import org.bank.globalutils.accountmanagementservice.dto.UserAccountDto;
import org.bank.globalutils.accountmanagementservice.models.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserAccountMapper {

    @Mapping(source = "userId", target = "user.uniqueUserId")
    UserAccount toEntity(UserAccountDto dto);
    @Mapping(source = "user.uniqueUserId", target = "userId")
    UserAccountDto toDto(UserAccount userAccount);
    List<UserAccountDto> toDto(List<UserAccount> userAccounts);
}
