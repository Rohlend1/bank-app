package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")Long id){
        userService.delete(id);
    }

    @GetMapping("{id}")
    public User findById(@PathVariable("id")Long id){
        return userService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody User user){
        userService.update(user);
    }
}
