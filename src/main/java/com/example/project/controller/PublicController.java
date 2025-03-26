package com.example.project.controller;

import com.example.project.entity.Block;
import com.example.project.repo.BlockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private BlockRepo blockRepo;
    @GetMapping
    public List<Block> getBlockChain(){
       return blockRepo.findAll();
    }


}
