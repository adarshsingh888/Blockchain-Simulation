package com.example.project.repo;

import com.example.project.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemoryPoolRepo extends MongoRepository<Transaction,String> {
    List<Transaction> findTop5ByOrderByAmountDesc();
}
