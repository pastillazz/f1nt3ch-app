package com.pastillazz.f1nt3ch.transactions.infrastructure.entities;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import com.pastillazz.f1nt3ch.wallet.infrastructure.entities.WalletEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    private UUID id;

    private BigDecimal amount;

    private String email;

    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @Column(name = "user_id")
    private Long userId;

    private Instant transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet_id")
    private WalletEntity fromWallet;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet_id")
    private WalletEntity toWallet;
}
