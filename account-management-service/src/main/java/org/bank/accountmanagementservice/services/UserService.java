package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.dto.UserDto;
import org.bank.accountmanagementservice.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    void save(UserDto user);

    UserDto findByUniqueId(String id);

    void update(UserUpdateDto user);
}
