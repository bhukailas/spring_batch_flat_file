package com.example.CifFileProcessor;

import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;

public class CustomLineTokenizer implements LineTokenizer {

    @Override
    public FieldSet tokenize(String line) {
        String[] tokens = line.split(",");

        String cardNumber = tokens[0].trim();
        String recordType = tokens[1].trim();

        // Handle type1 and type2 separately
        if ("type1".equals(recordType)) {
            String expirationDate = tokens[2].trim();
            return new DefaultFieldSet(new String[]{cardNumber, recordType, expirationDate},
                                       new String[]{"cardNumber", "recordType", "expirationDate"});
        } else if ("type2".equals(recordType)) {
            String creditLimit = tokens[2].trim();
            return new DefaultFieldSet(new String[]{cardNumber, recordType, creditLimit},
                                       new String[]{"cardNumber", "recordType", "creditLimit"});
        } else {
            throw new IllegalArgumentException("Unknown card type: " + recordType);
        }
    }
}
