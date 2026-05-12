package com.pastillazz.f1nt3ch.wallet.domain.model;

import java.math.BigDecimal;

public record Wallet(Long id,
                     BigDecimal balance,
                     Long userId,
                     String walletName) {
}
