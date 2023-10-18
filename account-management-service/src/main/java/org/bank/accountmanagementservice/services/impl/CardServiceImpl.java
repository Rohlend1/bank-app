package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.CardCreationDto;
import org.bank.accountmanagementservice.dto.CardResponseDto;
import org.bank.accountmanagementservice.models.Card;
import org.bank.accountmanagementservice.repositories.CardRepository;
import org.bank.accountmanagementservice.services.CardService;
import org.bank.accountmanagementservice.utils.CardNumberGenerator;
import org.bank.accountmanagementservice.utils.errors.InvalidCardNumber;
import org.bank.accountmanagementservice.utils.mappers.CardMapper;
import org.bank.accountmanagementservice.utils.security.AesEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    @Transactional(readOnly = true)
    public List<CardResponseDto> findAllByUserAccountNumber(String number){
        return cardMapper.toResponseDtos(cardRepository.findAllByUserAccountNumber(number));
    }

    public void save(CardCreationDto dto){
        Card card = cardMapper.toEntity(dto);
        card.setNumber(AesEncoder.encode(CardNumberGenerator.generateCardNumber(card.getPaymentSystem())));
        card.setCvv(AesEncoder.encode(generateCvv()));
        cardRepository.save(card);
    }

    public void blockCard(String cardNumber){
        if(CardNumberGenerator.validateCardNumber(cardNumber)){
            Card card = cardRepository.findByNumber(cardNumber);
            card.setBlocked(true);
        }
        else {
            throw new InvalidCardNumber();
        }
    }

    private String generateCvv(){
        Random random = new Random();
        return String.valueOf(random.nextInt(100, 1000));
    }
}
