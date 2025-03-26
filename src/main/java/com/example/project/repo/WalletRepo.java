package com.example.project.repo;

import com.example.project.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepo extends MongoRepository<Wallet,String> {

}
