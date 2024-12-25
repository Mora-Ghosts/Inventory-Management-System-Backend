package com.ShoeInvent.ShoeInvent.service;
import com.ShoeInvent.ShoeInvent.dto.CategoryDTO;
import com.ShoeInvent.ShoeInvent.dto.ProductTypeDTO;
import com.ShoeInvent.ShoeInvent.dto.StockDTO;
import com.ShoeInvent.ShoeInvent.entity.Category;
import com.ShoeInvent.ShoeInvent.entity.ProductType;
import com.ShoeInvent.ShoeInvent.entity.Stock;
import com.ShoeInvent.ShoeInvent.exception.ResourceNotFoundException;
import com.ShoeInvent.ShoeInvent.repository.CategoryRepository;
import com.ShoeInvent.ShoeInvent.repository.ProductTypeRepository;
import com.ShoeInvent.ShoeInvent.repository.StockRepository;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import com.ShoeInvent.ShoeInvent.util.BarcodeUtil;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductTypeRepository productRepository;

    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StockDTO getStockById(int id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        return mapToDTO(stock);
    }

    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = mapToEntity(stockDTO);
        stock = stockRepository.save(stock);

        return mapToDTO(stock);
    }

    public StockDTO updateStock(int id, StockDTO stockDTO) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        stock.setSize(stockDTO.getSize());
        stock.setTimestamp(stockDTO.getTimestamp());
        stock.setCount(stockDTO.getCount());
        stock.setStockPrice(stockDTO.getStockPrice());
        stock.setProductType(mapToEntity(stockDTO).getProductType());
        stock = stockRepository.save(stock);
        return mapToDTO(stock);
    }

    public void deleteStock(int id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with ID: " + id);
        }
        stockRepository.deleteById(id);
    }

    public byte[] getBarcodeByStockId(int id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        byte[] barcodeImage;
        barcodeImage = BarcodeUtil.generateBarcode(stock.getStockID());
        return barcodeImage;
    }


    public Path downloadBarcodeByStockId(int id, String downloadDir, int stockCount) {
        byte[] barcodeImage = getBarcodeByStockId(id);
        String defaultDownloadDir = System.getProperty("user.home") + "/Downloads";
        String targetDir = (downloadDir == null || downloadDir.isBlank()) ? defaultDownloadDir : downloadDir;

        try {
            // Define the PDF file path
            Path pdfFilePath = Path.of(targetDir,  "stock_"+ id +"_.pdf");

            // Generate the PDF with barcode copies
            createPdfWithBarcodes(pdfFilePath, barcodeImage, stockCount);

            return pdfFilePath;
        } catch (Exception e) {
            throw new RuntimeException("Error generating barcode PDF: " + e.getMessage(), e);
        }
    }


    private void createPdfWithBarcodes(Path pdfFilePath, byte[] barcodeImage, int stockCount) throws Exception {
        try (PDDocument document = new PDDocument()) {
            int cols = 6; // Barcodes per row
            float margin = 5f;
            float barcodeWidth = 100f; // Width of each barcode
            float barcodeHeight = 50f; // Height of each barcode
            int rowsPerPage = 15; // Number of rows per page
            int barcodesPerPage = rowsPerPage * cols;

            for (int i = 0; i < stockCount; i++) {
                if (i % barcodesPerPage == 0) {
                    // Add a new page
                    PDPage page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                }

                // Calculate position for the barcode
                int pageIndex = i / barcodesPerPage;
                int positionInPage = i % barcodesPerPage;
                int row = positionInPage / cols;
                int col = positionInPage % cols;

                float x = col * (barcodeWidth);
                float y = PDRectangle.A4.getHeight() -(margin + (row + 1) * (barcodeHeight + margin));

                // Add barcode to the page
                PDPage page = document.getPage(pageIndex);
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, barcodeImage, "barcode");
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                    contentStream.drawImage(pdImage, x, y, barcodeWidth, barcodeHeight);
                }
            }

            // Save the PDF
            document.save(pdfFilePath.toFile());
        }
    }

    private StockDTO mapToDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setStockId(stock.getStockID());
        dto.setSize(stock.getSize());
        dto.setTimestamp(stock.getTimestamp());
        dto.setCount(stock.getCount());
        dto.setStockPrice(stock.getStockPrice());
        dto.setProductId(stock.getProductType().getPid());
        return dto;
    }

    private Stock mapToEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setStockID(dto.getStockId());
        stock.setSize(dto.getSize());
        stock.setTimestamp(dto.getTimestamp());
        stock.setCount(dto.getCount());
        stock.setStockPrice(dto.getStockPrice());
        stock.setProductType(productRepository.getReferenceById(dto.getProductId()));
        return stock;
    }
}
