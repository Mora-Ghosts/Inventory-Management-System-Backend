package com.ShoeInvent.ShoeInvent.repository;
import com.ShoeInvent.ShoeInvent.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    // Additional query methods if needed
}
