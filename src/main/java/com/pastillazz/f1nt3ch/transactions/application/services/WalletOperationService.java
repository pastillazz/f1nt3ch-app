package com.pastillazz.f1nt3ch.transactions.application.services;

import com.pastillazz.f1nt3ch.common.application.services.NotificationProducerService;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import com.pastillazz.f1nt3ch.transactions.application.validators.TransactionValidator;
import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import com.pastillazz.f1nt3ch.transactions.domain.port.TransactionRepository;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.OperationWalletRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import com.pastillazz.f1nt3ch.transactions.infrastructure.mapper.OperationMapper;
import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class WalletOperationService {
    private final TransactionRepository transactionRepository;
    private final OperationMapper requestMapper;
    private final TransactionValidator transactionValidator;
    private final WalletRepository walletRepository;
    private final NotificationProducerService notificationProducerService;

    public TransactionResponse deposit(OperationWalletRequest request)
    {
        Transaction transaction = requestMapper.toModel(request);
        log.info(" Deposit Operation created -Type: {}, Status: {}",
                TransactionType.DEPOSIT,transaction.status());

        var fromWallet=transactionValidator.validateFromWallet(transaction);

        transactionValidator.validateUser(request.userId(), fromWallet, transaction);

        transactionValidator.validateInvalidAmount(transaction);

        var updatedFromWallet = Wallet.builder()
                .id(fromWallet.id())
                .userId(fromWallet.userId())
                .walletName(fromWallet.walletName())
                .type(fromWallet.type())
                .balance(fromWallet.balance().subtract(transaction.amount()))
                .build();

        walletRepository.updateWalletBalance(updatedFromWallet);

        Transaction transactionCompleted = transactionRepository.create(transaction);

        notificationProducerService.sendMessage("transfer-topic",transaction.id().toString(),
                TransactionEvent.success(transaction));

        return requestMapper.toResponse(transactionCompleted);

    }

}
