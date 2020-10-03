package net.petrikainulainen.spring.batch.common;

import net.petrikainulainen.spring.batch.StudentDTO;
import net.petrikainulainen.spring.batch.StudentDtoReturn;
import net.petrikainulainen.spring.batch.receita.ReceitaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * This custom {@code ItemProcessor} simply writes the information of the
 * processed student to the log and returns the processed object.
 *
 * @author Petri Kainulainen
 */
public class LoggingStudentProcessor implements ItemProcessor<StudentDTO, StudentDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingStudentProcessor.class);

    @Override
    public StudentDtoReturn process(StudentDTO item) throws Exception {
        LOGGER.info("Processing student information: {}", item);
        
        ReceitaService receitaService = new ReceitaService();
        Boolean retornoReceita =  receitaService.atualizarConta(item.getAgencia(),item.getContaFormatada(), item.getSaldoFormatada(), item.getStatus());   
             
        LOGGER.info("retorno receita {}", item + " - " + retornoReceita);
        return new StudentDtoReturn(item, retornoReceita);
    }
}
