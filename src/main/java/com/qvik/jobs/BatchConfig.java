package com.qvik.jobs;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qvik.repository.LogRepository;
import com.qvik.tasklet.LogDeletingTasklet;
 
 
@Configuration
@EnableBatchProcessing
public class BatchConfig {
     
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
	private LogRepository logRepository;
    
    @Value("${spring.log.max.age}")
	private int maxAge;
     
    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet(new LogDeletingTasklet(logRepository,maxAge))
                .build();
    }
     
    @Bean
    public Job demoJob(){
        return jobs.get("logJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .build();
    }
}