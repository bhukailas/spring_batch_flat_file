package com.example.CifFileProcessor.generator;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FileGenerationController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FileGenerationController.class);
    @Autowired
    private FileWriterService fileWriterService;

    @GetMapping("/generate-file")
    public String generateFile() {
        try {
            // Example data
            Person person1 = new Person("John Doe", 25, LocalDate.of(1995, 5, 15));
            fileWriterService.writeObjectToFile(List.of(person1));
            return "File generated successfully!";
        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            log.error("Error generating file: ", e);
            throw  new RuntimeException(e);
        }
    }
}
