package com.ShoeInvent.ShoeInvent.dto;

import java.math.BigDecimal;

public class StockValueDTO {
    private String category;
    private String productType;
    private BigDecimal totalValue;

    // Default Constructor
    public StockValueDTO() {
    } 
    // Constructor matching the query
    public StockValueDTO(String category, String productType, BigDecimal totalValue) {
        this.category = category;
        this.productType = productType;
        this.totalValue = totalValue;
    }

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
