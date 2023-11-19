package org.bank.notificationservice.services;

import lombok.RequiredArgsConstructor;
import org.bank.notificationservice.dto.NotificationDto;
import org.bank.notificationservice.mappers.NotificationMapper;
import org.bank.notificationservice.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationMapper notificationMapper;

    private final NotificationRepository notificationRepository;


    public void save(NotificationDto notificationDto){
        notificationRepository.save(notificationMapper.toEntity(notificationDto));
    }

    public List<NotificationDto> findAll(){
        return notificationMapper.toDtoList(notificationRepository.findAll());
    }
}
