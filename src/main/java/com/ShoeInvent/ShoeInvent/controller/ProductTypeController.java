package com.ShoeInvent.ShoeInvent.controller;
import com.ShoeInvent.ShoeInvent.dto.ProductTypeDTO;
import com.ShoeInvent.ShoeInvent.dto.TransactionTypeDTO;
import com.ShoeInvent.ShoeInvent.service.ProductTypeService;
import com.ShoeInvent.ShoeInvent.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
@CrossOrigin
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductTypeDTO>> getAllProductTypes() {
        return ResponseEntity.ok(productTypeService.getAllProductTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeDTO> getProductTypeById(@PathVariable int id) {
        return ResponseEntity.ok(productTypeService.getProductTypeById(id));
    }

    @PostMapping
    public ResponseEntity<ProductTypeDTO> createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return ResponseEntity.ok(productTypeService.createProductType(productTypeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTypeDTO> updateProductType(@PathVariable int id, @RequestBody ProductTypeDTO productTypeDTO) {
        return ResponseEntity.ok(productTypeService.updateProductType(id, productTypeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable int id) {
        productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }
}

