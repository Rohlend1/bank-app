package org.bank.globalutils.accountmanagementservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bank.globalutils.accountmanagementservice.utils.enums.PaymentSystem;

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

    @Column(name = "number", updatable = false)
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "number", updatable = false)
    @EqualsAndHashCode.Exclude
    private UserAccount userAccount;

    @Column(name = "expired_date", updatable = false)
    private LocalDate expiredDate;

    @Column(name = "release_date", updatable = false)
    private LocalDate releaseDate;

    @Column(name = "cvv", updatable = false)
    private String cvv;

    @Column(name = "payment_system")
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;

    @Column(name = "blocked")
    private Boolean blocked;
}
