package com.pastillazz.f1nt3ch.auditing.infrastructure.repositories;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.domain.port.AuditRepository;
import com.pastillazz.f1nt3ch.auditing.infrastructure.entities.AuditEntity;
import com.pastillazz.f1nt3ch.auditing.infrastructure.mapper.AuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuditRepositoryAdapter implements AuditRepository {

    private final MySQLAuditRepository mySQLAuditRepository;
    private final AuditMapper auditMapper;

    @Override
    public AuditLog save(AuditLog auditLog) {
        AuditEntity entity = auditMapper.toEntity(auditLog);
        AuditEntity savedEntity = mySQLAuditRepository.save(entity);
        return auditMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<AuditLog> findById(Long id) {
        return mySQLAuditRepository.findById(id)
                .map(auditMapper::toDomain);
    }

    @Override
    public List<AuditLog> findAll() {
        return mySQLAuditRepository.findAll().stream()
                .map(auditMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditLog> findByEntityName(String entityName) {
        return mySQLAuditRepository.findByEntityName(entityName).stream()
                .map(auditMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditLog> findByPerformedBy(String performedBy) {
        return mySQLAuditRepository.findByPerformedBy(performedBy).stream()
                .map(auditMapper::toDomain)
                .collect(Collectors.toList());
    }
}
