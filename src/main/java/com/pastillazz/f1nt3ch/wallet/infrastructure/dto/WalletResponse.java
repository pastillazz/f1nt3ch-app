package com.pastillazz.f1nt3ch.wallet.infrastructure.dto;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;

import java.math.BigDecimal;

public record WalletResponse(String walletName,
                             CurrencyType type,
                             BigDecimal balance) {
}
