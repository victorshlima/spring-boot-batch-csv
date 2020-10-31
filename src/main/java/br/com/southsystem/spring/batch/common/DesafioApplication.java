package br.com.southsystem.spring.batch.common;

import java.util.stream.Stream;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.southsystem.spring.batch.util.ContextInformation;

@SpringBootApplication
@EnableBatchProcessing
public class DesafioApplication { 
	
	
	
	@Autowired
	private ApplicationArguments applicationArguments;
    
	@Autowired
	private static ContaJobLauncher executorJob; 
	
	
    public static void main(String[] args) throws JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, Exception {
      
    	
    Stream.of(args).forEach(System.out::print);
    ContextInformation.setArgs(args);
    // batch processing conflito com ARGS - ApplicationArguments
    // o erro ocorre pq os ApplicationArguments não estão nulos, verificar outr meio de pegar ou setar esses arguemntos
    // setar no contexto esse arguemntos ou em um objeto statico com acesso global
    
    
    
    	
   	SpringApplication.run(DesafioApplication.class, args);
        
        
     //   executorJob.execute();
    }    
    

	 
	
	
//	
//	@Override
//    public void run(String... args) throws Exception {
//		
//			executorJob.execute();
//    }
//    
    
}
