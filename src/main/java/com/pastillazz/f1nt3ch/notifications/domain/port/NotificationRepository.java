package com.pastillazz.f1nt3ch.notifications.domain.port;

import com.pastillazz.f1nt3ch.notifications.domain.model.Notification;

import java.util.List;

public interface NotificationRepository
{
    Notification save(Notification notification);
    List<Notification> findAllByEmail(String email);
}
