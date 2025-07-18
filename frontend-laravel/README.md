<p align="center"><a href="https://laravel.com" target="_blank"><img src="https://raw.githubusercontent.com/laravel/art/master/logo-lockup/5%20SVG/2%20CMYK/1%20Full%20Color/laravel-logolockup-cmyk-red.svg" width="400" alt="Laravel Logo"></a></p>

<p align="center">
<a href="https://github.com/laravel/framework/actions"><img src="https://github.com/laravel/framework/workflows/tests/badge.svg" alt="Build Status"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/dt/laravel/framework" alt="Total Downloads"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/v/laravel/framework" alt="Latest Stable Version"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/l/laravel/framework" alt="License"></a>
</p>

# CleanCircuit Frontend

Este é o frontend do CleanCircuit, uma aplicação web para gestão de investimentos, desenvolvida em Laravel 12, Blade, TailwindCSS e Vite. Ele consome a API REST do backend (Spring Boot) para todas as operações.

## Funcionalidades

- Cadastro e autenticação de usuários
- Recuperação e redefinição de senha por e-mail
- Upload e remoção de imagem de perfil
- Cadastro e gerenciamento de ativos financeiros (ações, FIIs, ETFs, REITs, criptomoedas, renda fixa, etc.)
- Visualização de carteira consolidada, com variação e resumo por tipo de ativo
- Interface responsiva e moderna

## Como executar

### Pré-requisitos

- PHP 8.2+
- Composer
- Node.js 18+ e NPM/Yarn

### Passos

1. Instale as dependências:
   ```sh
   composer install
   npm install
   ```
2. Configure o arquivo `.env`:
   ```
   API_URL=http://localhost:8080
   ```
   (Aponte para a URL do backend)
3. Gere a chave da aplicação:
   ```sh
   php artisan key:generate
   ```
4. Rode o servidor:
   ```sh
   php artisan serve
   npm run dev
   ```
   O frontend estará disponível em `http://localhost:8000`.

### Configuração do arquivo .env:

- Defina API_URL para a URL do backend (ex: http://localhost:8080).
- Defina BRAPI_TOKEN com seu token de acesso à BRAPI, use o token `g3dCqDLPq5MtN8313Nao6R` caso não consiga gerar um token próprio.
- O frontend utiliza SQLite por padrão, não sendo necessário configurar banco de dados relacional.

## Observação sobre o Backend

- Certifique-se de que o backend está rodando e acessível para o funcionamento do frontend.

## Licença

MIT
