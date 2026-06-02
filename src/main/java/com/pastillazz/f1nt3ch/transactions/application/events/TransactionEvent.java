package com.pastillazz.f1nt3ch.transactions.application.events;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Builder
public record TransactionEvent(UUID id,
                               String email,
                               Long fromWalletId,
                               Long toWalletId,
                               BigDecimal amount,
                               CurrencyType currency,
                               Instant transactionDate,
                               TransactionType type,
                               TransactionStatus status,
                               String reason) {

    public static TransactionEvent success(Transaction transaction)
    {
        return TransactionEvent.builder()
                .id(transaction.id())
                .email(transaction.email())
                .fromWalletId(transaction.fromWalletId())
                .toWalletId(transaction.toWalletId())
                .amount(transaction.amount())
                .currency(transaction.currency())
                .transactionDate(transaction.transactionDate())
                .type(transaction.type())
                .status(transaction.status())
                .reason("Transaction completed successfully")
                .build();
    }
    public static TransactionEvent failure(Transaction transaction, String reason)
    {
        return TransactionEvent.builder()
                .id(transaction.id())
                .email(transaction.email())
                .fromWalletId(transaction.fromWalletId())
                .toWalletId(transaction.toWalletId())
                .amount(transaction.amount())
                .currency(transaction.currency())
                .transactionDate(transaction.transactionDate())
                .type(transaction.type())
                .status(TransactionStatus.FAILED)
                .reason(reason)
                .build();
    }
}
