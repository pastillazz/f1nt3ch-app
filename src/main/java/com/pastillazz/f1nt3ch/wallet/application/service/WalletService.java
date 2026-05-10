package com.pastillazz.f1nt3ch.wallet.application.service;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWalletById(Long id) {
        return walletRepository.findById(id);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet updateWallet(Long id, Wallet wallet) {
        return walletRepository.findById(id)
                .map(existingWallet -> {
                    Wallet updatedWallet = new Wallet(existingWallet.id(), wallet.balance(), wallet.userId());
                    return walletRepository.save(updatedWallet);
                }).orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id));
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    public Optional<Wallet> getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }
}
