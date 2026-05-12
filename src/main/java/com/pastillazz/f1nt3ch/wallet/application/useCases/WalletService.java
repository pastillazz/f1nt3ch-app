package com.pastillazz.f1nt3ch.wallet.application.useCases;

import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.domain.port.WalletRepository;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletResponse;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.RequestMapper;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final RequestMapper requestMapper;


    public WalletResponse createWallet(WalletRequest request) {
        Wallet savedWallet=walletRepository.save(requestMapper.toModel(request));
        return requestMapper.toResponse(savedWallet);
    }

    public Optional<WalletResponse> getWalletById(Long id) {
        return walletRepository.findById(id)
                .map(request->requestMapper.toResponse(request));
    }

    public List<WalletResponse> getAllWallets() {
        return walletRepository.findAll()
                .stream()
                .map(request->requestMapper.toResponse(request))
                .toList();
    }

    public WalletResponse updateWallet(Long id, UpdateRequest request) {
        Wallet wallet=requestMapper.toModel2(request);
        Wallet updatedWallet= walletRepository.updateWallet(id, wallet);
        return requestMapper.toResponse(updatedWallet);

    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    public List<WalletResponse> getAllWalletsByUserId(Long userId) {
        return walletRepository.findAllByUserId(userId)
                .stream()
                .map(r->requestMapper.toResponse(r))
                .toList();
    }
}
