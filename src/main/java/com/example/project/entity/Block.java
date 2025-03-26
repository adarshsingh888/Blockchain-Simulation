package com.example.project.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "blocks")
public class Block {
    @Id
    private String id;
    private int index;
    private long timestamp;
    private List<String> transactions;
    private String previousHash;
    private String hash;
    private int nonce;
    public Block(){
        this.transactions=new ArrayList<>();
    }


}
