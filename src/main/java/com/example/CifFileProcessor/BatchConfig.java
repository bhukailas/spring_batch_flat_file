package com.example.CifFileProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CardDataProcessor cardDataProcessor;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, CardDataProcessor cardDataProcessor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.cardDataProcessor = cardDataProcessor;
    }

    @Bean
    public FlatFileItemReader<Card> flatFileItemReader() {
        FlatFileItemReader<Card> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("card_data.txt"));
        reader.setLinesToSkip(0); // Skip header if present
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new CustomLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Card.class);
            }});
        }});
        reader.setName("Tsys-cif-flatFileItemReader");
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Card> flatFileItemWriter() {
        return new FlatFileItemWriterBuilder<Card>()
                .name("Tsys-cif-flatFileItemWriter")
                .append(true)
                .resource(new FileSystemResource("output/processed_card_data.txt"))
                .delimited()
                .delimiter(",")
                .names("cardNumber", "expirationDate", "creditLimit")
                .build();
    }

    @Bean
    public Step startStep() {
        return stepBuilderFactory.get("Tsys-cif-flatfileTransformerJob-startStep")
                .<Card, Card>chunk(10)
                .reader(flatFileItemReader())
                .processor(cardDataProcessor)
                .writer(flatFileItemWriter())
                .build();
    }

    @Bean
    public Job flatfileTransformerJob(CardDataJobListener listener) {
        return jobBuilderFactory.get("Tsys-cif-flatfileTransformerJob")
                .incrementer(new RunIdIncrementer())
                .start(startStep())
                .listener(listener)
                .build();
    }
}
