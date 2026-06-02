package com.pastillazz.f1nt3ch.auditing.domain.model;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record AuditLog(
        Long id,
        String entityName,
        Long entityId,
        String action,
        String performedBy,
        String details,
        LocalDateTime timestamp
) {}
