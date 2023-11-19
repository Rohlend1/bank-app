package org.bank.notificationservice.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(name = "message", length = 2500)
    private String message;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
