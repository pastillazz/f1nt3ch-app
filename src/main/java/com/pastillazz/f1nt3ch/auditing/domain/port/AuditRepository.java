package com.pastillazz.f1nt3ch.auditing.domain.port;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import java.util.List;
import java.util.Optional;

public interface AuditRepository {
    AuditLog save(AuditLog auditLog);
    Optional<AuditLog> findById(Long id);
    List<AuditLog> findAll();
    List<AuditLog> findByEntityName(String entityName);
    List<AuditLog> findByPerformedBy(String performedBy);
}
