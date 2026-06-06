package com.pastillazz.f1nt3ch.wallet.infrastructure.controllers;

import com.pastillazz.f1nt3ch.wallet.application.useCases.WalletService;
import com.pastillazz.f1nt3ch.wallet.domain.model.Wallet;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.UpdateRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletResponse;
import com.pastillazz.f1nt3ch.wallet.infrastructure.mapper.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final RequestMapper requestMapper;

    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest request)
    {
        Wallet wallet=requestMapper.toModel(request);
        Wallet createdWallet = walletService.createWallet(wallet);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(wallet.id())
                .toUri();
        return ResponseEntity.created(location).body(requestMapper.toResponse(createdWallet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> getWalletById(@PathVariable Long id)
    {
        return ResponseEntity.ok(requestMapper
                .toResponse(walletService.getWalletById(id)));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<WalletResponse>> getAllWallets()
    {
        return ResponseEntity.ok(walletService.getAllWallets()
                .stream()
                .map(requestMapper::toResponse)
                .toList());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id)
    {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update")
    public ResponseEntity<WalletResponse> updateWalletName(@RequestBody UpdateRequest request)
    {  Wallet wallet=requestMapper.toUpdateModel(request);

        return ResponseEntity.ok(requestMapper.toResponse(walletService
                .updateWalletName(wallet)));
    }

    @GetMapping("/find/user/{userId}")
    public ResponseEntity<List<WalletResponse>> getAllWalletsByUserId(@PathVariable Long userId)
    {
        return ResponseEntity.ok(walletService
                .getAllWalletsByUserId(userId)
                .stream()
                .map(requestMapper::toResponse)
                .toList());
    }
}
