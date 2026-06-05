package com.pastillazz.f1nt3ch.transactions.application.services;

import com.pastillazz.f1nt3ch.common.application.services.NotificationProducerService;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import com.pastillazz.f1nt3ch.transactions.application.validators.TransactionValidator;
import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.port.TransactionRepository;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.TransactionRequestMapper;
import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
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
    private final WalletRepository walletRepository;
    private final TransactionValidator transactionValidator;
    private final NotificationProducerService notificationProducerService;

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request)
    {
        Transaction transaction = requestMapper.toModel(request);
        log.info("Transaction created -Type: {}, Status: {}", transaction.type(),
                transaction.status());

        var fromWallet = transactionValidator.validateFromWallet(transaction);

        transactionValidator.validateUser(request.userId(), fromWallet, transaction);

        var toWallet=transactionValidator.validateToWallet(transaction);

        transactionValidator.validateInvalidAmount(transaction);

        transactionValidator.validateAmount(fromWallet, transaction);

        var updatedFromWallet = Wallet.builder()
                    .id(fromWallet.id())
                    .userId(fromWallet.userId())
                    .walletName(fromWallet.walletName())
                    .type(fromWallet.type())
                    .balance(fromWallet.balance().subtract(transaction.amount()))
                    .build();
        walletRepository.updateWalletBalance(updatedFromWallet);

        var updatedToWallet = Wallet.builder()
                .id(toWallet.id())
                .userId(toWallet.userId())
                .walletName(toWallet.walletName())
                .type(toWallet.type())
                .balance(toWallet.balance().add(transaction.amount()))
                .build();

            walletRepository.updateWalletBalance(updatedToWallet);

        Transaction transactionCompleted = transactionRepository.create(transaction);

        notificationProducerService.sendMessage("transfer-topic",transaction.id().toString(),
                TransactionEvent.success(transaction));

        return requestMapper.toResponse(transactionCompleted);

    }
}
