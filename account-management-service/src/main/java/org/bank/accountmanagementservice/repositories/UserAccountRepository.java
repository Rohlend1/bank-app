package org.bank.accountmanagementservice.repositories;


import org.bank.accountmanagementservice.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByNumber(String accountNumber);

    @Query(value = "SELECT usac FROM UserAccount usac JOIN usac.user u WHERE u.uniqueUserId = :uniqueId")
    List<UserAccount> findAllByUserUniqueId(UUID uniqueId);
}
