package com.ShoeInvent.ShoeInvent.repository;
 

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ShoeInvent.ShoeInvent.dto.StockValueDTO;
import com.ShoeInvent.ShoeInvent.entity.Stock;
 

@Repository
public interface AnalyticsRepository extends CrudRepository<Stock, Integer> {

    @Query("SELECT new com.ShoeInvent.ShoeInvent.dto.StockValueDTO(c.categoryName, pt.model, SUM(s.count * s.stockPrice)) " +
       "FROM Category c " +
       "JOIN ProductType pt ON c.cid = pt.category.cid " +
       "JOIN Stock s ON pt.pid = s.productType.pid " +
       "GROUP BY c.categoryName, pt.model")
    List<StockValueDTO> getStockValueByCategoryAndProductType();
}
