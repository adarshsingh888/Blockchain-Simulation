package com.example.project.service;
import com.example.project.entity.Node;
import com.example.project.repo.NodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    @Autowired
    private NodeRepo nodeRepo;

    // Create or Save Node
    public Node createNode(Node node) {
        return nodeRepo.save(node);
    }

    // Get all Nodes
    public List<Node> getAllNodes() {
        return nodeRepo.findAll();
    }

    // Get Node by ID
    public Node getNodeById(String id) {
        return nodeRepo.findById(id).get();
    }

    // Delete Node by ID
    public void deleteNodeById(String id) {
        nodeRepo.deleteById(id);
    }

    // Update Node
    public Node updateNode(String id, Node updatedNode) {
        if (nodeRepo.existsById(id)) {
            updatedNode.setNodeId(id);
            return nodeRepo.save(updatedNode);
        } else {
            throw new RuntimeException("Node not found with id: " + id);
        }
    }
}
