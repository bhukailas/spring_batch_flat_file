package com.example.CifFileProcessor;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CardDataWriter implements ItemWriter<Card> {

    @Autowired
    private CardDataProcessor cardDataProcessor;

    private static final String FILE_PATH = "output/processed_card_data.txt";

    @Override
    public void write(List<? extends Card> items) {
        // No direct writing here; all writing happens after grouping
    }

    // Method to write all combined records once per card
    public void writeCombinedRecords() {
        List<Card> combinedRecords = cardDataProcessor.getCombinedRecords();
        combinedRecords.forEach(record -> {
            String line = String.join(",",
                    record.getCardNumber(),
                    record.getExpirationDate(),
                    record.getCreditLimit()
            );
            writeToFile(line);
        });
    }

    // Method to write a line to the output file
    private synchronized void writeToFile(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }
}
