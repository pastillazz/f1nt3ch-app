package com.pastillazz.f1nt3ch.notifications.infrastructure.controllers;

import com.pastillazz.f1nt3ch.notifications.application.services.NotificationService;
import com.pastillazz.f1nt3ch.notifications.infrastructure.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController
{
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications
            (@RequestParam String email)
    {
        return ResponseEntity.ok(notificationService.getAllNotifications(email));
    }


}
