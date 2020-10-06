# Desafio - South System



* [JDK] (OpenJDK 8)
* [Maven] ( Maven 3.6.3)


# Execução

java -jar [artefato] --spring.output.ansi.enabled=always [URI_DO_ARQUIVO]

 
Exemplo:
java -jar desafio-validacao-receita-csv.jar --spring.output.ansi.enabled=always file:///D:/..../.../students.csv

O arquivo será salvo na pasta [processed] gerada no mesmo local do arterafato .jar, com o mesmo nome + timestamp


referencia de referencia para URI
https://pt.wikipedia.org/wiki/URI

 
# Build do projeto

   mvn clean install
  
