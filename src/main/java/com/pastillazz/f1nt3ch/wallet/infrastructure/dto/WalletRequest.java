package com.pastillazz.f1nt3ch.wallet.infrastructure.dto;

import java.math.BigDecimal;

public record WalletRequest(String walletName, BigDecimal balance,
                            Long userId ) {
}
