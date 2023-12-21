package org.bank.globalutils.accountmanagementservice.repositories;

import org.bank.globalutils.accountmanagementservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUniqueUserId(UUID uuid);
}