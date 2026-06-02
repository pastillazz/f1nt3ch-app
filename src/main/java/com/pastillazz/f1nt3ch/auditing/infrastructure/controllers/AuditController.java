package com.pastillazz.f1nt3ch.auditing.infrastructure.controllers;

import com.pastillazz.f1nt3ch.auditing.application.services.AuditService;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditRequest;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditing")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping
    public ResponseEntity<AuditResponse> create(@RequestBody AuditRequest request) {
        return ResponseEntity.ok(auditService.createAuditLog(request));
    }

    @GetMapping
    public ResponseEntity<List<AuditResponse>> getAll() {
        return ResponseEntity.ok(auditService.getAllAuditLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.getAuditLogById(id));
    }

    @GetMapping("/entity/{entityName}")
    public ResponseEntity<List<AuditResponse>> getByEntity(@PathVariable String entityName) {
        return ResponseEntity.ok(auditService.getAuditLogsByEntity(entityName));
    }

    @GetMapping("/user/{performedBy}")
    public ResponseEntity<List<AuditResponse>> getByUser(@PathVariable String performedBy) {
        return ResponseEntity.ok(auditService.getAuditLogsByUser(performedBy));
    }
}
