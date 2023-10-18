package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.dto.CardCreationDto;
import org.bank.accountmanagementservice.dto.CardResponseDto;

import java.util.List;

public interface CardService {

    List<CardResponseDto> findAllByUserAccountNumber(String number);
    void save(CardCreationDto card);
    void blockCard(String cardNumber);
}
