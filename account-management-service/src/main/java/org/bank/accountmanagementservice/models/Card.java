package org.bank.accountmanagementservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

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
    private Long number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private UserAccount userAccount;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "cvv")
    private Integer cvv;
}
