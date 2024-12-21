package com.ShoeInvent.ShoeInvent.repository;
import com.ShoeInvent.ShoeInvent.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    // Additional query methods if needed
}
