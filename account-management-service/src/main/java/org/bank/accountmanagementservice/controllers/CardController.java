package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.accountmanagementservice.dto.AccountNumberRequestDto;
import org.bank.accountmanagementservice.dto.CardCreationDto;
import org.bank.accountmanagementservice.dto.CardResponseDto;
import org.bank.accountmanagementservice.services.CardService;
import org.keycloak.admin.client.Keycloak;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    private final Keycloak keycloak;
    @PostMapping("/all")
    public List<CardResponseDto> findAll(@RequestBody AccountNumberRequestDto dto){
        return cardService.findAllByUserAccountNumber(dto.getAccountNumber());
    }
    @PostMapping
    public void save(@RequestBody CardCreationDto dto){
        cardService.save(dto);
    }

    @PostMapping("/block")
    public void blockCard(@RequestBody String cardNumber){
        cardService.blockCard(cardNumber);
    }
}
