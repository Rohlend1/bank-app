package org.bank.transactionservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.services.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void transferMoney(@RequestBody TransferMoneyDto dto){
        transactionService.transferMoney(dto);
    }
}
