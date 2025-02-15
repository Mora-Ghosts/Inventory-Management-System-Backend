package com.ShoeInvent.ShoeInvent.controller; 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoeInvent.ShoeInvent.dto.StockValueDTO;
import com.ShoeInvent.ShoeInvent.service.AnalyticsService;
 

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/stock-value")
    public List<StockValueDTO> getStockValueByCategoryAndProductType() {
        return analyticsService.getStockValueByCategoryAndProductType();
    }
}
