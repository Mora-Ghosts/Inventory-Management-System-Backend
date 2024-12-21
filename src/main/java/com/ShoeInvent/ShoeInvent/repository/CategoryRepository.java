package com.ShoeInvent.ShoeInvent.repository;

import com.ShoeInvent.ShoeInvent.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Additional query methods if needed
}
