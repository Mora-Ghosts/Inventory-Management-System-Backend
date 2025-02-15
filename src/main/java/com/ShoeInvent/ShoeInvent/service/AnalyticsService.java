package com.ShoeInvent.ShoeInvent.service;
 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoeInvent.ShoeInvent.dto.StockValueDTO;
import com.ShoeInvent.ShoeInvent.repository.AnalyticsRepository;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public List<StockValueDTO>getStockValueByCategoryAndProductType() {
        return analyticsRepository.getStockValueByCategoryAndProductType();
    }
}
