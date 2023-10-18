package org.bank.accountmanagementservice.repositories;

import org.bank.accountmanagementservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUniqueUserId(UUID uuid);
}
