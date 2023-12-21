package org.bank.globalutils.notificationservice.services;

import lombok.RequiredArgsConstructor;
import org.bank.globalutils.notificationservice.dto.NotificationDto;
import org.bank.globalutils.notificationservice.mappers.NotificationMapper;
import org.bank.globalutils.notificationservice.models.Notification;
import org.bank.globalutils.notificationservice.repositories.NotificationRepository;
import org.bank.globalutils.notificationservice.utils.errors.NotificationNotFoundException;
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

    public void setSeenNotification(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(()-> new NotificationNotFoundException(id));
        notification.setSeen(true);
    }
}
