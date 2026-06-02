package com.pastillazz.f1nt3ch.notifications.infrastructure.mapper;

import com.pastillazz.f1nt3ch.notifications.domain.model.Notification;
import com.pastillazz.f1nt3ch.notifications.infrastructure.entities.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationEntity toEntity(Notification domain)
    {
        return NotificationEntity.builder()
                .id(domain.id())
                .email(domain.email())
                .title(domain.title())
                .message(domain.message())
                .createdAt(domain.createdAt())
                .build();
    }
    public Notification toDomain(NotificationEntity entity)
    {
        return Notification.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .title(entity.getTitle())
                .message(entity.getMessage())
                .createdAt(entity.getCreatedAt())
                .build();
    }



}
