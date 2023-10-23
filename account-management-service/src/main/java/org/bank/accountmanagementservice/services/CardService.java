package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.dto.CardCreationDto;
import org.bank.accountmanagementservice.dto.CardResponseDto;

import java.util.List;
import java.util.UUID;

public interface CardService {

    List<CardResponseDto> findAllByUserAccountNumber(UUID number);
    void save(CardCreationDto card);
    void blockCard(String cardNumber);
}
