package com.example.project.controller;

import com.example.project.entity.Transaction;
import com.example.project.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
        return transaction;
    }
    @GetMapping
    public Iterable<Transaction> getAllTransaction(){
        return transactionService.getAllTranstaion();
    }
    @GetMapping("/topFive")
    public Iterable<Transaction> getTopFiveTransactions() {
        return transactionService.getTopFiveTransactions();
    }
}
