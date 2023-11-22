package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.UserDto;
import org.bank.accountmanagementservice.dto.UserRequestDto;
import org.bank.accountmanagementservice.dto.UserUpdateDto;
import org.bank.accountmanagementservice.services.UserService;
import org.bankApp.response.ResponseMessage;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseMessage save(@RequestBody UserRequestDto user){
        return userService.save(user);
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable("id")String id){
        return userService.findByUniqueId(id);
    }

    @PutMapping
    public void update(@RequestBody UserUpdateDto user){
        userService.update(user);
    }
}
