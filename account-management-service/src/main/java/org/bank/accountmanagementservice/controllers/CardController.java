package org.bank.accountmanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.models.Card;
import org.bank.accountmanagementservice.services.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<Card> findAll(){
        return cardService.findAll();
    }
    @PostMapping
    public void save(@RequestBody Card card){
        cardService.save(card);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")Long id){
        cardService.delete(id);
    }
    @GetMapping("{id}")
    public Card findById(@PathVariable("id")Long id){
        return cardService.findById(id);
    }
}
