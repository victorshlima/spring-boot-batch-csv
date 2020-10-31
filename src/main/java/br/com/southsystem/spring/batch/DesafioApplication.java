package br.com.southsystem.spring.batch;


import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.southsystem.spring.batch.job.ExecutorJob;
import br.com.southsystem.spring.batch.util.ContextInformation;



@SpringBootApplication
public class DesafioApplication  implements CommandLineRunner { 
	
	public static void main(String[] args) {
		
	  	
	    Stream.of(args).forEach(System.out::print);
	    ContextInformation.setArgs(args);
		
		SpringApplication.run(DesafioApplication.class, args);
		
	}
		
	@Autowired
	private ExecutorJob executorJob; 
	 
	@Override
    public void run(String... args) throws Exception {
		executorJob.execute();
    }
    
    
}
