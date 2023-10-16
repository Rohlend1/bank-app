package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.models.Card;
import org.bank.accountmanagementservice.repositories.CardRepository;
import org.bank.accountmanagementservice.services.CardService;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public List<Card> findAll(){
        return cardRepository.findAll();
    }
    public void save(Card card){
        cardRepository.save(card);
    }

    public void delete(Long id){
        cardRepository.deleteById(id);
    }

    public Card findById(Long id){
        return cardRepository.findById(id).orElseThrow(ModelNotFoundException::new);
    }
}
