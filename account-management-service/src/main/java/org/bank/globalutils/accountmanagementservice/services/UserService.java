package org.bank.globalutils.accountmanagementservice.services;

import org.bank.globalutils.accountmanagementservice.dto.UserDto;
import org.bank.globalutils.accountmanagementservice.dto.UserRequestDto;
import org.bank.globalutils.accountmanagementservice.dto.UserUpdateDto;
import org.bank.globalutils.response.ResponseMessage;


public interface UserService {

    ResponseMessage save(UserRequestDto user);

    UserDto findByUniqueId(String id);

    void update(UserUpdateDto user);
}
