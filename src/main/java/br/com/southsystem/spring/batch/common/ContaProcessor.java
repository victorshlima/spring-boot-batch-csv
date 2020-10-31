package br.com.southsystem.spring.batch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

>>>>>>> 0014
import br.com.southsystem.spring.batch.domain.ContaIn;
import br.com.southsystem.spring.batch.domain.ContaOut;
import br.com.southsystem.spring.batch.receita.ReceitaService;

<<<<<<< HEAD
=======

@Component
>>>>>>> 0014
public class ContaProcessor implements ItemProcessor<ContaIn, ContaOut> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContaProcessor.class);

<<<<<<< HEAD
=======

	@Bean
	public ReceitaService getReceitaService() {
		return new ReceitaService();
	}
	
	
>>>>>>> 0014
	@Override
	public ContaOut process(ContaIn item) throws Exception {
		LOGGER.info("Atualizando conta: {}", item);		
		try {
<<<<<<< HEAD
		ReceitaService receitaService = new ReceitaService();
=======
		ReceitaService receitaService = getReceitaService();
>>>>>>> 0014
		Boolean retornoReceita = receitaService.atualizarConta(item.getAgencia(), item.getContaFormatada(),
				item.getSaldoFormatada(), item.getStatus());
		return new ContaOut(item, retornoReceita);		
		} catch (Exception e) {
		LOGGER.info("Erro ao atualizar conta: {}", item + "- \n -"+ e.getMessage() );
		return new ContaOut(item, false);
		}	
	}
}
