package com.example.CifFileProcessor.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class FileWriterService {

    private static final String FILE_PATH = "output.txt";

    @Autowired
    private ConfigService configService;

    public void writeObjectToFile(List<Object> objects) throws IOException, IllegalAccessException, NoSuchFieldException {
        // Load the column configurations from the YAML file
        ColumnConfig columnConfig = configService.loadColumnConfig();
        List<ColumnConfig.Column> columnConfigList = columnConfig.getColumns();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            int lineNumber = 1; // Start from 1 for the first line
            for (Object obj : objects) {
                // Format the row using the line number and the object data
                String formattedRow = DynamicFileFormatUtils.formatRow(obj, columnConfigList, lineNumber);
                writer.write(formattedRow);
                writer.newLine();
                lineNumber++; // Increment line number for the next row
            }
        }
    }
}
