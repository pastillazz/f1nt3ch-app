package com.pastillazz.f1nt3ch.transactions.application.useCases;

import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.OperationWalletRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionUseCase {
    public TransactionResponse createTransaction(TransactionRequest request);
    public TransactionResponse deposit(OperationWalletRequest request);
    public Optional<TransactionResponse> getTransactionById(UUID id);
    public List<TransactionResponse> getAllTransactions();
    public List<TransactionResponse> getAllByToWalletId(Long toWalletId);
    public List<TransactionResponse> getAllByFromWalletId(Long fromWalletId);
}
