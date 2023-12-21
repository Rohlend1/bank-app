package org.bank.globalutils.notificationservice.controllers;

import lombok.RequiredArgsConstructor;
import org.bank.globalutils.notificationservice.dto.NotificationDto;
import org.bank.globalutils.notificationservice.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    @GetMapping("/{number}")
    public List<NotificationDto> findAllByAccountNumber(@PathVariable("number")UUID userUniqueNumber){
        return notificationService.findAllByUserUniqueNumber(userUniqueNumber);
    }

    @PostMapping("/{id}")
    public void setSeenNotification(@PathVariable("id")Long id){
        notificationService.setSeenNotification(id);
    }
}
