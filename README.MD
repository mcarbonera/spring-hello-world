Estudei tanto java que fiquei javiado.
_______________________________________
Curso de Spring:
https://www.udemy.com/course/spring-boot-ionic
_______________________________________
Esse Hello World tunado contém Spring Boot, Jpa, implementação de uma arquitetura Rest. 

A parte de banco foi feito em h2 em uma parte da video aula por uma questão de facilidade de uso.
Porém, na etapa de migrar para mysql eu fiz para oracle e, por isso, não foi possível seguir a
parte de deploy no Heroku.

Particularidades: 
    -> Foi necessário especificar o nome da tabela e schema. O h2 infere o nome da tabela pelo nome da classe java. Mas justamente pela possibilidade de migração, é uma boa prática especificar mesmo assim.
    -> A geração de sequences fica diferente (mysql tem autoincrement, oracle não. É necessário especificar o nome do SequenceGenerator).
    -> O driver do banco oracle não está no repositório Maven, então é necessário instalar local com o comando "mvn install:install-file -Dfile=C:\Users\Luiz\IdeaProjects\spring-hello-world\src\main\resources\lib\ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar".
    -> Após instalação, importar no arquivo pom.xml.
    -> o arquivo de configuração "application-dev.properties" é semelhante.
_______________________________________
