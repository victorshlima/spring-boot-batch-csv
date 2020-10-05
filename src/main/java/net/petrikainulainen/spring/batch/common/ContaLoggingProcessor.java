package net.petrikainulainen.spring.batch.common;

import net.petrikainulainen.spring.batch.domain.ContaIn;
import net.petrikainulainen.spring.batch.domain.ContaOut;
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

public class ContaLoggingProcessor implements ItemProcessor<ContaIn, ContaIn> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContaLoggingProcessor.class);

	@Autowired
	private ApplicationArguments applicationArguments;

	@Override
	public ContaOut process(ContaIn item) throws Exception {
		LOGGER.info("Processing student information: {}", item);

		ReceitaService receitaService = new ReceitaService();
		Boolean retornoReceita = receitaService.atualizarConta(item.getAgencia(), item.getContaFormatada(),
				item.getSaldoFormatada(), item.getStatus());

		LOGGER.info("retorno receita {}", item + " - " + retornoReceita);
		return new ContaOut(item, retornoReceita);
	}
}
