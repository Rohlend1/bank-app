package org.bank.notificationservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.notificationservice.dto.NotificationDto;
import org.bank.notificationservice.services.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> findAll(){
        return notificationService.findAll();
    }
}
