package com.ShoeInvent.ShoeInvent.service;
import com.ShoeInvent.ShoeInvent.dto.CategoryDTO;
import com.ShoeInvent.ShoeInvent.dto.ProductTypeDTO;
import com.ShoeInvent.ShoeInvent.entity.Category;
import com.ShoeInvent.ShoeInvent.entity.ProductType;
import com.ShoeInvent.ShoeInvent.exception.ResourceNotFoundException;
import com.ShoeInvent.ShoeInvent.repository.CategoryRepository;
import com.ShoeInvent.ShoeInvent.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

// ProductTypeService
@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<ProductTypeDTO> getAllProductTypes() {
        return productTypeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductTypeDTO getProductTypeById(int id) {
        ProductType productType = productTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductType not found with ID: " + id));
        return mapToDTO(productType);
    }

    public ProductTypeDTO createProductType(ProductTypeDTO productTypeDTO) {
        ProductType productType = mapToEntity(productTypeDTO);
        productType = productTypeRepository.save(productType);
        return mapToDTO(productType);
    }

    public ProductTypeDTO updateProductType(int id, ProductTypeDTO productTypeDTO) {
        ProductType productType = productTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductType not found with ID: " + id));
        productType.setBrand(productTypeDTO.getBrand());
        productType.setModel(productTypeDTO.getModel());
        productType.setColor(productTypeDTO.getColor());
        productType.setCategory(categoryRepository.getReferenceById(productTypeDTO.getCategoryId()));
        productType = productTypeRepository.save(productType);
        return mapToDTO(productType);
    }

    public void deleteProductType(int id) {
        if (!productTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("ProductType not found with ID: " + id);
        }
        productTypeRepository.deleteById(id);
    }

    private ProductTypeDTO mapToDTO(ProductType productType) {
        ProductTypeDTO dto = new ProductTypeDTO();
        dto.setPid(productType.getPid());
        dto.setBrand(productType.getBrand());
        dto.setModel(productType.getModel());
        dto.setColor(productType.getColor());
        dto.setCategoryId(productType.getCategory().getCid());
        return dto;
    }

    private ProductType mapToEntity(ProductTypeDTO dto) {
        ProductType productType = new ProductType();
        productType.setPid(dto.getPid());
        productType.setBrand(dto.getBrand());
        productType.setModel(dto.getModel());
        productType.setColor(dto.getColor());
        productType.setCategory(categoryRepository.getReferenceById(dto.getCategoryId()));
        return productType;
    }
}