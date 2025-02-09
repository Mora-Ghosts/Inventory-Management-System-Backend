package com.ShoeInvent.ShoeInvent.service;
import com.ShoeInvent.ShoeInvent.dto.CategoryDTO;
import com.ShoeInvent.ShoeInvent.dto.ProductTypeDTO;
import com.ShoeInvent.ShoeInvent.dto.TransactionsDTO;
import com.ShoeInvent.ShoeInvent.entity.Category;
import com.ShoeInvent.ShoeInvent.entity.ProductType;
import com.ShoeInvent.ShoeInvent.entity.Transactions;
import com.ShoeInvent.ShoeInvent.exception.ResourceNotFoundException;
import com.ShoeInvent.ShoeInvent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionsDTO> getAllTransactions() {
        return transactionsRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TransactionsDTO getTransactionById(int id) {
        Transactions transaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
        return mapToDTO(transaction);
    }

    public TransactionsDTO createTransaction(TransactionsDTO transactionsDTO) {
        Transactions transaction = mapToEntity(transactionsDTO);
        transaction = transactionsRepository.save(transaction);
        return mapToDTO(transaction);
    }

    public TransactionsDTO updateTransaction(int id, TransactionsDTO transactionsDTO) {
        Transactions transaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
        transaction.setTid(transactionsDTO.getTid());
        transaction.setPrice(transactionsDTO.getPrice());
        transaction.setQuantity(transactionsDTO.getQuantity());
        transaction.setTimestamp(transactionsDTO.getTimestamp());
        transaction.setPrice(transactionsDTO.getPrice());
        transaction = transactionsRepository.save(transaction);
        return mapToDTO(transaction);
    }

    public void deleteTransaction(int id) {
        if (!transactionsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found with ID: " + id);
        }
        transactionsRepository.deleteById(id);
    }

    private TransactionsDTO mapToDTO(Transactions transaction) {
        TransactionsDTO dto = new TransactionsDTO();
        dto.setTransactionTypeId(transaction.getTransactionType().getTtid());
        dto.setTid(transaction.getTid());
        dto.setStockId(transaction.getStock().getStockID());
        dto.setQuantity(transaction.getQuantity());
        dto.setTimestamp(transaction.getTimestamp());
        dto.setPrice(transaction.getPrice());
        return dto;
    }

    private Transactions mapToEntity(TransactionsDTO dto) {
        Transactions transaction = new Transactions();
        transaction.setTransactionType(transactionTypeRepository.getReferenceById(dto.getTransactionTypeId()));
        transaction.setStock(stockRepository.getReferenceById(dto.getStockId()));
        transaction.setQuantity(dto.getQuantity());
        transaction.setTimestamp(dto.getTimestamp());
        transaction.setPrice(dto.getPrice());
        return transaction;
    }
}
