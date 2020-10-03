package net.petrikainulainen.spring.batch;

import org.springframework.batch.item.ItemProcessor;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import net.petrikainulainen.spring.batch.common.LoggingStudentProcessor;




/**
 * This configuration class configures the Spring Batch job that
 * is used to demonstrate that our item reader reads the correct
 * information from the CSV file.
 */
@Configuration
public class SpringBatchExampleJobConfig {

    @Bean
    public ItemReader<StudentDTO> itemReader() {
        FlatFileItemReader<StudentDTO> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new ClassPathResource("data/students.csv")); // alterar para o paramentro de entrada
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
