# CleanCircuit

CleanCircuit é uma aplicação web para gestão de investimentos, composta por um backend em Spring Boot e um frontend em Laravel. O sistema permite o cadastro de usuários, gerenciamento de carteiras de ativos (ações, FIIs, ETFs, REITs, criptomoedas, renda fixa, etc.), operações de compra/venda, upload de imagem de perfil, além de funcionalidades de autenticação e recuperação de senha.

## Estrutura do Projeto

```
/
├── backend-Spring/      # Backend em Java Spring Boot (API REST)
├── frontend-laravel/    # Frontend em Laravel (PHP)
└── README.md            # Este arquivo
```

## Funcionalidades

- Cadastro e autenticação de usuários
- Recuperação e redefinição de senha por e-mail
- Upload e remoção de imagem de perfil
- Cadastro e gerenciamento de ativos financeiros
- Visualização de carteira consolidada, com variação e resumo por tipo de ativo
- Integração com BRAPI para consulta de ativos
- Comunicação entre frontend e backend via API REST

## Tecnologias Utilizadas

- **Backend:** Java 21, Spring Boot, Spring Data JPA, PostgreSQL, RabbitMQ, Spring Mail, BRAPI API
- **Frontend:** Laravel 12, PHP 8.2, Blade, TailwindCSS, Vite
- **Outros:** Docker (opcional), Composer, NPM/Yarn

## Como Executar

### Pré-requisitos

- Java 21+
- Node.js 18+ e NPM/Yarn
- Composer
- PostgreSQL
- RabbitMQ

### Backend (Spring Boot)

1. Configure o banco de dados PostgreSQL e RabbitMQ conforme `backend-Spring/src/main/resources/application.properties`.
2. Instale as dependências:
   ```sh
   cd backend-Spring
   ./mvnw clean install
   ```
3. Rode a aplicação:
   ```sh
   ./mvnw spring-boot:run
   ```
   A API estará disponível em `http://localhost:8080`.

### Frontend (Laravel)

1. Instale as dependências:
   ```sh
   cd frontend-laravel
   composer install
   npm install
   ```
2. Configure o arquivo `.env` com a URL da API backend:
   ```
   API_URL=http://localhost:8080
   ```
3. Rode o servidor de desenvolvimento:
   ```sh
   php artisan serve
   npm run dev
   ```
   O frontend estará disponível em `http://localhost:8000`.

## Documentação

- O backend expõe endpoints REST documentados via código-fonte.
- O frontend utiliza rotas Laravel e Blade para renderização das páginas.
- Para mais detalhes, consulte os arquivos README individuais em cada subdiretório.


## Licença

MIT

---

> Este projeto integra backend e frontend, sendo necessário rodar ambos para funcionamento completo.
