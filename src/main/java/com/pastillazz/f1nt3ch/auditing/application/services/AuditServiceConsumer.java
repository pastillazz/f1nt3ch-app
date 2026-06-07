package com.pastillazz.f1nt3ch.auditing.application.services;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.domain.port.AuditRepository;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuditServiceConsumer
{
    private final AuditRepository auditRepository;

    @KafkaListener(topics = "transfer-topic", groupId = ("audit-group"),
            concurrency = "6")
    public void saveAuditLog(TransactionEvent transactionEvent) {
        log.info("Received transaction event in audit: {}", transactionEvent);
        AuditLog auditLog = AuditLog.setTransactionAudit(transactionEvent);
        auditRepository.save(auditLog);
        log.info("Audit log saved: {}", auditLog);
    }
}

