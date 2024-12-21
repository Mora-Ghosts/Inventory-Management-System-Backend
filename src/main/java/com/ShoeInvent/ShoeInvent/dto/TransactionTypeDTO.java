package com.ShoeInvent.ShoeInvent.dto;


// TransactionTypeDTO
public class TransactionTypeDTO {

    private int ttid;
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