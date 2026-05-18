package com.pastillazz.f1nt3ch.transactions.infrastructure.repositories;

import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.port.TransactionRepository;
import com.pastillazz.f1nt3ch.transactions.infrastructure.entities.TransactionEntity;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {
    private final MySQLTransactionRepository mySQLTransactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction create(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        TransactionEntity savedEntity = mySQLTransactionRepository.save(entity);
        return transactionMapper.toModel(savedEntity);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return mySQLTransactionRepository.findById(id)
                .map(transactionMapper::toModel);
    }

    @Override
    public List<Transaction> findAll() {
        return mySQLTransactionRepository.findAll().stream()
                .map(transactionMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findAllByFromWalletId(Long walletId) {
        return mySQLTransactionRepository.findAllByFromWalletId(walletId)
                .stream()
                .map(transactionMapper::toModel).toList();

    }

    @Override
    public List<Transaction> findAllByToWalletId(Long toWalletId) {
        return mySQLTransactionRepository.findAllByToWalletId(toWalletId)
                .stream()
                .map(transactionMapper::toModel).toList();
    }


}
