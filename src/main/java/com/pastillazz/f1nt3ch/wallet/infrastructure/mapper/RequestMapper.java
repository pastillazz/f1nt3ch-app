package com.pastillazz.f1nt3ch.wallet.infrastructure.mapper;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.UpdateRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestMapper
{
    public Wallet toModel(WalletRequest request)
    {
        return new Wallet( null,
                BigDecimal.ZERO,
                request.userId(),
                request.walletName(),
                request.type()
                );
    }

    public WalletResponse toResponse(Wallet wallet)
    {
        return new WalletResponse(wallet.walletName(),
                wallet.type(),
                wallet.balance());
    }
    public Wallet toUpdateModel(UpdateRequest request)
    {
        var wallet=Wallet.builder()
                .id(request.id())
                .walletName(request.walletName())
                .build();
        return  wallet;
    }
}
