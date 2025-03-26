package com.example.project.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Wallets")
public class Wallet {
    @Id
    private String walletId;                // Unique ID for the node (user)
    private String publicKey;               // Public key for verifying transactions
    private String privateKey;              // Private key (simulated for signing)
    private Double balance;



    public Wallet(String walletId, String publicKey, String privateKey,Double balance) {
        this.walletId = walletId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.balance=balance;

    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId='" + walletId + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", balance='"+balance+ '\''+
                '}';
    }
}

