package com.example.project.service;

import com.example.project.entity.Node;
import com.example.project.entity.Transaction;
import com.example.project.repo.MemoryPoolRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TransactionService {

    private final MemoryPoolRepo memoryPoolRepo;
    private final NodeService nodeService;

    public TransactionService(MemoryPoolRepo memoryPoolRepo, NodeService nodeService) {
        this.memoryPoolRepo = memoryPoolRepo;
        this.nodeService = nodeService;
    }

    public void addTransaction(Transaction transaction) {
        broadcastTransactionToAllNodes(transaction.toString());
        memoryPoolRepo.save(transaction);
    }
    public void broadcastTransactionToAllNodes(String transactionData) {
        List<Node> allNodes = nodeService.getAllNodes();
        for (Node node : allNodes) {
            node.getMemoryPool().add(transactionData);
            nodeService.updateNode(node.getNodeId(),node);
        }
    }


    public List<Transaction> getAllTranstaion(){
        return memoryPoolRepo.findAll();
    }
    public List<Transaction> getTopFiveTransactions() {
        return memoryPoolRepo.findTop5ByOrderByAmountDesc();
    }
}
