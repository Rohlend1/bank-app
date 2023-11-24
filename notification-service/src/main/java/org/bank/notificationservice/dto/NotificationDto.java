package org.bank.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bankApp.util.MessageType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class NotificationDto {

    private Long id;

    private String message;

    private LocalDateTime timestamp;

    private UUID userUniqueNumber;

    private MessageType type;

    private Boolean seen;
}
