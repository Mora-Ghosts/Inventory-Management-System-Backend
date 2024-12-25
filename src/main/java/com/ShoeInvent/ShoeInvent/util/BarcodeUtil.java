package com.ShoeInvent.ShoeInvent.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BarcodeUtil {

    // Generate barcode image as a byte array
    public static byte[] generateBarcode(int stockID) {
        try {
            String barcodeData = "SKU" + stockID; // You can customize the barcode format here
            MultiFormatWriter barcodeWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(barcodeData, BarcodeFormat.CODE_128, 200, 100);

            BufferedImage barcodeImage = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 100; y++) {
                    barcodeImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            // Convert the BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(barcodeImage, "png", baos);
            return baos.toByteArray();

        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating barcode: " + e.getMessage());
        }
    }
}

