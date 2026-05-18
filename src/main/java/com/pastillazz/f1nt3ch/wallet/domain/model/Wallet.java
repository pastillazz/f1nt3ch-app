package com.pastillazz.f1nt3ch.wallet.domain.model;

import com.pastillazz.f1nt3ch.common.CurrencyType;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record Wallet(Long id,
                     BigDecimal balance,
                     Long userId,
                     String walletName,
                     CurrencyType type) {
}
