package com.pastillazz.f1nt3ch.notifications.infrastructure.repositories;

import com.pastillazz.f1nt3ch.notifications.domain.model.Notification;
import com.pastillazz.f1nt3ch.notifications.domain.port.NotificationRepository;
import com.pastillazz.f1nt3ch.notifications.infrastructure.entities.NotificationEntity;
import com.pastillazz.f1nt3ch.notifications.infrastructure.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final MySQLNotificationRepository mySQLNotificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public Notification save(Notification notification)
    {
        NotificationEntity entity = notificationMapper.toEntity(notification);
        NotificationEntity savedEntity = mySQLNotificationRepository.save(entity);
        return notificationMapper.toDomain(savedEntity);
    }

    @Override
    public List<Notification> findAllByEmail(String email)
    {
        List<NotificationEntity> notifications=mySQLNotificationRepository.findAllByEmail(email);
        return notifications
                .stream()
                .map(notificationMapper::toDomain)
                .toList();

    }


}
