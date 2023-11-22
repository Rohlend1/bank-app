package org.bank.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.bankApp.util.MessageType;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto {

    private String message;

    private LocalDateTime timestamp;

    private MessageType type;

    private Boolean seen;
}
