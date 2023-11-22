package org.bank.accountmanagementservice.utils.mappers;

import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.bank.accountmanagementservice.dto.UserDto;
import org.bank.accountmanagementservice.dto.UserRequestDto;
import org.bank.accountmanagementservice.dto.UserUpdateDto;
import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.models.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    @Mapping(target = "userAccounts", expression = "java(getUserAccount(dto.getUserAccountsIds()))")
    User toEntity(UserDto dto);
    @Mapping(target = "userAccountsIds", expression = "java(getUserAccountIds(user.getUserAccounts()))")
    UserDto toDto(User user);
    List<UserAccountDto> toDto(List<UserAccount> userAccounts);

    @Mapping(target = "userAccounts", expression = "java(getUserAccount(dto.getUserAccountsIds()))")
    User toEntity(UserUpdateDto dto);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "patronymic", source = "patronymic")
    UserDto toUserDto(UserRequestDto user);

    default Set<Long> getUserAccountIds(Set<UserAccount> userAccounts){
        return userAccounts == null ? new HashSet<>() : userAccounts.stream().map(UserAccount::getId).collect(Collectors.toSet());
    }

    default Set<UserAccount> getUserAccount(Set<Long> userAccountIds){
        return userAccountIds == null ? new HashSet<>() : userAccountIds.stream().map(id->{
            UserAccount userAccount = new UserAccount();
            userAccount.setId(id);
            return userAccount;
        }).collect(Collectors.toSet());
    }
}
