package com.example.project.controller;
import com.example.project.entity.Block;
import com.example.project.entity.Node;
import com.example.project.service.BlockService;
import com.example.project.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeService nodeService;
    @Autowired
    private BlockService blockService;

    // Create a new node
    @PostMapping
    public Node createNode(@RequestBody Node node) {
        return nodeService.createNode(node);
    }

    // Get all nodes
    @GetMapping
    public List<Node> getAllNodes() {
        return nodeService.getAllNodes();
    }

    // Get a node by ID
    @GetMapping("/{id}")
    public Node getNodeById(@PathVariable String id) {
        return nodeService.getNodeById(id);
    }

    // Delete a node by ID
    @DeleteMapping("/{id}")
    public String deleteNodeById(@PathVariable String id) {
        nodeService.deleteNodeById(id);
        return "Node deleted with id: " + id;
    }

    // Update node by ID
    @PutMapping("/{id}")
    public Node updateNode(@PathVariable String id, @RequestBody Node updatedNode) {
        return nodeService.updateNode(id, updatedNode);
    }
    // Endpoint for creating a new block by a miner node
    @PostMapping("/create/{nodeId}")
    public ResponseEntity<Block> createBlock(@PathVariable String nodeId) {
        Block createdBlock = blockService.createBlock(nodeId);
        return ResponseEntity.ok(createdBlock);
    }

    // Endpoint to execute and verify blocks received from other nodes
    @PostMapping("/execute/{nodeId}")
    public ResponseEntity<String> executeExternalBlocksForNode(@PathVariable String nodeId) {
        blockService.executeExternalBlocksForNode(nodeId);
        return ResponseEntity.ok("External verified blocks executed and added to local blockchain.");
    }

}
