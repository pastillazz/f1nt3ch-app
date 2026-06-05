package com.pastillazz.f1nt3ch.auditing.infrastructure.dto;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record AuditResponse(
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
) {}
