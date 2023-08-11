# Aluraflix - Plataforma de Vídeos - Java Spring Boot API REST

O Aluraflix é uma aplicação de plataforma de vídeos construída com Java Spring Boot, que permite aos usuários visualizar e gerenciar uma coleção de vídeos.

## Funcionalidades

- Listagem de vídeos disponíveis.
- Cadastro de novos vídeos.
- Atualização de informações de vídeos.
- Remoção de vídeos.
- Categorização de vídeos.

## Tecnologias Utilizadas

- Java 11
- Spring Boot
- Spring Data JPA
- Spring Web (Spring MVC)
- Banco de Dados (H2, apenas para aprendizado)
- Maven (gerenciamento de dependências)
- Swagger (para documentação da API - ainda não desenvolvido)

## Configuração do Ambiente

1. Clone este repositório: `git clone https://github.com/seu-usuario/seu-repositorio.git`
2. Navegue até o diretório do projeto: `cd aluraflix`
3. Configure as informações do banco de dados no arquivo `application.properties` ou `application.yml`.
4. Execute a aplicação: `mvn spring-boot:run` ou `./gradlew bootRun`.

## Documentação da API

A documentação da API está disponível usando o Swagger. Para acessar, inicie a aplicação e vá para: `http://localhost:8080/swagger-ui.html`

## Endpoints da API

- `GET /videos`: Retorna a lista de vídeos disponíveis.
- `POST /videos`: Cadastra um novo vídeo.
- `PUT /videos/{id}`: Atualiza informações de um vídeo existente.
- `DELETE /videos/{id}`: Remove um vídeo.
- ...

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.
