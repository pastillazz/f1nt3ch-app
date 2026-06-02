package com.pastillazz.f1nt3ch.notifications.application.services;

import com.pastillazz.f1nt3ch.notifications.domain.model.Notification;
import com.pastillazz.f1nt3ch.notifications.domain.port.NotificationRepository;
import com.pastillazz.f1nt3ch.notifications.infrastructure.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService
{
    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getAllNotifications(String email) {
       List<Notification> notifications= notificationRepository.findAllByEmail(email);
       return notifications
               .stream()
               .map(n->NotificationResponse.builder()
                        .title(n.title())
                        .message(n.message())
                        .createdAt(n.createdAt())
                        .build())
               .toList();
    }
}
