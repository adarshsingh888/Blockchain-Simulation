package com.example.project.service;

import com.example.project.entity.Wallet;
import com.example.project.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepo;

    // Create a new wallet node
    public Wallet createWallet(Wallet wallet) {
        return walletRepo.save(wallet);
    }

    // Get all wallet nodes
    public List<Wallet> getAllWallets() {
        return walletRepo.findAll();
    }

    // Get wallet node by ID
    public Optional<Wallet> getWalletById(String walletId) {
        return walletRepo.findById(walletId);
    }

    // Update wallet node
    public Wallet updateWallet(String walletId, Wallet updatedWallet) {
        return walletRepo.findById(walletId)
                .map(existingNode -> {
                    existingNode.setPublicKey(updatedWallet.getPublicKey());
                    existingNode.setPrivateKey(updatedWallet.getPrivateKey());
                    existingNode.setBalance(updatedWallet.getBalance());
                    return walletRepo.save(existingNode);
                })
                .orElseThrow(() -> new RuntimeException("Wallet not found with ID: " + walletId));
    }

    // Delete wallet node by ID
    public void deleteWallet(String walletId) {
        walletRepo.deleteById(walletId);
    }
}
