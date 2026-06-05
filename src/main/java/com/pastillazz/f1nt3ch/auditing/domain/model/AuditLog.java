package com.pastillazz.f1nt3ch.auditing.domain.model;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record AuditLog(
        UUID id,
        Long userId,
        UUID transactionId,
        String email,
        Long fromWalletId,
        Long toWalletId,
        BigDecimal amount,
        CurrencyType currency,
        Instant transactionDate,
        Instant auditDate,
        TransactionType type,
        String details,
        TransactionStatus status
) {
    public static AuditLog setTransactionAudit(TransactionEvent event) {
        return AuditLog.builder()
                .id(null)
                .userId(event.userId())
                .transactionId(event.id())
                .email(event.email())
                .fromWalletId(event.fromWalletId())
                .toWalletId(event.toWalletId())
                .amount(event.amount())
                .currency(event.currency())
                .transactionDate(event.transactionDate())
                .auditDate(Instant.now())
                .type(event.type())
                .details(event.reason())
                .status(event.status())
                .build();
    }
}
