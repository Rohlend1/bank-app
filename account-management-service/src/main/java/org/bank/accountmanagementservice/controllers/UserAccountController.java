package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.ReplenishBalanceDto;
import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.bank.accountmanagementservice.services.UserAccountService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-account")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping
    public List<UserAccountDto> findAllByUserPhoneNumber(@RequestParam String uniqueNumber){
        return userAccountService.findAllByUserUniqueId(uniqueNumber);
    }

    @PostMapping
    public void save(@RequestBody UserAccountDto dto){
        userAccountService.save(dto);
    }

    @GetMapping("{id}")
    public UserAccountDto findById(@PathVariable("id")Long id){
        return userAccountService.findById(id);
    }

    @PostMapping("/close")
    public void closeAccount(@RequestBody String accountNumber){
        userAccountService.closeAccount(accountNumber);
    }

    @PatchMapping("/close")
    public void topUpBalance(@RequestBody ReplenishBalanceDto dto){
        userAccountService.topUpBalance(dto);
    }

    @GetMapping("/check")
    public Boolean doTransactionIfAllowed(@RequestParam MultiValueMap<String, String> dataToCheck){
        return userAccountService.doTransactionIfAllowed(dataToCheck);
    }
}
