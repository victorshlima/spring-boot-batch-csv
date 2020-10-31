package br.com.southsystem.spring.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import br.com.southsystem.spring.batch.common.ContaJobConfig;
import br.com.southsystem.spring.batch.domain.ContaIn;
import br.com.southsystem.spring.batch.util.ContextInformation;

@Component
public class ContaItemReader extends FlatFileItemReader<ContaIn>  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContaJobConfig.class);
	
	
	@Autowired
	private ApplicationArguments applicationArguments;

	String[] filename = ContextInformation.getArgs();
	
	
	public ContaItemReader () {
		
		ContextInformation.getArgs();
		
		setResource(getResourceInputFile());
		setLinesToSkip(1);
		LineMapper<ContaIn> ContaLineMapper = createContaLineMapper();
		setLineMapper(ContaLineMapper);
	
	}
	
	
	
	@Bean
	public Resource getResourceInputFile() {
		
		try {
							
		//	String[] filenameArgs = applicationArguments.getSourceArgs();
			ClassLoader cl = this.getClass().getClassLoader();
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
			Resource resource = resolver.getResource(filename[1]);
			return resource;
		} catch (Exception e) {
			LOGGER.error("Verifique o parametro nome arquivo {}", e.getLocalizedMessage());
		}
		return null;
	}
	
	@Bean
	private LineMapper<ContaIn> createContaLineMapper() {
		DefaultLineMapper<ContaIn> ContaLineMapper = new DefaultLineMapper<>();
		LineTokenizer ContaLineTokenizer = createContaLineTokenizer();
		ContaLineMapper.setLineTokenizer(ContaLineTokenizer);
		FieldSetMapper<ContaIn> ContaInformationMapper = createContaInformationMapper();
		ContaLineMapper.setFieldSetMapper(ContaInformationMapper);
		return ContaLineMapper;
	}
	
	@Bean
	private FieldSetMapper<ContaIn> createContaInformationMapper() {
		BeanWrapperFieldSetMapper<ContaIn> ContaInformationMapper = new BeanWrapperFieldSetMapper<>();
		ContaInformationMapper.setTargetType(ContaIn.class);
		return ContaInformationMapper;
	}
	
	@Bean
	private LineTokenizer createContaLineTokenizer() {
		DelimitedLineTokenizer ContaLineTokenizer = new DelimitedLineTokenizer();
		ContaLineTokenizer.setDelimiter(";");
		ContaLineTokenizer.setNames(new String[] { "agencia", "conta", "saldo", "status" });
		return ContaLineTokenizer;
	}

}
