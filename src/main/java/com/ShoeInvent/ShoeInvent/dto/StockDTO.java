package com.ShoeInvent.ShoeInvent.dto;


// StockDTO
public class StockDTO {

    private int stockId;
    private int productId;
    private int size;
    private java.util.Date timestamp;
    private int count;
    private java.math.BigDecimal stockPrice;

    // Getters and Setters
    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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