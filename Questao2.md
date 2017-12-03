************************************************************************************************************************
**Juntamente com o Código, deve-se documentar a estratégia utilizada para a criação da aplicação, a arquitetura utilizada
e os padrões. A documentação pode ser feita via GIT/Bitbucket;**
************************************************************************************************************************

Para o projeto optei por utilizar **Spring Boot** o qual se tornou o padrão de facto para projetos java, pelas facilidades,
integrações oferecidas e pelo fato de vir com **Tomcat** embarcado.

Para persistência optei por utilizar como banco de dados o **MongoDB** pela _flexibilidade , desempenho e similaridade (BSON -
JSON)_ com o formato Json que será trafegado nas APIS.

Também optei por utilizar **Spring Data Mongo** por abstrair e facilitar toda a parte de criação dos repositórios 
e servir como ponto de partida para os comandos de CRUD de persistencia de dados. 
Já ofere algumas interfaces básicas como a de busca e inserção de dados.

Para criar os serviços **REST** optei pelo **Spring Rest** pela facilidade e integração com Spring boot ao invés de utilizar
**o JAX-RS com JERSEY.**

Também utilizei o **Spring Cloud**(**Feing e Hystrix**) para consumir serviços REST e **Circuit Breaker**

Para criar a aplicação foi implementado o serviço Rest **POST** para o sócio torcedor.

Para tal foram criadas 2 entidades **SocioTorcedor** e **SocioTorcedorResource**:
 - _A entidade **SocioTorcedor** faz a modelagem Java - MongoDB_
 - _A entidade **SocioTorcedorResource** é a entidade que vai ser serializada para ser recebida e enviada pelas interfaces da
   API em formato Json. Tende a ser mais leve que a entidade que de domonio e contém anotações para documentação Swagger_.

O serviço de criação de Sócio Torcedor é acionado pelo método **POST** em **/api/v1/socios** , algumas informações sobre:
 - _O conteúdo trafegado entre o cliente e o serviço suporta o formato **application/json**._
 - _Recebe um Json com os seguintes campos (**nomeCompleto, email, dataNascimento e timeCoracao**)_
 - _Foi criado um **índice unico** no **MongoDB** para **e-mail**, ao tentar se cadastar um sócio com um e-mail já 
   cadastrado, a api retorna HTTP Status **409 CONFLICT** "**Usuário já cadastrado**"_
 - _OS campos de data devem ter o seguinte formato : "**YYYY-MM-DD**"_
 - _Após a criação do Sócio Torcedor é retornado o status **CREATED 201** e um lista com as campanhas cadastradas 
   para o time do coração_
 - _Caso o serviços de campanhas esteja indisponível, será retornado somente o status **CREATED 201**_.


Documento no **MongoDB** - **sociotorcedor**

#### Pacotes e componentes:

**br.com.sociotorcedor** - _Pacote default da aplicação_
    - **ApplicationStarter** - _Configurações do Spring Boot e inicializador da aplicação_

**br.com.sociotorcedor.configuracao** - _Pacote para classes de configuração da aplicação_
    - **SwaggerConfig** - _Configura o Swagger na aplicação para mapear todos os endpoints Rest_

**br.com.sociotorcedor.domain** - _Pacote para as classes de dominio_
    - **SocioTorcedor** - _Entidade para mapear classe java para o documento MongoDB._

**br.com.sociotorcedor.exception** - _Pacote para as classes de exception_
    - **SocioTorcedorJaCadastradoException** - _Exception que será lançada quando se tentar cadastrar um torcedor com e-mail já_
      **cadastrado**

**br.com.sociotorcedor.repository** - _Pacote para as classes de repositório da aplicação_
    - **SocioTorcedorRepository** - _Interface para CRUD de Sócio Torcedor_

**br.com.sociotorcedor.rest.api** - _Pacote para as classes de endpoint Rest_
    - **SocioTorcedorController** - _Classe para expor os serviços REST de Sócio Torcedor_

**br.com.sociotorcedor.rest.domain** - _Pacote para as classes a serem serializadas nas APIs Rest_
    - **CampanhaResource** - _Classes para receber os dados da campanha da API de campanhas_
    - **ErrorInfo** - _Classe com informações de erros retornada pela API_
    - **SocioTorcedorResource** - _Classe para receber os dados do SocioTorcedor na API Rest_

**br.com.sociotorcedor.service**
    - **CampanhaService** - _Serviço para buscar as campanhas de um determinado Time_
    - **SocioTorcedorService** -  _Interface para encapsular as operaçoes de CRUD e Regras de Sócio Torcedor_


#### Testes e Regras:

**test/br.com.sociotorcedor.rest.api** - _Pacote para classes de test das APIs_

   - **SocioTorcedorControllerTest** - _Valida a interface e regras da API REST de Sócio Torcedor_
   - **quandoSocioTorcedorCadastradoDeveRetornarListaDeCampanhas** - _Valida se o cadastro é efetuado com sucesso e
     se as campanhas são retornadas pela API._

   - **quandoServicoDeCampanhasEstiverForaHystrixDeveChamarCallbackECadastrarSocioERetornarCreated** - _Valida quando
     o serviço de campanhas estiver indisponivel o usuário é cadastrado com sucesso._

   - **naoDeveCadastrarDoisUsuariosComMesmoEmail** - _Valida se ao tentar cadastrar dois usuários com mesmo e-mail a mensagem
     de "Usuário já cadastrado"  é retornada._

************************************************************************************************************************