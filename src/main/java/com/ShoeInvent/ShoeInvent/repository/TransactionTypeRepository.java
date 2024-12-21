package com.ShoeInvent.ShoeInvent.repository;
import com.ShoeInvent.ShoeInvent.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
    // Additional query methods if needed
}
