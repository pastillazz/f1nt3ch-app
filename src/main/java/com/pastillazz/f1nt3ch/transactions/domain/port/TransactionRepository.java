package com.pastillazz.f1nt3ch.transactions.domain.port;

import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction create(Transaction transaction);

    Optional<Transaction> findById(UUID id);

    List<Transaction> findAll();

    List<Transaction> findAllByFromWalletId(Long fromWalletId);

    List<Transaction> findAllByToWalletId(Long toWalletId);


}
