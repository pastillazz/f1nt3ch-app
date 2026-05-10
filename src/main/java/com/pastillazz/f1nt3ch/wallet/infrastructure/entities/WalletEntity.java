package com.pastillazz.f1nt3ch.wallet.infrastructure.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    public WalletEntity() {
    }

    public WalletEntity(Long id, BigDecimal balance, Long userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
