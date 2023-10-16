package org.bank.transactionservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.transactionservice.dto.TransferMoneyDto;
import org.bank.transactionservice.models.Transaction;
import org.bank.transactionservice.repositories.TransactionRepository;
import org.bank.transactionservice.services.TransactionService;
import org.bank.transactionservice.utils.enums.Status;
import org.bank.transactionservice.utils.errors.TransactionFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final WebClient webClient;

    public void transferMoney(TransferMoneyDto dto) {
        MultiValueMap<String, String> parametersToCheck = new LinkedMultiValueMap<>();
        parametersToCheck.put("accountNumbers", List.of(dto.getSenderAccountNumber(), dto.getReceiverAccountNumber()));
        parametersToCheck.put("amount", List.of(dto.getAmount().toString()));
        Boolean isPossible = webClient
                .get()
                .uri("http://account-management-service/user-account/check",
                        uriBuilder ->
                        uriBuilder.queryParams(parametersToCheck).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        Transaction transaction = new Transaction(dto.getSenderAccountNumber(), dto.getReceiverAccountNumber(), dto.getDescription(), dto.getAmount());

        if(Boolean.TRUE.equals(isPossible)){
            transaction.setStatus(Status.ACCEPTED);
            transactionRepository.save(transaction);
        }
        else {
            transaction.setStatus(Status.CANCELED);
            throw new TransactionFailedException();
        }

    }
}
