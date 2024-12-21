package com.ShoeInvent.ShoeInvent.entity;
import jakarta.persistence.*;

// Stock Entity
@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockID;

    @ManyToOne
    @JoinColumn(name = "PID", nullable = false)
    private ProductType productType;

    @Column(name = "Size", nullable = false)
    private int size;

    @Column(name = "Timestamp", nullable = false)
    private java.util.Date timestamp;

    @Column(name = "Count", nullable = false)
    private int count;

    @Column(name = "stockPrice", nullable = false)
    private java.math.BigDecimal stockPrice;

    // Getters and Setters
    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public java.util.Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public java.math.BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(java.math.BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
    }
}