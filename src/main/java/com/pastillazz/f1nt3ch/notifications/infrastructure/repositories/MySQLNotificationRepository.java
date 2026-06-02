package com.pastillazz.f1nt3ch.notifications.infrastructure.repositories;

import com.pastillazz.f1nt3ch.notifications.infrastructure.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySQLNotificationRepository extends JpaRepository<NotificationEntity, Long>
{
    List<NotificationEntity> findAllByEmail(String email);
}
