package com.ShoeInvent.ShoeInvent.entity;
import jakarta.persistence.*;

// Transactions Entity
@Entity
@Table(name = "Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @ManyToOne
    @JoinColumn(name = "TTID", nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "stockID", nullable = false)
    private Stock stock;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "timeStamp", nullable = false)
    private java.util.Date timestamp;

    @Column(name = "price", nullable = false)
    private String price;

    // Getters and Setters
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public java.util.Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
