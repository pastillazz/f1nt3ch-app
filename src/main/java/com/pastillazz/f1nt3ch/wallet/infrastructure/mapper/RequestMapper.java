package com.pastillazz.f1nt3ch.wallet.infrastructure.mapper;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletResponse;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public Wallet toModel(WalletRequest request){
        return new Wallet( null,
                request.balance(),
                request.userId(),
                request.walletName());
    }
    public WalletResponse toResponse(Wallet wallet){
        return new WalletResponse(wallet.walletName(), wallet.balance());

    }
    public Wallet toModel2(UpdateRequest request){
        return new Wallet(null,
                request.balance(),
                null,
                request.walletName());
    }
}
