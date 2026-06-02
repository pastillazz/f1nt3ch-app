package com.pastillazz.f1nt3ch.notifications.infrastructure.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record NotificationResponse(
        String title,
        String message,
        Instant createdAt
) {}
