package org.bank.transactionservice.repositories;

import org.bank.transactionservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.receiverAccountNumber = :accountNumber OR t.senderAccountNumber = :accountNumber")
    List<Transaction> findTransactionsByAccountNumber(@Param("accountNumber") String accountNumber);

}
