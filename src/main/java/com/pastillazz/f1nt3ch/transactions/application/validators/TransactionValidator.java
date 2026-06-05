package com.pastillazz.f1nt3ch.transactions.application.validators;

import com.pastillazz.f1nt3ch.common.application.services.NotificationProducerService;
import com.pastillazz.f1nt3ch.transactions.application.events.TransactionEvent;
import com.pastillazz.f1nt3ch.transactions.domain.model.Transaction;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionValidator
{
    private final WalletRepository walletRepository;
     private final NotificationProducerService notificationProducerService;

    public Wallet validateFromWallet(Transaction transaction)
    {
        return walletRepository.findById(transaction.fromWalletId())
                .orElseThrow(()->
                {
                    log.error("Transaction failed -Origin wallet not found -Type: {}," +
                            " Status: {}", transaction.type(), TransactionStatus.FAILED);

                    String message="Origin wallet (fromWallet) not found";

                    notificationProducerService.sendMessage("transfer-topic",transaction.id().toString(),
                            TransactionEvent.failure(transaction, message));

                    return new RuntimeException(message);
                });
    }

    public Wallet validateToWallet(Transaction transaction)
    {
        return walletRepository.findById(transaction.toWalletId())
                .orElseThrow(()->
                {
                    log.error("Transaction failed -Destination wallet not found -Type: {}," +
                            " Status: {}", transaction.type(), TransactionStatus.FAILED);

                    String message="Destination wallet (toWallet) not found";

                    notificationProducerService.sendMessage("transfer-topic",transaction.id().toString(),
                            TransactionEvent.failure(transaction, message));

                    return new RuntimeException(message);
                });
    }

    public void validateUser(Long id, Wallet wallet, Transaction transaction)
    {
        if (!wallet.userId().equals(id))
        {
            log.error("Transaction failed -User not authorized -Type: {}, " +
                    "Status: {}", transaction.type(), TransactionStatus.FAILED);

            String message="User not authorized to perform this transaction";

            notificationProducerService.sendMessage("transfer-topic",wallet.id().toString(),
                    TransactionEvent.failure(transaction, message));

            throw new RuntimeException(message);
        }
    }

    public void validateAmount(Wallet fromWallet, Transaction transaction)
    {
        if (fromWallet.balance().compareTo(transaction.amount())<0)
        {
            log.error("Transaction failed -Insufficient balance -Type: {}, " +
                    "Status: {}", transaction.type(), TransactionStatus.FAILED);

            String message="Insufficient balance";

            notificationProducerService.sendMessage("transfer-topic",transaction.id().toString(),
                    TransactionEvent.failure(transaction, message));

            throw new RuntimeException(message);
        }
    }

    public void validateInvalidAmount(Transaction transaction)
    {
        if (transaction.amount().compareTo(BigDecimal.ZERO)<=0)
        {
            log.error("Transaction failed -Invalid amount -Type: {}, " +
                    "Status: {}", transaction.type(), TransactionStatus.FAILED);
            throw new RuntimeException("Invalid amount");
        }
        String message="Invalid amount";

        notificationProducerService.sendMessage("transfer-topic",transaction.id().toString(),
            TransactionEvent.failure(transaction, message));
    }

}
