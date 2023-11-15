package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.AccountNumberRequestDto;
import org.bank.accountmanagementservice.dto.ReplenishBalanceDto;
import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.bank.accountmanagementservice.services.UserAccountService;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-account")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping
    public List<UserAccountDto> findAllByUserUniqueId(@RequestParam String uniqueNumber){
        return userAccountService.findAllByUserUniqueId(uniqueNumber);
    }

    @PostMapping
    public void save(@RequestBody UserAccountDto dto){
        userAccountService.save(dto);
    }

    @GetMapping("{id}")
    public UserAccountDto findById(@PathVariable("id") Long id){
        return userAccountService.findById(id);
    }

    @PostMapping("/close")
    public void closeAccount(@RequestBody AccountNumberRequestDto dto){
        userAccountService.closeAccount(dto.getAccountNumber());
    }

    @PatchMapping("/topUp")
    public void topUpBalance(@RequestBody ReplenishBalanceDto dto){
        userAccountService.topUpBalance(dto);
    }

    @GetMapping("/check")
    public Boolean doTransactionIfAllowed(@RequestParam MultiValueMap<String, String> dataToCheck){
        return userAccountService.doTransactionIfAllowed(dataToCheck);
    }

    @ExceptionHandler(value = {ModelNotFoundException.class})
    public String exceptionHandle(Exception e){
        return String.format("%s\nwas caught at %s", e.getMessage(), LocalDateTime.now());
    }
}
