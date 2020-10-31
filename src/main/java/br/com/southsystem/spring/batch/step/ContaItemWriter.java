

package br.com.southsystem.spring.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import br.com.southsystem.spring.batch.common.ContaJobConfig;
import br.com.southsystem.spring.batch.domain.ContaOut;
import br.com.southsystem.spring.batch.util.ContextInformation;

@Component
public class ContaItemWriter extends FlatFileItemWriter<ContaOut> {
	
	@Autowired
	private ApplicationArguments applicationArguments;
	
	
	String[] filename = ContextInformation.getArgs();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContaJobConfig.class);
	public static final String DIR_OUTPUT = "target/processed/";
	

	public  ContaItemWriter() {
					setName("ContaItemWriter");
					setResource(new FileSystemResource(getDiretoryFileNameOutput()));
					setLineAggregator(new PassThroughLineAggregator<>());			
	}
	
	//@Bean
	public String getDiretoryFileNameOutput() {
		try {
			//String[] filename = applicationArguments.getSourceArgs();
			return DIR_OUTPUT + filename[1].substring(filename[1].lastIndexOf("/") + 1) + System.currentTimeMillis();
		} catch (Exception e) {
			LOGGER.error("Verifique o parametro nome arquivo {}", e.getLocalizedMessage());
		}
		return null;
	}

}



		

