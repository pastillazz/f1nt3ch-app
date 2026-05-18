package com.pastillazz.f1nt3ch.transactions.application.services;

import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import com.pastillazz.f1nt3ch.transactions.domain.port.TransactionRepository;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.OperationWalletRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.OperationMapper;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.TransactionMapper;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletOperationService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final OperationMapper requestMapper;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public TransactionResponse deposit(OperationWalletRequest request)
    {
        Transaction transaction = requestMapper.toModel(request);
        log.info(" Deposit Operation created -Type: {}, Status: {}",
                TransactionType.DEPOSIT,transaction.status());

        var toWallet=walletRepository.findById(transaction.fromWalletId());

        if(toWallet.isEmpty())
        {
            log.error("Transaction failed -Destination wallet not found -Type: {}," +
                    " Status: {}", transaction.type(), TransactionStatus.FAILED);
            throw new RuntimeException("wallet not found");
        }

        if (request.amount().compareTo(BigDecimal.ZERO)<=0)
        {
            log.error("Transaction failed -Invalid amount -Type: {}, " +
                    "Status: {}", transaction.type(), TransactionStatus.FAILED);
            throw new RuntimeException("Invalid amount");
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
