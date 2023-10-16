package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.models.UserAccount;
import org.bank.accountmanagementservice.services.UserAccountService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-account")
@Slf4j
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping
    public List<UserAccount> findAll(){
        return userAccountService.findAll();
    }

    @PostMapping
    public void save(@RequestBody UserAccount userAccount){
        userAccountService.save(userAccount);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")Long id){
        userAccountService.delete(id);
    }

    @GetMapping("{id}")
    public UserAccount findById(@PathVariable("id")Long id){
        return userAccountService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody UserAccount userAccount){
        userAccountService.update(userAccount);
    }

    @GetMapping("/check")
    public Boolean isTransactionAllowed(@RequestParam MultiValueMap<String, String> dataToCheck){
        log.info(dataToCheck.toString());
        return userAccountService.isTransactionAllowed(dataToCheck);
    }
}
