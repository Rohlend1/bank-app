package org.bank.globalutils.accountmanagementservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bank.globalutils.accountmanagementservice.utils.enums.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "user_account")
@Data
@NoArgsConstructor
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;

    @Column(name = "number", updatable = false, unique = true)
    private UUID number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "unique_user_id", updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    public void generateUUID(){
        number = UUID.randomUUID();
    }
}
