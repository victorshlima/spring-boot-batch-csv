package br.com.southsystem.spring.batch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import br.com.southsystem.spring.batch.domain.ContaIn;
import br.com.southsystem.spring.batch.domain.ContaOut;

import java.util.List;

public class ContaLoggingWriter implements ItemWriter<ContaOut> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContaLoggingWriter.class);

    @Override
    public void write(List<? extends ContaOut> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of a student: {}", i));
    }
}
