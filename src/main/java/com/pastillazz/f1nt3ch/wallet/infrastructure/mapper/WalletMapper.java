package com.pastillazz.f1nt3ch.wallet.infrastructure.mapper;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.infrastructure.entities.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletEntity toEntity(Wallet wallet) {
        return new WalletEntity(
                wallet.id(),
                wallet.balance(),
                wallet.userId()
        );
    }

    public Wallet toModel(WalletEntity entity) {
        return new Wallet(
                entity.getId(),
                entity.getBalance(),
                entity.getUserId()
        );
    }
}
