package com.example.CifFileProcessor;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardDataJobListener implements JobExecutionListener {

    @Autowired
    private CardDataWriter cardDataWriter;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // No action needed before the job
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        cardDataWriter.writeCombinedRecords();
    }
}
