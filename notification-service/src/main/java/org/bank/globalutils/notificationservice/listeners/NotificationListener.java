package org.bank.globalutils.notificationservice.listeners;

import lombok.RequiredArgsConstructor;
import org.bank.globalutils.kafka.KafkaMessage;
import org.bank.globalutils.notificationservice.dto.NotificationDto;
import org.bank.globalutils.notificationservice.mappers.NotificationMapper;
import org.bank.globalutils.notificationservice.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;


    @KafkaListener(topics = "notification-topic", containerFactory = "kafkaContainerFactory")
    public void receiveNotification(KafkaMessage kafkaMessage){
        NotificationDto notificationDto = notificationMapper.toDto(kafkaMessage);
        notificationService.save(notificationDto);
    }
}
