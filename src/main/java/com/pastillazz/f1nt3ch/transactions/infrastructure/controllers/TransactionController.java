package com.pastillazz.f1nt3ch.transactions.infrastructure.controllers;

import com.pastillazz.f1nt3ch.transactions.application.services.TransactionService;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.OperationWalletRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionRequest;
import com.pastillazz.f1nt3ch.transactions.infrastructure.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<TransactionResponse>
    createTransaction(@RequestBody TransactionRequest request)
    {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable UUID id)
    {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionResponse>> getAllTransactions()
    {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/get/wallet/{fromWalletId}")
    public ResponseEntity<List<TransactionResponse>>
    getAllByFromWalletId(@PathVariable Long fromWalletId)
    {
        return ResponseEntity.ok(transactionService.getAllByFromWalletId(fromWalletId));
    }

    @GetMapping("/get/wallet/to/{toWalletId}")
    public ResponseEntity<List<TransactionResponse>>
    getAllByToWalletId(@PathVariable Long toWalletId)
    {
        return ResponseEntity.ok(transactionService.getAllByToWalletId(toWalletId));
    }

    @PostMapping("/deposit")
    public  ResponseEntity<TransactionResponse>
    deposit(@RequestBody OperationWalletRequest request)
    {
        return ResponseEntity.ok(transactionService.deposit(request));
    }




}
