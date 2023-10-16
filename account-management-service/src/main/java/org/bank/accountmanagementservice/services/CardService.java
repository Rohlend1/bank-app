package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.models.Card;

import java.util.List;

public interface CardService {

    List<Card> findAll();
    void save(Card card);
    void delete(Long id);
    Card findById(Long id);
}
