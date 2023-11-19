package org.bank.notificationservice.mappers;

import org.bank.notificationservice.dto.NotificationDto;
import org.bank.notificationservice.models.Notification;
import org.bankApp.kafka.KafkaMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification toEntity(NotificationDto dto);

    NotificationDto toDto(Notification entity);

    @Mapping(target = "message", source = "message")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "type", source = "type")
    NotificationDto toDto(KafkaMessage message);

    List<Notification> toEntityList(List<NotificationDto> dto);

    List<NotificationDto> toDtoList(List<Notification> entity);
}
