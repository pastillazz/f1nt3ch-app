package com.pastillazz.f1nt3ch.auditing.infrastructure.repositories;

import com.pastillazz.f1nt3ch.auditing.infrastructure.entities.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MySQLAuditRepository extends JpaRepository<AuditEntity, UUID>
{
    public Optional<AuditEntity> findByTransactionId(UUID transactionId);
    public List<AuditEntity> findByUserId(Long userId);
    public List<AuditEntity> findByEmail(String email);
}
