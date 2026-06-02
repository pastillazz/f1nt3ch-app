package com.pastillazz.f1nt3ch.transactions.infrastructure.dto;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        BigDecimal amount,
        Long fromWalletId,
        Long toWalletId,
        CurrencyType currency,
        TransactionType type,
        TransactionStatus status,
        Instant transactionDate
) {}
