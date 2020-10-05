package net.petrikainulainen.spring.batch.common;

import net.petrikainulainen.spring.batch.domain.StudentDTO;
import net.petrikainulainen.spring.batch.domain.StudentDtoReturn;
import net.petrikainulainen.spring.batch.receita.ReceitaService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

/**
 * This custom {@code ItemProcessor} simply writes the information of the
 * processed student to the log and returns the processed object.
 *
 * @author Petri Kainulainen
 */
public class LoggingStudentProcessor implements ItemProcessor<StudentDTO, StudentDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingStudentProcessor.class);

	@Autowired
	private ApplicationArguments applicationArguments;

	@Override
	public StudentDtoReturn process(StudentDTO item) throws Exception {
		LOGGER.info("Processing student information: {}", item);

		ReceitaService receitaService = new ReceitaService();
		Boolean retornoReceita = receitaService.atualizarConta(item.getAgencia(), item.getContaFormatada(),
				item.getSaldoFormatada(), item.getStatus());

		String[] filenameArgs = applicationArguments.getSourceArgs();

//		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
//
//		System.out.println(ClassLoader.getSystemClassLoader().getResource(".").getPath());
//		System.out.println(jarDir.getAbsolutePath());
//
//		File f = new File(System.getProperty("java.class.path"));
//		File dir = f.getAbsoluteFile().getParentFile();
//		String path = dir.toString();

//		Path filePath3;
//		filePath3 = Paths.get(filenameArgs[0]);


		
		
		
		
		
	//item.setAgencia(ClassLoader.getSystemClassLoader().getResource(".").getPath().toString());
	//	item.setConta(path.toString());
		item.setSaldo("");
		item.setStatus("");
		
		
		
		
		
		

		LOGGER.info("retorno receita {}", item + " - " + retornoReceita);
		return new StudentDtoReturn(item, retornoReceita);
	}
}
