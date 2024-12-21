package com.ShoeInvent.ShoeInvent.controller;
import com.ShoeInvent.ShoeInvent.dto.TransactionTypeDTO;
import com.ShoeInvent.ShoeInvent.dto.TransactionsDTO;
import com.ShoeInvent.ShoeInvent.service.TransactionTypeService;
import com.ShoeInvent.ShoeInvent.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping
    public ResponseEntity<List<TransactionsDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionsService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsDTO> getTransactionById(@PathVariable int id) {
        return ResponseEntity.ok(transactionsService.getTransactionById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionsDTO> createTransaction(@RequestBody TransactionsDTO transactionsDTO) {
        return ResponseEntity.ok(transactionsService.createTransaction(transactionsDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionsDTO> updateTransaction(@PathVariable int id, @RequestBody TransactionsDTO transactionsDTO) {
        return ResponseEntity.ok(transactionsService.updateTransaction(id, transactionsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
