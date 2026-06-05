package com.pastillazz.f1nt3ch.auditing.infrastructure.mapper;

import com.pastillazz.f1nt3ch.auditing.domain.model.AuditLog;
import com.pastillazz.f1nt3ch.auditing.infrastructure.dto.AuditResponse;
import com.pastillazz.f1nt3ch.auditing.infrastructure.entities.AuditEntity;
import org.springframework.stereotype.Component;

@Component
public class AuditMapper
{

    public AuditLog toDomain(AuditEntity entity) {

        return AuditLog.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .transactionId(entity.getTransactionId())
                .fromWalletId(entity.getFromWalletId())
                .toWalletId(entity.getToWalletId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .transactionDate(entity.getTransactionDate())
                .auditDate(entity.getAuditDate())
                .type(entity.getType())
                .details(entity.getDetails())
                .status(entity.getStatus())
                .build();
    }

    public AuditEntity toEntity(AuditLog domain)
    {

        return AuditEntity.builder()
                .id(null)
                .email(domain.email())
                .userId(domain.userId())
                .transactionId(domain.transactionId())
                .fromWalletId(domain.fromWalletId())
                .toWalletId(domain.toWalletId())
                .amount(domain.amount())
                .currency(domain.currency())
                .transactionDate(domain.transactionDate())
                .auditDate(domain.auditDate())
                .type(domain.type())
                .details(domain.details())
                .status(domain.status())
                .build();
    }
    public AuditResponse toResponse(AuditLog domain)
    {
        return AuditResponse.builder()
                .email(domain.email())
                .userId(domain.userId())
                .transactionId(domain.transactionId())
                .fromWalletId(domain.fromWalletId())
                .toWalletId(domain.toWalletId())
                .amount(domain.amount())
                .currency(domain.currency())
                .transactionDate(domain.transactionDate())
                .auditDate(domain.auditDate())
                .type(domain.type())
                .details(domain.details())
                .status(domain.status())
                .build();
    }
}
