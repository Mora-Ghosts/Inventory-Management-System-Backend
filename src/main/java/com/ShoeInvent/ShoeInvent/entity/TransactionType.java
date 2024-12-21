package com.ShoeInvent.ShoeInvent.entity;
import jakarta.persistence.*;

// TransactionType Entity
@Entity
@Table(name = "TransactionType")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ttid;

    @Column(name = "TransactionType", nullable = false)
    private String transactionType;

    // Getters and Setters
    public int getTtid() {
        return ttid;
    }

    public void setTtid(int ttid) {
        this.ttid = ttid;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
