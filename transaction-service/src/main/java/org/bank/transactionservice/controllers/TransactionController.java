package org.bank.transactionservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.models.Transaction;
import org.bank.transactionservice.services.TransactionService;
import org.bank.transactionservice.utils.errors.TransactionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @CircuitBreaker(name = "transaction", fallbackMethod = "fallback")
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

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    public String fallback(){
        return "Service is not working try to transfer money later";
    }
}
