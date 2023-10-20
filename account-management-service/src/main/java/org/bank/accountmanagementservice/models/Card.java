package org.bank.accountmanagementservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bank.accountmanagementservice.utils.enums.PaymentSystem;

import java.time.LocalDate;

@Table(name = "card")
@Data
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @EqualsAndHashCode.Exclude
    private UserAccount userAccount;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "payment_system")
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;

    @Column(name = "blocked")
    private Boolean blocked;
}
