package com.ShoeInvent.ShoeInvent.controller;
import com.ShoeInvent.ShoeInvent.dto.StockDTO;
import com.ShoeInvent.ShoeInvent.dto.TransactionTypeDTO;
import com.ShoeInvent.ShoeInvent.service.StockService;
import com.ShoeInvent.ShoeInvent.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable int id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @PostMapping
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.createStock(stockDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable int id, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStock(id, stockDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/barcode")
    public ResponseEntity<byte[]> getBarcodeByStockId(@PathVariable int id) {
        byte[] barcode = stockService.getBarcodeByStockId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(barcode);
    }

    @GetMapping("/{id}/barcode/download")
    public ResponseEntity<InputStreamResource> downloadBarcodePdfByStockId(@PathVariable int id,
                                                                           @RequestParam(required = false) String downloadDir,
                                                                           @RequestParam(defaultValue = "1") int stockCount) {
        try {
            // Call the service to generate the PDF with barcode copies
            Path pdfFilePath = stockService.downloadBarcodeByStockId(id, downloadDir, stockCount);

            // Create the resource for file streaming
            InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFilePath.toFile()));

            // Build and return the response entity with proper headers
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFilePath.getFileName().toString() + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (FileNotFoundException e) {
            // Return a 404 response if the file does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (RuntimeException e) {
            // Return a 500 response for runtime exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        } catch (Exception e) {
            // General exception handler for any other errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
