package com.pastillazz.f1nt3ch.auditing.application.services;

import com.pastillazz.f1nt3ch.auditing.domain.port.AuditRepository;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditResponse;
import com.pastillazz.f1nt3ch.auditing.infrastructure.mapper.AuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    public AuditResponse findAuditLogById(UUID id)
    {
        return auditRepository.findById(id).
                map(auditMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Audit log not found"));
    }

    public List<AuditResponse> findAllAuditLogs()
    {
        return auditRepository.findAll().stream()
                .map(auditMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AuditResponse findByTransactionId(UUID transactionId)
    {
        return auditRepository.findByTransactionId(transactionId)
                .map(auditMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Audit log not found"));
    }

    public List<AuditResponse> findByUserId(Long userId)
    {
        return auditRepository.findByUserId(userId).stream()
                .map(auditMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AuditResponse> findByEmail(String email)
    {
        return auditRepository.findByEmail(email).stream()
                .map(auditMapper::toResponse)
                .collect(Collectors.toList());
    }
}
