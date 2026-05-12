package com.pastillazz.f1nt3ch.wallet.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "wallet")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_name", nullable = false)
    private String walletName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(name = "user_id", nullable = false)
    private Long userId;



}
