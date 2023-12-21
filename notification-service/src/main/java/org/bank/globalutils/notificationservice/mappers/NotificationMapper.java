package org.bank.globalutils.notificationservice.mappers;

import org.bank.globalutils.kafka.KafkaMessage;
import org.bank.globalutils.notificationservice.dto.NotificationDto;
import org.bank.globalutils.notificationservice.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification toEntity(NotificationDto dto);

    NotificationDto toDto(Notification entity);

    @Mapping(target = "message", source = "message")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "type", source = "type")
    NotificationDto toDto(KafkaMessage message);

    List<Notification> toEntityList(List<NotificationDto> dto);

    List<NotificationDto> toDtoList(List<Notification> entity);
}
