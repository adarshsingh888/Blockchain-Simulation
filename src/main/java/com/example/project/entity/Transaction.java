package com.example.project.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String sender;
    private String receiver;
    private double amount;
    private long timestamp;

    @Override
    public String toString() {
        return id+" "+sender+" "+receiver+" "+amount+" "+timestamp;
    }
}
