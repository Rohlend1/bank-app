package org.bank.globalutils.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.globalutils.util.MessageType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage {

    private LocalDateTime timestamp;

    private String message;

    private UUID userUniqueNumber;

    private MessageType type;
}
