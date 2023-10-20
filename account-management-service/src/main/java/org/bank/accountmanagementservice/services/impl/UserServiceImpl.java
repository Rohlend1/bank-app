package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.UserDto;
import org.bank.accountmanagementservice.dto.UserUpdateDto;
import org.bank.accountmanagementservice.repositories.UserRepository;
import org.bank.accountmanagementservice.services.UserService;
import org.bank.accountmanagementservice.utils.mappers.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findByUniqueId(String id){
        return userMapper.toDto(userRepository.findByUniqueUserId(UUID.fromString(id)));
    }

    public void save(UserDto user){
        userRepository.save(userMapper.toEntity(user));
    }


    public void update(UserUpdateDto dto){
        userRepository.save(userMapper.toEntity(dto));
    }
}
