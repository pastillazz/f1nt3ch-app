package com.pastillazz.f1nt3ch.auditing.infrastructure.controllers;

import com.pastillazz.f1nt3ch.auditing.application.services.AuditService;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auditing")
@RequiredArgsConstructor
public class AuditController
{

    private final AuditService auditService;

    @GetMapping("/auditId/{id}")
     public ResponseEntity<AuditResponse> getAuditLogById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(auditService.findAuditLogById(id));
    }

    @GetMapping("/all")
     public ResponseEntity<List<AuditResponse>> getAllAuditLogs()
     {
         return ResponseEntity.ok(auditService.findAllAuditLogs());
     }

    @GetMapping("/all/transactionId/{transactionId}")
     public ResponseEntity<AuditResponse> getAuditLogByTransactionId
            (@PathVariable UUID transactionId)
     {
         return ResponseEntity.ok(auditService.findByTransactionId(transactionId));
     }
    @GetMapping("/all/userId/{userId}")
     public ResponseEntity<List<AuditResponse>> getAuditLogsByUserId
            (@PathVariable Long userId)
     {
         return ResponseEntity.ok(auditService.findByUserId(userId));
     }

     @GetMapping("/all/email")
     public ResponseEntity<List<AuditResponse>> getAuditLogsByEmail(@RequestParam String email)
     {
         return ResponseEntity.ok(auditService.findByEmail(email));
     }
}
