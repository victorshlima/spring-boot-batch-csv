package br.com.southsystem.spring.batch.common;

import org.springframework.batch.item.ItemProcessor;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableBatchProcessing
@Configuration
public class ContaJobConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContaJobConfig.class);
	
	@Autowired
	private JobBuilderFactory jobBuilders;
	
	@Autowired
	ContaStepConfig contaStepConfig;
	

	@Bean
	public Job accountJob() {
		return jobBuilders
				.get("accountJob")
				.incrementer(new RunIdIncrementer())		
				.flow(contaStepConfig.ContaJobStep())			
				.end()
				.build();
	}		
		

}
