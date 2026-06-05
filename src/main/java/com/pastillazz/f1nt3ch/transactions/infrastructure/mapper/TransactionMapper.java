package com.pastillazz.f1nt3ch.transactions.infrastructure.mapper;

import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.infrastructure.entities.TransactionEntity;
import com.pastillazz.f1nt3ch.wallet.infrastructure.entities.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(Transaction transaction)
    {
        var fromWallet= WalletEntity.builder()
                .id(transaction.fromWalletId())
                .build();
        var toWallet= WalletEntity.builder()
                .id(transaction.toWalletId())
                .build();

        return TransactionEntity.builder()
                .email(transaction.email())
                .id(transaction.id())
                .userId(transaction.userId())
                .fromWallet(fromWallet)
                .toWallet(toWallet)
                .amount(transaction.amount())
                .currency(transaction.currency())
                .transactionDate(transaction.transactionDate())
                .type(transaction.type())
                .status(TransactionStatus.COMPLETED)
                .build();
    }

    public Transaction toModel(TransactionEntity entity)
    {
        return new Transaction(
                entity.getId(),
                entity.getUserId(),
                entity.getEmail(),
                entity.getFromWallet().getId(),
                entity.getToWallet().getId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getTransactionDate(),
                entity.getType(),
                entity.getStatus()

        );
    }
}
