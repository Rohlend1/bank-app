package org.bank.globalutils.notificationservice.repositories;

import org.bank.globalutils.notificationservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserUniqueNumber(UUID userUniqueNumber);
}
