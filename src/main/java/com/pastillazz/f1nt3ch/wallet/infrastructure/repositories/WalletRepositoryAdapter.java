package com.pastillazz.f1nt3ch.wallet.infrastructure.repositories;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import com.pastillazz.f1nt3ch.wallet.infrastructure.entities.WalletEntity;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class WalletRepositoryAdapter implements WalletRepository {
    private final MySQLWalletRepository mySQLWalletRepository;
    private final WalletMapper walletMapper;

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity entity = walletMapper.toEntity(wallet);
        WalletEntity savedEntity = mySQLWalletRepository.save(entity);
        return walletMapper.toModel(savedEntity);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return mySQLWalletRepository.findById(id)
                .map(walletMapper::toModel);
    }

    @Override
    public List<Wallet> findAll() {
        return mySQLWalletRepository.findAll().stream()
                .map(walletMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        mySQLWalletRepository.deleteById(id);
    }

    @Override
    public Optional<Wallet> findByUserId(Long userId) {
        return mySQLWalletRepository.findByUserId(userId)
                .map(walletMapper::toModel);
    }

    @Override
    public List<Wallet> findAllByUserId(Long userId) {
        return mySQLWalletRepository.findAllByUserId(userId)
                .stream()
                .map(wallets-> walletMapper.toModel(wallets))
                .toList();
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) {
        return (mySQLWalletRepository.findById(id)
                .map(existingWallet -> {
                    WalletEntity updateWallet = new WalletEntity(existingWallet.getId(),
                            wallet.walletName(),
                            wallet.balance(),
                            existingWallet.getUserId());
                    mySQLWalletRepository.save(updateWallet);
                    return walletMapper.toModel(updateWallet);
                }).orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id)));

    }

}
