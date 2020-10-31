# Desafio - South System



* [JDK] (OpenJDK 8)
* [Maven] ( Maven 3.6.3)


# Execução

java -jar [artefato] --spring.output.ansi.enabled=always [URI_DO_ARQUIVO]

 
Exemplo:
java -jar desafio-validacao-receita-csv.jar --spring.output.ansi.enabled=always file:///D:/..../.../students.csv

exemplo: file:///D:/development/git/myRepo/spring-boot-batch-csv/src/main/resources/data/students.csv

O arquivo será salvo na pasta [processed] gerada no mesmo local do arterafato .jar, com o mesmo nome + timestamp

 
# Build do projeto

   mvn clean install
  