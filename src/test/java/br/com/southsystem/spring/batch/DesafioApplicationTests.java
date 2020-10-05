package br.com.southsystem.spring.batch;


import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import br.com.southsystem.spring.batch.common.ContaJobConfig;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { ContaJobConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, 
DirtiesContextTestExecutionListener.class})
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class DesafioApplicationTests {
	

//	@Test
	void contextLoads() {
	}

	

	
	
}
