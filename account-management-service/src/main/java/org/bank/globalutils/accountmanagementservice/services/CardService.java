package org.bank.globalutils.accountmanagementservice.services;

import org.bank.globalutils.accountmanagementservice.dto.CardCreationDto;
import org.bank.globalutils.accountmanagementservice.dto.CardResponseDto;

import java.util.List;
import java.util.UUID;

public interface CardService {

    List<CardResponseDto> findAllByUserAccountNumber(UUID number);
    void save(CardCreationDto card);
    void blockCard(String cardNumber);
}
