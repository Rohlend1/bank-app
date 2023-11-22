package org.bank.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bankApp.util.MessageType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotificationDto {

    private String message;

    private LocalDateTime timestamp;

    private UUID userUniqueNumber;

    private MessageType type;

    private Boolean seen;
}
