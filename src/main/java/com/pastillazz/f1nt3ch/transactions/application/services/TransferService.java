package com.pastillazz.f1nt3ch.transactions.application.services;

import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.port.TransactionRepository;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.TransactionMapper;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.TransactionRequestMapper;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.WalletMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService{
    private final TransactionRepository transactionRepository;
    private final TransactionRequestMapper requestMapper;
    private final TransactionMapper transactionMapper;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request)
    {
        Transaction transaction = requestMapper.toModel(request);
        log.info("Transaction created -Type: {}, Status: {}", transaction.type(),transaction.status());

        var fromWallet=walletRepository.findById(transaction.fromWalletId());
        var toWallet=walletRepository.findById(transaction.toWalletId());

            if(fromWallet.isEmpty())
            {
                log.error("Transaction failed -Origin wallet not found -Type: {}," +
                        " Status: {}", transaction.type(), TransactionStatus.FAILED);
                throw new RuntimeException("Origin wallet (fromWallet) not found");
            }

            if (fromWallet.get().balance().compareTo(request.amount())<0)
            {
                log.error("Transaction failed -Insufficient balance -Type: {}, " +
                        "Status: {}", transaction.type(), TransactionStatus.FAILED);
                throw new RuntimeException("Insufficient balance");
            }

            var fromWalletEntity = walletMapper.toEntity(fromWallet.get());
            fromWalletEntity.setBalance(fromWalletEntity.getBalance()
                    .subtract(transaction.amount()));

            walletRepository.updateWallet(walletMapper
                    .toModel(fromWalletEntity));

            if(toWallet.isEmpty())
            {
                log.error("Transaction failed -Destination wallet not found -Type: {}, Status: {}",
                        transaction.type(),TransactionStatus.FAILED);
                throw new RuntimeException("Destination wallet (toWallet) not found");
            }

            var toWalletEntity = walletMapper.toEntity(toWallet.get());
            toWalletEntity.setBalance(toWalletEntity.getBalance()
                    .add(transaction.amount()));

            walletRepository.updateWallet(walletMapper
                    .toModel(toWalletEntity));


        Transaction transactionCompleted = transactionRepository.create(transaction);
        return requestMapper.toResponse(transactionCompleted);


    }
}
