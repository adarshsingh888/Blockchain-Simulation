package com.example.project.repo;

import com.example.project.entity.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NodeRepo extends MongoRepository<Node,String> {
}
