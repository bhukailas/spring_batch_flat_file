package com.example.CifFileProcessor.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigService {

    public ColumnConfig loadColumnConfig() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ClassPathResource resource = new ClassPathResource("column-config.yml");
        return objectMapper.readValue(resource.getInputStream(), ColumnConfig.class);
    }
}
