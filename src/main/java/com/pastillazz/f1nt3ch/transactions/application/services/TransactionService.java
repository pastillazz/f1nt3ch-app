package com.pastillazz.f1nt3ch.transactions.application.services;

import com.pastillazz.f1nt3ch.transactions.application.useCases.TransactionUseCase;
import com.pastillazz.f1nt3ch.transactions.domain.port.TransactionRepository;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.OperationWalletRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.TransactionRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {
    private final TransactionRepository transactionRepository;
    private final TransactionRequestMapper requestMapper;
    private final TransferService transferService;
    private final WalletOperationService walletOperationService;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
       return transferService.createTransaction(request);
    }

    @Override
    public TransactionResponse deposit(OperationWalletRequest request) {
        return walletOperationService.deposit(request);
    }


    public Optional<TransactionResponse> getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .map(requestMapper::toResponse);
    }

    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(requestMapper::toResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> getAllByToWalletId(Long toWalletId)
    {
        return transactionRepository.findAllByToWalletId(toWalletId)
                .stream()
                .map(requestMapper::toResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> getAllByFromWalletId(Long fromWalletId)
    {
        return transactionRepository.findAllByFromWalletId(fromWalletId)
                .stream()
                .map(requestMapper::toResponse)
                .toList();
    }


}



