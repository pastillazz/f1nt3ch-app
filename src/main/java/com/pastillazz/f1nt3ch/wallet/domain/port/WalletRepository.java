package com.pastillazz.f1nt3ch.wallet.domain.port;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    Wallet save(Wallet wallet);
    Optional<Wallet> findById(Long id);
    List<Wallet> findAll();
    void deleteById(Long id);
    List<Wallet> findAllByUserId(Long userId);
    Wallet updateWalletBalance( Wallet wallet);
    Wallet updateWalletName(Wallet wallet);

}
