package com.pastillazz.f1nt3ch.transactions.infrastructure.dto;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;

import java.math.BigDecimal;

public record OperationWalletRequest(
        Long fromWalletId,
        BigDecimal amount,
        CurrencyType currency,
        TransactionType type
        ) {
}
