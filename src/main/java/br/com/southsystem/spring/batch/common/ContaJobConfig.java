package br.com.southsystem.spring.batch.common;

import org.springframework.batch.item.ItemProcessor;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import br.com.southsystem.spring.batch.domain.ContaIn;
import br.com.southsystem.spring.batch.domain.ContaOut;

@Configuration
public class ContaJobConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContaJobConfig.class);
	public static final String DIR_OUTPUT = "target/processed/";

	@Autowired
	private ApplicationArguments applicationArguments;

	@Bean
	public ItemReader<ContaIn> itemReader() {
		FlatFileItemReader<ContaIn> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(getResourceInputFile());
		csvFileReader.setLinesToSkip(1);
		LineMapper<ContaIn> ContaLineMapper = createContaLineMapper();
		csvFileReader.setLineMapper(ContaLineMapper);
		return csvFileReader;
	}

	public Resource getResourceInputFile() {
		try {
			String[] filenameArgs = applicationArguments.getSourceArgs();
			ClassLoader cl = this.getClass().getClassLoader();
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
			Resource resource = resolver.getResource(filenameArgs[1]);
			return resource;
		} catch (Exception e) {
			LOGGER.error("Verifique o parametro nome arquivo {}", e.getLocalizedMessage());
		}
		return null;
	}

	public String getDiretoryFileNameOutput() {
		try {
			String[] filename = applicationArguments.getSourceArgs();
			return DIR_OUTPUT + filename[1].substring(filename[1].lastIndexOf("/") + 1) + System.currentTimeMillis();
		} catch (Exception e) {
			LOGGER.error("Verifique o parametro nome arquivo {}", e.getLocalizedMessage());
		}
		return null;
	}

	@Bean
	public FlatFileItemWriter<ContaOut> itemWriter() {
		return new FlatFileItemWriterBuilder<ContaOut>().name("itemWriter")
				.resource(new FileSystemResource(getDiretoryFileNameOutput()))
				.lineAggregator(new PassThroughLineAggregator<>()).build();
	}

	@Bean
	public ItemProcessor<ContaIn, ContaOut> ContaProcessor() {
		return new ContaProcessor();
	}

	private LineMapper<ContaIn> createContaLineMapper() {
		DefaultLineMapper<ContaIn> ContaLineMapper = new DefaultLineMapper<>();
		LineTokenizer ContaLineTokenizer = createContaLineTokenizer();
		ContaLineMapper.setLineTokenizer(ContaLineTokenizer);
		FieldSetMapper<ContaIn> ContaInformationMapper = createContaInformationMapper();
		ContaLineMapper.setFieldSetMapper(ContaInformationMapper);
		return ContaLineMapper;
	}

	private LineTokenizer createContaLineTokenizer() {
		DelimitedLineTokenizer ContaLineTokenizer = new DelimitedLineTokenizer();
		ContaLineTokenizer.setDelimiter(";");
		ContaLineTokenizer.setNames(new String[] { "agencia", "conta", "saldo", "status" });
		return ContaLineTokenizer;
	}

	private FieldSetMapper<ContaIn> createContaInformationMapper() {
		BeanWrapperFieldSetMapper<ContaIn> ContaInformationMapper = new BeanWrapperFieldSetMapper<>();
		ContaInformationMapper.setTargetType(ContaIn.class);
		return ContaInformationMapper;
	}

	@Bean
	public Step ContaJobStep(ItemReader<ContaIn> reader, ItemWriter<ContaOut> writer,
			ItemProcessor<ContaIn, ContaOut> ContaProcessor, StepBuilderFactory stepBuilderFactory, TaskExecutor taskExecutor) {
		return stepBuilderFactory.get("ContaJobStep").<ContaIn, ContaOut>chunk(1).reader(reader)
				.processor(ContaProcessor)
				.writer(writer)
				.taskExecutor(taskExecutor)
				.throttleLimit(10000)
				.build();
	}
	
		
	@Bean
	public Job ContaJob(Step ContaJobStep, JobBuilderFactory jobBuilderFactory) {  		
		return jobBuilderFactory.get("ContaJob")
				.incrementer(new RunIdIncrementer()).flow(ContaJobStep).end()
				.build();
	}
}
