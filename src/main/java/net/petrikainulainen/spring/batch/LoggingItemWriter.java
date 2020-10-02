package net.petrikainulainen.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import net.petrikainulainen.spring.batch.common.LoggingStudentWriter;

import java.util.List;

/**
 * This {@code ItemWriter} writes the received {@link StudentDTO} objects
 * to a log file. The goal of this component is to help us to demonstrate
 * that our item reader reads the correct information from the CSV file.
 */
public class LoggingItemWriter implements ItemWriter<StudentDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);


    @Override
    public void write(List<? extends StudentDTO> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of a student: {}", i));
    }
}
