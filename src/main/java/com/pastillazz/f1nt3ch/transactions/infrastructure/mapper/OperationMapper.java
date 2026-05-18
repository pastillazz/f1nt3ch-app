package com.pastillazz.f1nt3ch.transactions.infrastructure.mapper;

import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.OperationWalletRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class OperationMapper {
    public Transaction toModel(OperationWalletRequest request)
    {
        return new Transaction(
                null,
                request.fromWalletId(),
                request.fromWalletId(),
                request.amount(),
                request.currency(),
                Instant.now(),
                TransactionType.DEPOSIT,
                TransactionStatus.PENDING

        );
    }

    public TransactionResponse toResponse(Transaction transaction)
    {
        return new TransactionResponse(
                transaction.id(),
                transaction.amount(),
                transaction.fromWalletId(),
                transaction.toWalletId(),
                transaction.currency(),
                transaction.type(),
                transaction.status(),
                transaction.transactionDate()

        );
    }
}
