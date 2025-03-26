package com.example.project.controller;

import com.example.project.entity.Wallet;
import com.example.project.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    // Create a new wallet
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        Wallet createdWallet = walletService.createWallet(wallet);
        return ResponseEntity.ok(createdWallet);
    }

    // Get all wallets
    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return ResponseEntity.ok(wallets);
    }

    // Get wallet by ID
    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable String walletId) {
        return walletService.getWalletById(walletId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update wallet by ID
    @PutMapping("/{walletId}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable String walletId, @RequestBody Wallet wallet) {
        try {
            Wallet updatedWallet = walletService.updateWallet(walletId, wallet);
            return ResponseEntity.ok(updatedWallet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete wallet by ID
    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable String walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }
}
