package org.bank.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto {

    private String message;

    private LocalDateTime timestamp;
}
