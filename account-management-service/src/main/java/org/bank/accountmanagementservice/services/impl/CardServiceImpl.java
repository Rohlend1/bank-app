package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.CardCreationDto;
import org.bank.accountmanagementservice.dto.CardResponseDto;
import org.bank.accountmanagementservice.models.Card;
import org.bank.accountmanagementservice.models.UserAccount;
import org.bank.accountmanagementservice.repositories.CardRepository;
import org.bank.accountmanagementservice.repositories.UserAccountRepository;
import org.bank.accountmanagementservice.services.CardService;
import org.bank.accountmanagementservice.utils.CardNumberGenerator;
import org.bank.accountmanagementservice.utils.errors.InvalidCardNumber;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.bank.accountmanagementservice.utils.mappers.CardMapper;
import org.bank.accountmanagementservice.utils.security.AesEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final UserAccountRepository userAccountRepository;

    private final CardMapper cardMapper;

    private final AesEncoder aesEncoder;

    @Transactional(readOnly = true)
    public List<CardResponseDto> findAllByUserAccountNumber(UUID number){
        List<Card> cards = cardRepository.findAllByUserAccountNumber(number);
        cards.forEach(card -> {
            card.setNumber(aesEncoder.decode(card.getNumber()));
            card.setCvv(aesEncoder.decode(card.getCvv()));
        });
        return cardMapper.toResponseDtos(cards);
    }

    public void save(CardCreationDto dto){
        UserAccount userAccount = userAccountRepository.findByNumber(dto.getUserAccountNumber()).orElseThrow(ModelNotFoundException::new);
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        Card card = cardMapper.toEntity(dto);
        card.setUserAccount(userAccount);
        LocalDate localDate = LocalDate.now(zoneId);
        card.setExpiredDate(localDate.plus(5, ChronoUnit.YEARS));
        card.setReleaseDate(localDate);
        card.setNumber(aesEncoder.encode(CardNumberGenerator.generateCardNumber(card.getPaymentSystem())));
        card.setCvv(aesEncoder.encode(generateCvv()));
        cardRepository.save(card);
    }

    public void blockCard(String cardNumber){
        if(CardNumberGenerator.validateCardNumber(cardNumber)){
            Card card = cardRepository.findByNumber(aesEncoder.encode(cardNumber));
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
