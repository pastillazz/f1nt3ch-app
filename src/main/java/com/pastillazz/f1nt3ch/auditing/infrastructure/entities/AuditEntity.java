package com.pastillazz.f1nt3ch.auditing.infrastructure.entities;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionStatus;
import com.pastillazz.f1nt3ch.transactions.domain.model.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private Long userId;

    private String email;

    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(name = "from_wallet_id")
    private Long fromWalletId;

    @Column(name = "to_wallet_id")
    private Long toWalletId;

    private BigDecimal amount;
    
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "audit_date")
    private Instant auditDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String details;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;



}
