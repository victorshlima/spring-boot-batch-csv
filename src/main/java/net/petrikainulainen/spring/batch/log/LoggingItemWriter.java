package net.petrikainulainen.spring.batch.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import net.petrikainulainen.spring.batch.domain.ContaIn;

import java.util.List;

public class LoggingItemWriter implements ItemWriter<ContaIn> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);

    @Override
    public void write(List<? extends ContaIn> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of a student: {}", i));
    }
}
