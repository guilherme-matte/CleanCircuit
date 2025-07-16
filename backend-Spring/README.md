# CleanCircuit Backend

Este é o backend do CleanCircuit, uma API REST desenvolvida em Spring Boot para gestão de investimentos. Ele fornece autenticação, gerenciamento de usuários, operações de ativos financeiros, integração com BRAPI, envio de e-mails e muito mais. O frontend consome esta API para todas as operações.

## Funcionalidades

- Cadastro, autenticação e gerenciamento de usuários
- Recuperação e redefinição de senha por e-mail
- Upload e remoção de imagem de perfil
- Cadastro e operações de ativos financeiros (ações, FIIs, ETFs, REITs, criptomoedas, renda fixa, etc.)
- Resumo consolidado da carteira do usuário
- Integração com BRAPI para consulta de ativos
- Envio de e-mails (boas-vindas, redefinição de senha) via RabbitMQ
- API REST pronta para integração com frontend

## Banco de Dados

- **Banco utilizado:** PostgreSQL
- **Nome padrão:** `CleanCircuit`
- **Configuração:**  
  O banco de dados deve estar criado e acessível antes de iniciar o backend.  
  Configure as credenciais e o nome do banco no arquivo [`src/main/resources/application.properties`](src/main/resources/application.properties):

  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/CleanCircuit
  spring.datasource.username=postgres
  spring.datasource.password=admin
  ```

  > Altere o nome do banco, usuário e senha conforme sua necessidade.

- **Não é necessário criar as tabelas manualmente:** o Spring Boot irá criar todas as tabelas necessárias automaticamente ao iniciar a aplicação.
- O backend **só funcionará corretamente se o banco de dados estiver criado e acessível**.

## Como executar

### Pré-requisitos

- Java 21+
- Maven
- PostgreSQL (com banco `CleanCircuit` criado)
- RabbitMQ

### Passos

1. Instale as dependências:
   ```sh
   ./mvnw clean install
   ```
2. Configure o arquivo `src/main/resources/application.properties` conforme seu ambiente.
3. Rode a aplicação:
   ```sh
   ./mvnw spring-boot:run
   ```
   A API estará disponível em `http://localhost:8080`.

## Observações

- O backend deve estar rodando e conectado ao banco para que o frontend funcione corretamente.
- Endpoints REST documentados via código-fonte.
- Para integração com BRAPI, configure o token no arquivo de propriedades (`BRAPI_TOKEN`).

## Licença