package com.pastillazz.f1nt3ch.transactions.domain.model;
import com.pastillazz.f1nt3ch.common.CurrencyType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Builder
public record Transaction(UUID id,
                          Long fromWalletId,
                          Long toWalletId,
                          BigDecimal amount,
                          CurrencyType currency,
                          Instant transactionDate,
                          TransactionType type,
                          TransactionStatus status
                          ) {


}
