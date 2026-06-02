package com.pastillazz.f1nt3ch.wallet.infrastructure.dto;

import com.pastillazz.f1nt3ch.common.domain.model.CurrencyType;

public record WalletRequest(String walletName,
                            CurrencyType type,
                            Long userId )
{
}
