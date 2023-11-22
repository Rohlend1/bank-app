package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.dto.UserDto;
import org.bank.accountmanagementservice.dto.UserRequestDto;
import org.bank.accountmanagementservice.dto.UserUpdateDto;
import org.bankApp.response.ResponseMessage;


public interface UserService {

    ResponseMessage save(UserRequestDto user);

    UserDto findByUniqueId(String id);

    void update(UserUpdateDto user);
}
