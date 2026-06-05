package com.pastillazz.f1nt3ch.auditing.domain.port;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuditRepository
{
    AuditLog save(AuditLog auditLog);
    Optional<AuditLog> findById(UUID id);
    List<AuditLog> findAll();
    Optional<AuditLog> findByTransactionId(UUID transactionId);
    List<AuditLog> findByUserId(Long userId);
    List<AuditLog> findByEmail(String email);
}
