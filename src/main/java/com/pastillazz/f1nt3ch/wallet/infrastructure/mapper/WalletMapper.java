package com.pastillazz.f1nt3ch.wallet.infrastructure.mapper;

import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.infrastructure.entities.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletMapper {

    public WalletEntity toEntity(Wallet wallet) {
        UserEntity user = UserEntity.builder()
                .id(wallet.userId())
                .build();
        return WalletEntity.builder()
                .id(wallet.id())
                .balance(wallet.balance())
                .user(user)
                .walletName(wallet.walletName())
                .build();
    }

    public Wallet toModel(WalletEntity entity) {
        return new Wallet(
                entity.getId(),
                entity.getBalance(),
                entity.getUser().getId(),
                entity.getWalletName()
        );
    }
}
