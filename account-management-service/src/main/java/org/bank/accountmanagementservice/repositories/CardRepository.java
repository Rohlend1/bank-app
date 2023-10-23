package org.bank.accountmanagementservice.repositories;

import org.bank.accountmanagementservice.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT c FROM Card c JOIN c.userAccount usac WHERE usac.number = :num")
    List<Card> findAllByUserAccountNumber(@Param("num") UUID number);

    Card findByNumber(String Number);
}
