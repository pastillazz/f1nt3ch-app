package com.pastillazz.f1nt3ch.wallet.infrastructure.mapper;

import java.math.BigDecimal;

public record UpdateRequest(String walletName, BigDecimal balance) {
}
