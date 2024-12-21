package com.ShoeInvent.ShoeInvent.service;

import com.ShoeInvent.ShoeInvent.dto.CategoryDTO;
import com.ShoeInvent.ShoeInvent.entity.Category;
import com.ShoeInvent.ShoeInvent.exception.ResourceNotFoundException;
import com.ShoeInvent.ShoeInvent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// CategoryService
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        return mapToDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return mapToDTO(category);
    }

    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        category.setCategoryName(categoryDTO.getCategoryName());
        category = categoryRepository.save(category);
        return mapToDTO(category);
    }

    public void deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCid(category.getCid());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }

    private Category mapToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setCid(dto.getCid());
        category.setCategoryName(dto.getCategoryName());
        return category;
    }
}