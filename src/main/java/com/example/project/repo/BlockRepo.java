package com.example.project.repo;

import com.example.project.entity.Block;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockRepo extends MongoRepository<Block, String> {
    Block findTopByOrderByIndexDesc();
}
