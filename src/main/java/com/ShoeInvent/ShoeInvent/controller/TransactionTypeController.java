package com.ShoeInvent.ShoeInvent.controller;

import com.ShoeInvent.ShoeInvent.dto.TransactionTypeDTO;
import com.ShoeInvent.ShoeInvent.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-types")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping
    public ResponseEntity<List<TransactionTypeDTO>> getAllTransactionTypes() {
        return ResponseEntity.ok(transactionTypeService.getAllTransactionTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionTypeDTO> getTransactionTypeById(@PathVariable int id) {
        return ResponseEntity.ok(transactionTypeService.getTransactionTypeById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionTypeDTO> createTransactionType(@RequestBody TransactionTypeDTO transactionTypeDTO) {
        return ResponseEntity.ok(transactionTypeService.createTransactionType(transactionTypeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionTypeDTO> updateTransactionType(@PathVariable int id, @RequestBody TransactionTypeDTO transactionTypeDTO) {
        return ResponseEntity.ok(transactionTypeService.updateTransactionType(id, transactionTypeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionType(@PathVariable int id) {
        transactionTypeService.deleteTransactionType(id);
        return ResponseEntity.noContent().build();
    }
}
