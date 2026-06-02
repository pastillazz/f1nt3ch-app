package com.pastillazz.f1nt3ch.auditing.infrastructure.repositories;

import com.pastillazz.f1nt3ch.auditing.infrastructure.entities.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySQLAuditRepository extends JpaRepository<AuditEntity, Long> {
    List<AuditEntity> findByEntityName(String entityName);
    List<AuditEntity> findByPerformedBy(String performedBy);
}
