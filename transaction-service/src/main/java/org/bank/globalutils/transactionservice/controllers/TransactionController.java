package org.bank.globalutils.transactionservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.globalutils.response.ResponseMessage;
import org.bank.globalutils.transactionservice.dto.TransferMoneyDto;
import org.bank.globalutils.transactionservice.models.Transaction;
import org.bank.globalutils.transactionservice.services.TransactionService;
import org.bank.globalutils.transactionservice.utils.errors.TransactionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseMessage transferMoney(@RequestBody TransferMoneyDto dto){
        return transactionService.transferMoney(dto);
    }

    @GetMapping("/{id}")
    public List<Transaction> findAllByAccountNumber(@PathVariable("id")UUID accountNumber){
        return transactionService.findAllByAccountNumber(accountNumber);
    }

    @ExceptionHandler(value = {TransactionFailedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String exceptionHandle(Exception e){
        return String.format("%s \nwas caught at %s", e.getMessage(), LocalDateTime.now());
    }

}
