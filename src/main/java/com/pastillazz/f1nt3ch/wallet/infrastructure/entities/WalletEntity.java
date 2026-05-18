package com.pastillazz.f1nt3ch.wallet.infrastructure.entities;

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
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_name", nullable = false)
    private String walletName;

    @Column(nullable = false)
    private BigDecimal balance;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "fromWallet", cascade = CascadeType.ALL)
    private List<TransactionEntity> outgoingTransactions;

    @OneToMany(mappedBy = "toWallet", cascade = CascadeType.ALL)
    private List<TransactionEntity> incomingTransactions;


    public WalletEntity(Long id, String s, BigDecimal balance, UserEntity user) {
    }
}
