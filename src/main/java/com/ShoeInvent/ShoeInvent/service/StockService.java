package com.ShoeInvent.ShoeInvent.service;
import com.ShoeInvent.ShoeInvent.dto.CategoryDTO;
import com.ShoeInvent.ShoeInvent.dto.ProductTypeDTO;
import com.ShoeInvent.ShoeInvent.dto.StockDTO;
import com.ShoeInvent.ShoeInvent.entity.Category;
import com.ShoeInvent.ShoeInvent.entity.ProductType;
import com.ShoeInvent.ShoeInvent.entity.Stock;
import com.ShoeInvent.ShoeInvent.exception.ResourceNotFoundException;
import com.ShoeInvent.ShoeInvent.repository.CategoryRepository;
import com.ShoeInvent.ShoeInvent.repository.ProductTypeRepository;
import com.ShoeInvent.ShoeInvent.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductTypeRepository productRepository;

    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StockDTO getStockById(int id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        return mapToDTO(stock);
    }

    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = mapToEntity(stockDTO);
        stock = stockRepository.save(stock);
        return mapToDTO(stock);
    }

    public StockDTO updateStock(int id, StockDTO stockDTO) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        stock.setSize(stockDTO.getSize());
        stock.setTimestamp(stockDTO.getTimestamp());
        stock.setCount(stockDTO.getCount());
        stock.setStockPrice(stockDTO.getStockPrice());
        stock.setProductType(mapToEntity(stockDTO).getProductType());
        stock = stockRepository.save(stock);
        return mapToDTO(stock);
    }

    public void deleteStock(int id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with ID: " + id);
        }
        stockRepository.deleteById(id);
    }

    private StockDTO mapToDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setStockId(stock.getStockID());
        dto.setSize(stock.getSize());
        dto.setTimestamp(stock.getTimestamp());
        dto.setCount(stock.getCount());
        dto.setStockPrice(stock.getStockPrice());
        dto.setProductId(stock.getProductType().getPid());
        return dto;
    }

    private Stock mapToEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setStockID(dto.getStockId());
        stock.setSize(dto.getSize());
        stock.setTimestamp(dto.getTimestamp());
        stock.setCount(dto.getCount());
        stock.setStockPrice(dto.getStockPrice());
        stock.setProductType(productRepository.getReferenceById(dto.getProductId()));
        return stock;
    }
}
