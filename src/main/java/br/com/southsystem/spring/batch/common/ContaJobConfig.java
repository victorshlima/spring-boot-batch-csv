package br.com.southsystem.spring.batch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.southsystem.spring.batch.job.JobListenerInfo;





@EnableBatchProcessing
@Configuration
public class ContaJobConfig {

	@Autowired
	private JobListenerInfo  jobListenerInfo;
	
	@Autowired
	private JobBuilderFactory jobBuilders;
	
	@Autowired
	private ContaStepConfig contaStepConfig;
	

	@Bean
	public Job contatJob() {
		return jobBuilders
				.get("contaJob")
				.incrementer(new RunIdIncrementer())
				.listener(JobListenerFactoryBean.getListener(jobListenerInfo))
				.flow(contaStepConfig.ContaJobStep())			
				.end()
				.build();
	}		
		
	

}
