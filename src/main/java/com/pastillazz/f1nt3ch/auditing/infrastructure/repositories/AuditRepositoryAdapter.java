package com.pastillazz.f1nt3ch.auditing.infrastructure.repositories;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.domain.port.AuditRepository;
import com.pastillazz.f1nt3ch.auditing.infrastructure.entities.AuditEntity;
import com.pastillazz.f1nt3ch.auditing.infrastructure.mapper.AuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AuditRepositoryAdapter implements AuditRepository
{

    private final MySQLAuditRepository mySQLAuditRepository;
    private final AuditMapper auditMapper;

    @Override
    public AuditLog save(AuditLog auditLog) {
        AuditEntity entity = auditMapper.toEntity(auditLog);
        AuditEntity savedEntity = mySQLAuditRepository.save(entity);
        return auditMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<AuditLog> findById(UUID id)
    {
        return mySQLAuditRepository.findById(id)
                .map(auditMapper::toDomain);
    }

    @Override
    public List<AuditLog> findAll()
    {
        return mySQLAuditRepository.findAll().stream()
                .map(auditMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuditLog> findByTransactionId(UUID transactionId)
    {
        return mySQLAuditRepository.findByTransactionId(transactionId)
                .map(auditMapper::toDomain);
    }

    @Override
    public List<AuditLog> findByUserId(Long userId)
    {
        return mySQLAuditRepository.findByUserId(userId).stream()
                .map(auditMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditLog> findByEmail(String email)
    {
        return mySQLAuditRepository.findByEmail(email).stream()
                .map(auditMapper::toDomain)
                .collect(Collectors.toList());
    }


}
