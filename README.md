# Projeto Cadastro de Sócio Torcedor
- _Informaçães técnicas sobre o projeto estão após a descrição_

Eu, como usuário, quero me cadastrar informando **meu e-mail e o meu Time do Coração através** de uma API que me permite 
participar de algumas campanhas. 

Para tanto, os critérios de aceite dessa história são:

 - Dado um **E-mail que já existe**, informar que o cadastro já foi efetuado, porém, caso o cliente não tenha nenhuma campanha 
   associada o serviço deverá enviar as novas campanhas como resposta.
   
 - O Cadastro deve ser composto de:
    - Nome Completo;
    - E-mail;
    - Data de Nascimento;
    - Meu Time do Coração;

 - **O Cliente não pode ter mais de um cadastro ativo;**

 - Ao efetuar o cadastro, o serviço deverá repassar uma lista de campanhas o qual está associado ao time do coração.
 
 - O Consumo das listas das campanhas deve ser feita via Serviço exposto conforme descrito no exercício anterior;
 
 - O Cadastramento das campanhas deverá ser feito via Serviço (API, conforme descrito no exercício anterior)

**A associação do cadastro do Cliente juntamente com as Campanhas deverá ocorrer em um segundo passo, utilizando a API 
construída no próximo exercício);**

_O que se espera para esse exercício - dicas e direcionamentos:_

 - Os serviços devem receber e responder JSON;
 - Faça o uso de “Mocks” principalmente nos testes;
 - Pense em como documentar os cenários desenvolvidos (Testes sempre são uma boa forma de documentar);
 - Ao finalizar o desenvolvimento você deve compartilhar o código pelo Github ou Bitbucket;
 - Fique à vontade para entrar em contato e tirar dúvidas;
 - Juntamente com o Código, deve-se documentar a estratégia utilizada para a criação da aplicação, a arquitetura 
    utilizada e os padrões. A documentação pode ser feita via GIT/Bitbucket e respondido por email com o link.
 - Em caso de uso do Git/Bitbucket não esqueça de criar o .gitignore.
 - Não precisa ser construído nenhuma tela para os cadastros;
 - **Alguns requisitos não funcionais devem ser previstos:**
    - O Serviço será acessado de forma acentuada, ou seja, a previsão é que o serviço receba 100 requisições por segundo;
    - A aplicação deverá prever falhas de integração entre as APIs, não deixando o cliente sem nenhuma resposta;

## Informações sobre o projeto

- Para esta questão(2) foi implementado somente o method PUT, conforme solicitado, o CRUD completo foi implementado
na questão 1.

- O caminho **base** para as os endpoins é : /api/v1
  - Para esta aplicação temos :  **/api/v1/socios** 

- Porta da aplicação :**9080**

- Para iniciar a aplicação execute : --> **gradle bootRun** 

- Para ver a **documentação** das APIS inclusive os exemplos usando **curl** veja --> **/api/swagger-ui.html** (ex: http://localhost:9080/api/swagger-ui.html)
    -  Para documentar a API usei o **Swagger** caso não conheça o Swagger veja : --> http://swagger.io/ 
    
- A aplicação contém um banco de dados **MongoDB** imbutido que é inicializado junto com aplicação    
    - Porta para acessar o MongoDB: **54321**
    
- Para log veja o arquivo sociotorcedor.log criado na raiz da aplicação.

- Para os recursos expostos eu usei os seguinte:
    - **200 OK** - para GET requests.
    - **201** Created - para POST.
    - **204** No Content - para PUT, PATCH, e DELETE requests.

- Java code coverage :      

## Tecnologias e frameworks utilizados

- **Java versão 8** - 

- **Gradle** - _Para construção do projeto e gerenciamento de frameworks_

- **Spring boot** - _Padrão para construção de projetos usando Spring_

- **Spring HATEOAS** - _Spring HATEOAS fornece algumas APIs para facilitar a criação de representações REST que seguem 
    o princípio HATEOAS quando se trabalha com Spring e especialmente Spring MVC. O problema central que ele tenta 
    abordar é a criação de links e a montagem de representações._ 

- **Spring Cloud Feign** -  _Feign é um projeto que faz parte do grande guarda-chuvas de soluções do Spring Cloud e ele 
   basicamente é utilizado para integração com serviços Rest._  

- **Spring Cloud Hystrix** -  _O Hystrix implementa o padrão Circuit Breaker, que de forma bem rápida é um failover para
 chamadas entre micro serviços, ou seja, caso um micro serviço estiver fora do ar um método de fallback é chamado_  
    
- **MongoDB** - _O MongoDB é um banco de dados de documentos de código aberto que fornece alto desempenho, alta disponibilidade 
  e dimensionamento automático. Um registro no MongoDB é um documento, que é uma estrutura de dados composta de pares de campo e valor.
 Os documentos MongoDB são semelhantes aos objetos JSON._ 

- **Swagger** - _Swagger é uma poderoso framework de código aberto apoiada por um grande ecossistema de ferramentas para projetar,
 Compilar, documentar e consumir as APIs RESTful._

- **AssertJ** - _O AssertJ core é uma biblioteca Java que fornece uma interface fluente para escrever asserções. Seu principal objetivo é
Para melhorar a legibilidade do código de teste e facilitar a manutenção dos testes._

- **Spring Data MONGO** - _Spring Data MONGO: Tecnologia responsável por gerar boa parte do código relacionado a camada de persistência
e mapeamnto Documento(MongoDb) com a classe Java , assim como algumas interfaces básicas para CRUD dos documentos._ 

- **Spring Web MVC** - _Framework web usado como solução para a definição de componentes seguindo o modelo arquitetural REST._ 

- **Jackson** - _API para conversão de dados Java em Json e vice-versa._ 