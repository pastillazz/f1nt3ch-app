package com.pastillazz.f1nt3ch.auditing.application.services;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.domain.port.AuditRepository;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
@Slf4j
public class AuditServiceConsumer
{
    private final AuditRepository auditRepository;

    @KafkaListener(topics = "transfer_topic", groupId = ("audit-group"),
            concurrency = "6")
    public void saveAuditLog(TransactionEvent transactionEvent) {
        AuditLog auditLog = AuditLog.setTransactionAudit(transactionEvent);
        auditRepository.save(auditLog);
        log.info("Audit log saved: {}", auditLog);
    }
}

