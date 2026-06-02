package com.pastillazz.f1nt3ch.notifications.domain.model;

import com.pastillazz.f1nt3ch.auth.application.events.UserCreatedEvent;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import lombok.Builder;

import java.time.Instant;

@Builder
public record Notification(
        Long id,
        String email,
        String title,
        String message,
        Instant createdAt
) {
    public static Notification setUserNotification(UserCreatedEvent event)
    {
        return Notification.builder()
                .email(event.email())
                .title(event.title())
                .message(event.message())
                .createdAt(Instant.now())
                .build();
    }
    public static Notification setTransactionNotification(TransactionEvent event)
    {

        return Notification.builder()
                .email(event.email())
                .title("Transaction Notification")
                .message(event.reason())
                .createdAt(Instant.now())
                .build();
    }
}
