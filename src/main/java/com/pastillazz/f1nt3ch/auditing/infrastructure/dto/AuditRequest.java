package com.pastillazz.f1nt3ch.auditing.infrastructure.dto;

import lombok.Builder;

@Builder
public record AuditRequest(
        String entityName,
        Long entityId,
        String action,
        String performedBy,
        String details
) {}
