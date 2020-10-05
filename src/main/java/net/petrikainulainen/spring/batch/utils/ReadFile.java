//package net.petrikainulainen.spring.batch.utils;
//
//import java.io.IOException;
//import java.io.Reader;
//import java.io.Writer;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import net.petrikainulainen.spring.batch.StudentDTO;
//
//
//@Service
//public class ReadFile {
//	
//	
//private static final Logger logger = LoggerFactory.getLogger(ReadFile.class);
//	
//	public static final String NOME_ARQUIVO_FINAL = "resultado.csv";
//	public static final char CSV_SEPARATOR = ';';
//	private Path filePath;
//
//
//	public List<StudentDTO> leArquivoCsv(String fileName) {
//		List<StudentDTO> infoContas = new ArrayList<>();
//		filePath = Paths.get(fileName);
//        
//		try (Reader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
//			logger.info("Realizando leitura do arquivo...");
//
//			logger.info("Realizando leitura do arquivo...");
//
//		} catch (IOException e) {
//			logger.error("Falha ao realizar leitura do arquivo");
//			e.printStackTrace();
//		}
//		
//		return infoContas;
//	}
//
//	/**
//	 * Gera arquivo csv através da lista de {@link ContaBean} informada.
//	 * 
//	 * @param infoContas
//	 */
//	public void escreveArquivoCsv(List<CsvConta> infoContas) {
//		var filePathNovoArquivo = Paths.get(filePath.getParent().toString() + "/" + NOME_ARQUIVO_FINAL);
//		
//		try (Writer writer = Files.newBufferedWriter(filePathNovoArquivo)) {
//			logger.info("Gerando arquivo final...");
//			
//			final CustomMappingCsvContaBean<CsvConta> mappingStrategy = new CustomMappingCsvContaBean<>();
//			mappingStrategy.setType(CsvConta.class);
//
//			final StatefulBeanToCsv<CsvConta> beanToCsv = new StatefulBeanToCsvBuilder<CsvConta>(writer).withSeparator(CSV_SEPARATOR).withMappingStrategy(mappingStrategy).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
//			beanToCsv.write(infoContas);
//
//			logger.info("Arquivo gerado com sucesso!");
//		} catch(IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
//			logger.error("Falha ao gerar arquivo!");
//			e.printStackTrace();
//		}
//
//	}
//
//}
