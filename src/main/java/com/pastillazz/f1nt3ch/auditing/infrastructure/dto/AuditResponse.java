package com.pastillazz.f1nt3ch.auditing.infrastructure.dto;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record AuditResponse(
        Long id,
        String entityName,
        Long entityId,
        String action,
        String performedBy,
        String details,
        LocalDateTime timestamp
) {}
