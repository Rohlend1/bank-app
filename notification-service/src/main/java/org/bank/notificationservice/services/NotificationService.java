package org.bank.notificationservice.services;

import lombok.RequiredArgsConstructor;
import org.bank.notificationservice.dto.NotificationDto;
import org.bank.notificationservice.mappers.NotificationMapper;
import org.bank.notificationservice.models.Notification;
import org.bank.notificationservice.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationMapper notificationMapper;

    private final NotificationRepository notificationRepository;


    public void save(NotificationDto notificationDto){
        Notification notification = notificationMapper.toEntity(notificationDto);
        if(notification.getSeen() == null){
            notification.setSeen(Boolean.FALSE);
        }
        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> findAll(){
        return notificationMapper.toDtoList(notificationRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> findAllByUserUniqueNumber(UUID userUniqueNumber){
        return notificationMapper.toDtoList(notificationRepository.findAllByUserUniqueNumber(userUniqueNumber));
    }
}
