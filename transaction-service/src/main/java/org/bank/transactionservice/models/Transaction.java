package org.bank.transactionservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.transactionservice.utils.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id")
    private UUID id;

    @Column(name = "sender_account_number")
    private String senderAccountNumber;

    @Column(name = "receiver_account_number")
    private String receiverAccountNumber;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    public void setTransactionTime(){
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        LocalDateTime transactionTime = LocalDateTime.now(zoneId);
    }

    public Transaction(String senderAccountNumber, String receiverAccountNumber, String description, BigDecimal amount) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.description = description;
        this.amount = amount;
    }
}
