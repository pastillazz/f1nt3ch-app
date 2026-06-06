package com.pastillazz.f1nt3ch.wallet.application.useCases;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet)
    {
        return walletRepository.save(wallet);
    }

    public Wallet getWalletById(Long id)
    {
        return walletRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Wallet not found with id: "+id));
    }

    public List<Wallet> getAllWallets()
    {
        return walletRepository.findAll();

    }

    public Wallet updateWalletName(Wallet wallet)
    {
        return walletRepository
                .updateWalletName(wallet);
    }
    public void deleteWallet(Long id)
    {
        walletRepository.deleteById(id);
    }

    public List<Wallet> getAllWalletsByUserId(Long userId)
    {
        return walletRepository.findAllByUserId(userId);

    }
}
