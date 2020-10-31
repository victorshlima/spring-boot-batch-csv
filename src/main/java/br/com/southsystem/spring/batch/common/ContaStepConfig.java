package br.com.southsystem.spring.batch.common;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.southsystem.spring.batch.domain.ContaIn;
import br.com.southsystem.spring.batch.domain.ContaOut;
import br.com.southsystem.spring.batch.job.StepListenerInfo;
import br.com.southsystem.spring.batch.step.ContaItemProcessor;
import br.com.southsystem.spring.batch.step.ContaItemReader;
import br.com.southsystem.spring.batch.step.ContaItemWriter;






@Component
public class ContaStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;


	@Autowired
	private StepListenerInfo stepListenerInfo;
		
	@Bean
	public ContaItemReader contaItemReader() {
		return new ContaItemReader();
	}

	@Bean
	public ContaItemProcessor contaItemProcessor() {
		return new ContaItemProcessor();
	}

	@Bean
	public ContaItemWriter contaItemWriter() {
		return new ContaItemWriter();
	}
	
	@Bean
	public Step ContaJobStep() {
		return stepBuilderFactory.get("ContaJobStep")
				.listener(stepListenerInfo)
				.<ContaIn, ContaOut>chunk(100)				
				.reader(contaItemReader())
				.processor(contaItemProcessor())
				.writer(contaItemWriter())
//				.taskExecutor(taskExecutor) // add task
//				.throttleLimit(10000)
				.build();
	}
	

	
}
