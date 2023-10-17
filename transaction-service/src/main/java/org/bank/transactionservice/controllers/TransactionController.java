package org.bank.transactionservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.models.Transaction;
import org.bank.transactionservice.services.TransactionService;
import org.bank.transactionservice.utils.errors.TransactionFailedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void transferMoney(@RequestBody TransferMoneyDto dto){
        transactionService.transferMoney(dto);
    }

    @GetMapping
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    @ExceptionHandler(value = {TransactionFailedException.class})
    public String exceptionHandle(Exception e){
        return String.format("%s \nwas caught at %s", e.getMessage(), LocalDateTime.now());
    }
}
