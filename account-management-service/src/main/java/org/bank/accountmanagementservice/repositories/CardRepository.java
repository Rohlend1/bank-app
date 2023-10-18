package org.bank.accountmanagementservice.repositories;

import org.bank.accountmanagementservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT c FROM Card c LEFT JOIN c.userAccount usac WHERE usac.number = :number")
    List<Card> findAllByUserAccountNumber(String number);

    Card findByNumber(String Number);
}
