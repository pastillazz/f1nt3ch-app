package com.pastillazz.f1nt3ch.wallet.infrastructure.controllers;

import com.pastillazz.f1nt3ch.wallet.application.useCases.WalletService;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.UpdateRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletRequest;
import com.pastillazz.f1nt3ch.wallet.infrastructure.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest request)
    {
        return ResponseEntity.ok(walletService.createWallet(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<WalletResponse> getWalletById(@PathVariable Long id)
    {
        return walletService.getWalletById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WalletResponse>> getAllWallets()
    {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id)
    {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update")
    public ResponseEntity<WalletResponse> updateWalletName(@RequestBody UpdateRequest request)
    {
        return ResponseEntity.ok(walletService.updateWalletName(request));
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<WalletResponse>> getAllWalletsByUserId(@PathVariable Long userId)
    {
        return ResponseEntity.ok(walletService.getAllWalletsByUserId(userId));
    }
}
