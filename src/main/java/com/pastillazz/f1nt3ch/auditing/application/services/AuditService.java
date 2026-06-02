package com.pastillazz.f1nt3ch.auditing.application.services;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.domain.port.AuditRepository;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditRequest;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditResponse createAuditLog(AuditRequest request) {
        AuditLog auditLog = AuditLog.builder()
                .entityName(request.entityName())
                .entityId(request.entityId())
                .action(request.action())
                .performedBy(request.performedBy())
                .details(request.details())
                .timestamp(LocalDateTime.now())
                .build();
        AuditLog savedLog = auditRepository.save(auditLog);
        return mapToResponse(savedLog);
    }

    public List<AuditResponse> getAllAuditLogs() {
        return auditRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AuditResponse> getAuditLogsByEntity(String entityName) {
        return auditRepository.findByEntityName(entityName).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AuditResponse> getAuditLogsByUser(String performedBy) {
        return auditRepository.findByPerformedBy(performedBy).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AuditResponse getAuditLogById(Long id) {
        return auditRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Audit log not found"));
    }

    private AuditResponse mapToResponse(AuditLog auditLog) {
        return AuditResponse.builder()
                .id(auditLog.id())
                .entityName(auditLog.entityName())
                .entityId(auditLog.entityId())
                .action(auditLog.action())
                .performedBy(auditLog.performedBy())
                .details(auditLog.details())
                .timestamp(auditLog.timestamp())
                .build();
    }
}
