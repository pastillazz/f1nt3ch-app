package com.pastillazz.f1nt3ch.wallet.application.useCases;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.UpdateRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletResponse;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final RequestMapper requestMapper;

    public WalletResponse createWallet(WalletRequest request)
    {
        Wallet savedWallet=walletRepository.save(requestMapper.toModel(request));
        return requestMapper.toResponse(savedWallet);
    }

    public Optional<WalletResponse> getWalletById(Long id)
    {
        return walletRepository.findById(id)
                .map(requestMapper::toResponse);
    }

    public List<WalletResponse> getAllWallets()
    {
        return walletRepository.findAll()
                .stream()
                .map(requestMapper::toResponse)
                .toList();
    }

    public WalletResponse updateWalletName(UpdateRequest request)
    {
        Wallet updatedWallet=walletRepository
                .updateWalletName(requestMapper.toUpdateModel(request));
        return requestMapper.toResponse(updatedWallet);
    }
    public void deleteWallet(Long id)
    {
        walletRepository.deleteById(id);
    }

    public List<WalletResponse> getAllWalletsByUserId(Long userId)
    {
        return walletRepository.findAllByUserId(userId)
                .stream()
                .map(requestMapper::toResponse)
                .toList();
    }
}
