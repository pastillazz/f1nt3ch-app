package com.pastillazz.f1nt3ch.auditing.infrastructure.mapper;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.infrastructure.entities.AuditEntity;
import org.springframework.stereotype.Component;

@Component
public class AuditMapper {

    public AuditLog toDomain(AuditEntity entity) {
        if (entity == null) return null;
        return AuditLog.builder()
                .id(entity.getId())
                .entityName(entity.getEntityName())
                .entityId(entity.getEntityId())
                .action(entity.getAction())
                .performedBy(entity.getPerformedBy())
                .details(entity.getDetails())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public AuditEntity toEntity(AuditLog domain) {
        if (domain == null) return null;
        return AuditEntity.builder()
                .id(domain.id())
                .entityName(domain.entityName())
                .entityId(domain.entityId())
                .action(domain.action())
                .performedBy(domain.performedBy())
                .details(domain.details())
                .timestamp(domain.timestamp())
                .build();
    }
}
