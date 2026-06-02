package com.pastillazz.f1nt3ch.wallet.infrastructure.entities;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.infrastructure.entities.TransactionEntity;
import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_name", nullable = false)
    private String walletName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private CurrencyType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "fromWallet")
    private List<TransactionEntity> outgoingTransactions;

    @OneToMany(mappedBy = "toWallet")
    private List<TransactionEntity> incomingTransactions;



}
