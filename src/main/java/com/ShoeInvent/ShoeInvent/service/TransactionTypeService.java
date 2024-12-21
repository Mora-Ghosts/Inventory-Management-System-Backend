package com.ShoeInvent.ShoeInvent.service;

import com.ShoeInvent.ShoeInvent.dto.CategoryDTO;
import com.ShoeInvent.ShoeInvent.dto.ProductTypeDTO;
import com.ShoeInvent.ShoeInvent.dto.TransactionTypeDTO;
import com.ShoeInvent.ShoeInvent.entity.Category;
import com.ShoeInvent.ShoeInvent.entity.ProductType;
import com.ShoeInvent.ShoeInvent.entity.TransactionType;
import com.ShoeInvent.ShoeInvent.exception.ResourceNotFoundException;
import com.ShoeInvent.ShoeInvent.repository.CategoryRepository;
import com.ShoeInvent.ShoeInvent.repository.ProductTypeRepository;
import com.ShoeInvent.ShoeInvent.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

// TransactionTypeService
@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionTypeDTO> getAllTransactionTypes() {
        return transactionTypeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TransactionTypeDTO getTransactionTypeById(int id) {
        TransactionType transactionType = transactionTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TransactionType not found with ID: " + id));
        return mapToDTO(transactionType);
    }

    public TransactionTypeDTO createTransactionType(TransactionTypeDTO transactionTypeDTO) {
        TransactionType transactionType = mapToEntity(transactionTypeDTO);
        transactionType = transactionTypeRepository.save(transactionType);
        return mapToDTO(transactionType);
    }

    public TransactionTypeDTO updateTransactionType(int id, TransactionTypeDTO transactionTypeDTO) {
        TransactionType transactionType = transactionTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TransactionType not found with ID: " + id));
        transactionType.setTransactionType(transactionTypeDTO.getTransactionType());
        transactionType = transactionTypeRepository.save(transactionType);
        return mapToDTO(transactionType);
    }

    public void deleteTransactionType(int id) {
        if (!transactionTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("TransactionType not found with ID: " + id);
        }
        transactionTypeRepository.deleteById(id);
    }

    private TransactionTypeDTO mapToDTO(TransactionType transactionType) {
        TransactionTypeDTO dto = new TransactionTypeDTO();
        dto.setTtid(transactionType.getTtid());
        dto.setTransactionType(transactionType.getTransactionType());
        return dto;
    }

    private TransactionType mapToEntity(TransactionTypeDTO dto) {
        TransactionType transactionType = new TransactionType();
        transactionType.setTtid(dto.getTtid());
        transactionType.setTransactionType(dto.getTransactionType());
        return transactionType;
    }
}