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

import net.petrikainulainen.spring.batch.domain.StudentDTO;
import net.petrikainulainen.spring.batch.domain.StudentDtoReturn;





/**
 * This configuration class configures the Spring Batch job that
 * is used to demonstrate that our item reader reads the correct
 * information from the CSV file.
 */
@Configuration
public class SpringBatchExampleJobConfig {

	
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchExampleJobConfig.class);

	@Autowired
    private ApplicationArguments  applicationArguments;
	
	
//	@Autowired
//	ReadFile readFile;
	

	
//    @Bean
//    public ItemReader<StudentDTO> itemReader() {
//        FlatFileItemReader<StudentDTO> csvFileReader = new FlatFileItemReader<>();
//        
//        String[] filenameArgs = applicationArguments.getSourceArgs();
//  
//        
//		try {
//			Path  filePath = Paths.get(filenameArgs[1]);
//			filePath = 	filePath.toRealPath(LinkOption.NOFOLLOW_LINKS);
//		    System.out.println(filePath.toString());
//		    
//		    
//		    LOGGER.debug(filePath.toString());
//		    LOGGER.info(filePath.toString());
//		    LOGGER.trace(filePath.toString());
//		    LOGGER.error(filePath.toString());
//		    LOGGER.warn(filePath.toString());
//		    
//		    LOGGER.debug(filePath.getRoot().toString());
//		    LOGGER.info(filePath.getRoot().toString());
//		    LOGGER.trace(filePath.getRoot().toString());
//		    LOGGER.error(filePath.getRoot().toString());
//		    LOGGER.warn(filePath.getRoot().toString());
//        
//   //     readFile.leArquivoCsv("students.csv");
//
//        File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
//       
//        ///D:/development/DEV_TOOLS/desafiotecnico/spring-batch-examples/reading-data/csv-file/SpringReadWrite/target/classes/
//        System.out.println(ClassLoader.getSystemClassLoader().getResource(".").getPath());
//        
//        System.out.println(jarDir.getAbsolutePath());
//        
//        
//        File f = new File(System.getProperty("java.class.path"));
//        File dir = f.getAbsoluteFile().getParentFile();
//        String path = dir.toString();
//        
//      	 Path filePath3;
// 		filePath3 = Paths.get(filenameArgs[1]);
// 	
//        FileSystemResource fileSystemResource = new FileSystemResource(filenameArgs[1]);
//
//        
//        csvFileReader.setResource(fileSystemResource);
//        csvFileReader.setLinesToSkip(1);
//
//        LineMapper<StudentDTO> studentLineMapper = createStudentLineMapper();
//        csvFileReader.setLineMapper(studentLineMapper);
//
////		try (Reader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
////			LOGGER.info("Realizando leitura do arquivo...");
////
////			reader.read();
////		} catch (IOException e) {
////			LOGGER.error("Falha ao realizar leitura do arquivo");
////			e.printStackTrace();
////		}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        return csvFileReader;
//    }    
    

    @Bean
    public ItemReader<StudentDTO> itemReader() {
        FlatFileItemReader<StudentDTO> csvFileReader = new FlatFileItemReader<>();
        
        
        String[] filenameArgs = applicationArguments.getSourceArgs();
        
        
    //	Path  filePath = Paths.get(filenameArgs[1]);
//		try {
//			filePath = 	filePath.toRealPath(LinkOption.NOFOLLOW_LINKS);
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//	    System.out.println(filePath.toString());
//	    
//	    
//	    
//	    LOGGER.debug(filePath.getFileName().toString());
//	    LOGGER.debug(filePath.getFileSystem().toString());
//	    LOGGER.debug(filePath.getParent().toString());
//	    
//	    
//	    
//	  
//	    LOGGER.debug(filePath.toString());
//	    LOGGER.info(filePath.toString());
//	    LOGGER.trace(filePath.toString());
//	    LOGGER.error(filePath.toString());
//	    LOGGER.warn(filePath.toString());
//	    
//	    LOGGER.debug(filePath.getRoot().toString());
//	    LOGGER.info(filePath.getRoot().toString());
//	    LOGGER.trace(filePath.getRoot().toString());
//	    LOGGER.error(filePath.getRoot().toString());
//	    LOGGER.warn(filePath.getRoot().toString());
        
        
        
        
        
        
        
        
        
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
 
    //	File org.springframework.core.io.Resource.getFile()
        
        csvFileReader.setResource(
        		resources
        		); // alterar para o paramentro de entrada
        csvFileReader.setLinesToSkip(1);

        LineMapper<StudentDTO> studentLineMapper = createStudentLineMapper();
        csvFileReader.setLineMapper(studentLineMapper);

        return csvFileReader;
    }    
    
    
    
    
    
    
    
    

    @Bean
    public FlatFileItemWriter itemWriter() {
            return  new FlatFileItemWriterBuilder<StudentDtoReturn>()
                                       .name("itemWriter")
                                       .resource(new FileSystemResource("target/test-outputs/output.txt"))
                                       .lineAggregator(new PassThroughLineAggregator<>())
                                       .build();
    }
    
    @Bean
    public ItemProcessor<StudentDTO, StudentDTO> studentProcessor() {
        return new LoggingStudentProcessor();
    }

    private LineMapper<StudentDTO> createStudentLineMapper() {
        DefaultLineMapper<StudentDTO> studentLineMapper = new DefaultLineMapper<>();

        LineTokenizer studentLineTokenizer = createStudentLineTokenizer();
        studentLineMapper.setLineTokenizer(studentLineTokenizer);

        FieldSetMapper<StudentDTO> studentInformationMapper = createStudentInformationMapper();
        studentLineMapper.setFieldSetMapper(studentInformationMapper);

        return studentLineMapper;
    }

    private LineTokenizer createStudentLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(";");
        studentLineTokenizer.setNames(new String[]{"agencia", "conta", "saldo", "status"});
        return studentLineTokenizer;
    }

    private FieldSetMapper<StudentDTO> createStudentInformationMapper() {
        BeanWrapperFieldSetMapper<StudentDTO> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
        studentInformationMapper.setTargetType(StudentDTO.class);
        return studentInformationMapper;
    }
    
    
    private LineAggregator<StudentDTO> createStudentLineAggregator() {
        DelimitedLineAggregator<StudentDTO> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
 
        FieldExtractor<StudentDTO> fieldExtractor = createStudentFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
 
        return lineAggregator;
    }
    private FieldExtractor<StudentDTO> createStudentFieldExtractor() {
        BeanWrapperFieldExtractor<StudentDTO> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"agencia", "conta", "saldo", "status"});
        return extractor;
    }
    

//    @Bean
//    public ItemWriter<StudentDTO> itemWriter() {
//        return new LoggingItemWriter();
//    }

    /**
     * Creates a bean that represents the only step of our batch job.
     * @param reader
     * @param writer
     * @param stepBuilderFactory
     * @return
     */
    @Bean
    public Step exampleJobStep(ItemReader<StudentDTO> reader,
                               ItemWriter<StudentDTO> writer,
                               ItemProcessor<StudentDTO, StudentDTO> studentProcessor,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleJobStep")
                .<StudentDTO, StudentDTO>chunk(1)
                .reader(reader)
                .processor(studentProcessor)
                .writer(writer)
                .build();
    }

    /**
     * Creates a bean that represents our example batch job.
     * @param exampleJobStep
     * @param jobBuilderFactory
     * @return
     */
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
