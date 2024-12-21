package com.ShoeInvent.ShoeInvent.repository;
import com.ShoeInvent.ShoeInvent.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
    // Additional query methods if needed
}
