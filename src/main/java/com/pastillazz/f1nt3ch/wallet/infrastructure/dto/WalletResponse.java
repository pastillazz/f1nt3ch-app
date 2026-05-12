package com.pastillazz.f1nt3ch.wallet.infrastructure.dto;

import java.math.BigDecimal;

public record WalletResponse(String walletName, BigDecimal balance) {
}
