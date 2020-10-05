package net.petrikainulainen.spring.batch.common;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

import net.petrikainulainen.spring.batch.domain.ContaIn;
import net.petrikainulainen.spring.batch.domain.ContaOut;

@Configuration
public class ContaJobConfig {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ContaJobConfig.class);

	@Autowired
    private ApplicationArguments  applicationArguments;

    @Bean
    public ItemReader<ContaIn> itemReader() {
        FlatFileItemReader<ContaIn> csvFileReader = new FlatFileItemReader<>();
        
        
        String[] filenameArgs = applicationArguments.getSourceArgs();  
             
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();

		Path filePath3;
        
        
        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        
     //   Resource resources = resolver.getResource("file:///D:/development/DEV_TOOLS/desafiotecnico/spring-batch-examples/reading-data/csv-file/SpringReadWrite/target/students.csv" );
       // Resource resources = resolver.getResource(filenameArgs[0] );
        Resource resources = resolver.getResource(filenameArgs[1] );  
        
        
        try {
			resources.getFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			resources.getURI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        csvFileReader.setResource(
        		resources
        		); 
        csvFileReader.setLinesToSkip(1);

        LineMapper<ContaIn> studentLineMapper = createStudentLineMapper();
        csvFileReader.setLineMapper(studentLineMapper);

        return csvFileReader;
    }    
    

    @Bean
    public FlatFileItemWriter itemWriter() {
            return  new FlatFileItemWriterBuilder<ContaOut>()
                                       .name("itemWriter")
                                       .resource(new FileSystemResource("target/test-outputs/output.txt"))
                                       .lineAggregator(new PassThroughLineAggregator<>())
                                       .build();
    }
    
    @Bean
    public ItemProcessor<ContaIn, ContaIn> studentProcessor() {
        return new ContaLoggingProcessor();
    }

    private LineMapper<ContaIn> createStudentLineMapper() {
        DefaultLineMapper<ContaIn> studentLineMapper = new DefaultLineMapper<>();

        LineTokenizer studentLineTokenizer = createStudentLineTokenizer();
        studentLineMapper.setLineTokenizer(studentLineTokenizer);

        FieldSetMapper<ContaIn> studentInformationMapper = createStudentInformationMapper();
        studentLineMapper.setFieldSetMapper(studentInformationMapper);

        return studentLineMapper;
    }

    private LineTokenizer createStudentLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(";");
        studentLineTokenizer.setNames(new String[]{"agencia", "conta", "saldo", "status"});
        return studentLineTokenizer;
    }

    private FieldSetMapper<ContaIn> createStudentInformationMapper() {
        BeanWrapperFieldSetMapper<ContaIn> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
        studentInformationMapper.setTargetType(ContaIn.class);
        return studentInformationMapper;
    }
    
    
    private LineAggregator<ContaIn> createStudentLineAggregator() {
        DelimitedLineAggregator<ContaIn> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
 
        FieldExtractor<ContaIn> fieldExtractor = createStudentFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
 
        return lineAggregator;
    }
    private FieldExtractor<ContaIn> createStudentFieldExtractor() {
        BeanWrapperFieldExtractor<ContaIn> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"agencia", "conta", "saldo", "status"});
        return extractor;
    }
    

    @Bean
    public Step exampleJobStep(ItemReader<ContaIn> reader,
                               ItemWriter<ContaIn> writer,
                               ItemProcessor<ContaIn, ContaIn> studentProcessor,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleJobStep")
                .<ContaIn, ContaIn>chunk(1)
                .reader(reader)
                .processor(studentProcessor)
                .writer(writer)
                .build();
    }


    @Bean
    public Job exampleJob(Step exampleJobStep,
                          JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("exampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(exampleJobStep)
                .end()
                .build();
    }
}
