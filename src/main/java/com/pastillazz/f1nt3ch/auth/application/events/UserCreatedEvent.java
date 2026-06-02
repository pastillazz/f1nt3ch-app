package com.pastillazz.f1nt3ch.auth.application.events;

import lombok.Builder;

@Builder
public record UserCreatedEvent(
        String alias,
        String email,
        String title,
        String message
) {}
