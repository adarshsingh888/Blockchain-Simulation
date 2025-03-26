package com.example.project.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document(collection = "Nodes")
public class Node {
    @Id
    private String nodeId;
    private List<Block> blockchain;
    private List<String> memoryPool;   // ✅ Add MemoryPool to each node
    private List<String> nodeType;
    private List<Block> verifiedExternalBlocks;

    public Node(String nodeId) {
        this.nodeId = nodeId;
        this.blockchain = new ArrayList<>();
        this.verifiedExternalBlocks=new ArrayList<>();
        this.memoryPool = new ArrayList<>();  // Each node initializes its own memory pool
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId='" + nodeId + '\'' +
                ", blockchain size=" + blockchain.size() +
                ", memoryPool size=" + memoryPool.toString() +
                '}';
    }
}
