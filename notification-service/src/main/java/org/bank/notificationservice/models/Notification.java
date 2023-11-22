package org.bank.notificationservice.models;

import jakarta.persistence.*;
import lombok.Data;
import org.bankApp.util.MessageType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(name = "message", length = 2500)
    private String message;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "user_unique_number")
    private UUID userUniqueNumber;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(name = "seen")
    private Boolean seen;
}
