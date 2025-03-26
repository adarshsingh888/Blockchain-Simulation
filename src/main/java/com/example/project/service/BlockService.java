package com.example.project.service;

import com.example.project.entity.Block;
import com.example.project.entity.Node;
import com.example.project.repo.BlockRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockService {
    private final BlockRepo blockRepo;
    private final NodeService nodeService;

    public BlockService(BlockRepo blockRepo, NodeService nodeService) {
        this.blockRepo = blockRepo;
        this.nodeService = nodeService;
    }

    public Block createBlock(String nodeId) {
        Node minerNode = nodeService.getNodeById(nodeId);
        List<String> transactions = new ArrayList<>(minerNode.getMemoryPool());

        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions in memory pool for block creation");
        }

        Block newBlock = new Block();
        newBlock.setIndex(minerNode.getBlockchain().size() + 1);
        newBlock.setPreviousHash(minerNode.getBlockchain().isEmpty() ? "0" :
                minerNode.getBlockchain().get(minerNode.getBlockchain().size() - 1).getHash());

        newBlock.setTransactions(transactions);
        newBlock.setTimestamp(System.currentTimeMillis());
        newBlock.setNonce(0);
        newBlock.setHash(calculateBlockHash(newBlock));

        // Save block to miner’s local blockchain and DB
        minerNode.getBlockchain().add(newBlock);
        minerNode.getMemoryPool().clear();
        nodeService.updateNode(minerNode.getNodeId(), minerNode);
        blockRepo.save(newBlock);
        // Broadcast block to other nodes
        broadcastBlockToAllNodes(newBlock, nodeId);

        return newBlock;
    }

    private String calculateBlockHash(Block block) {
        String dataToHash = block.getIndex() + block.getPreviousHash() +
                block.getTransactions().toString() + block.getTimestamp() + block.getNonce();
        return DigestUtils.sha256Hex(dataToHash);
    }

    private void broadcastBlockToAllNodes(Block block, String creatorNodeId) {
        List<Node> allNodes = nodeService.getAllNodes();
        for (Node node : allNodes) {
            if (!node.getNodeId().equals(creatorNodeId)) {
                node.getVerifiedExternalBlocks().add(block);
                nodeService.updateNode(node.getNodeId(), node);
            }
        }
    }

    public void executeExternalBlocksForNode(String nodeId) {
        Node node = nodeService.getNodeById(nodeId);
        List<Block> externalBlocks = new ArrayList<>(node.getVerifiedExternalBlocks());

        for (Block block : externalBlocks) {
            // Verify previous hash
            String lastHash = node.getBlockchain().isEmpty() ? "0" :
                    node.getBlockchain().get(node.getBlockchain().size() - 1).getHash();

            if (!block.getPreviousHash().equals(lastHash)) {
                System.out.println("Invalid block from other node. Skipping...");
                continue;
            }

            // Verify block hash integrity
            String recalculatedHash = calculateBlockHash(block);
            if (!block.getHash().equals(recalculatedHash)) {
                System.out.println("Hash mismatch! Skipping block...");
                continue;
            }

            // Add block to local blockchain
            node.getBlockchain().add(block);
            System.out.println("Block from other node added to local blockchain.");
        }

        // Clear verified external blocks after processing
        node.getVerifiedExternalBlocks().clear();
        nodeService.updateNode(node.getNodeId(), node);
    }

}
