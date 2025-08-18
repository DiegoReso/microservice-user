# API de Gerenciamento de Tarefas

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33?style=flat-square&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=json-web-tokens&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-2C3E50?style=flat-square&logo=lombok&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat-square&logo=github-actions&logoColor=white)
![Kanban](https://img.shields.io/badge/Kanban-007ACC?style=flat-square&logo=trello&logoColor=white)

## Visão Geral do Projeto

Este projeto é uma **API de gerenciamento de tarefas** construída com uma arquitetura de **microsserviços**. O objetivo é criar um sistema robusto e escalável para gerenciar tarefas pessoais ou de equipe, começando com a base fundamental: o microsserviço de usuários. A arquitetura modular permite que cada serviço seja desenvolvido, implantado e escalado de forma independente, garantindo flexibilidade e resiliência ao sistema.

-----

## Microsserviço de Usuários

O microsserviço de usuários é a espinha dorsal do sistema, responsável por toda a gestão de usuários e autenticação. Ele é o primeiro componente em desenvolvimento e já conta com as funcionalidades essenciais.

### Tecnologias e Ferramentas

* **Java ☕ e Spring Boot:** A base do desenvolvimento, proporcionando agilidade, segurança e um ecossistema robusto.
* **DTO (Data Transfer Object):** Utilizado para padronizar a comunicação e expor apenas os dados necessários, garantindo maior segurança e flexibilidade. A implementação com o padrão **Builder do Lombok** torna o código mais legível e imutável.
* **PostgreSQL:** Banco de dados relacional escolhido para garantir a integridade e consistência dos dados dos usuários.
* **JWT (JSON Web Tokens):** Usado para autenticação segura e sem estado, permitindo uma comunicação eficiente entre os serviços.
* **Lombok:** Ferramenta que reduz o código boilerplate (repetitivo), tornando o desenvolvimento mais rápido e o código mais limpo.
* **Gradle:** Gerenciador de dependências e automação de build, garantindo um processo de desenvolvimento eficiente.
* **GitHub Actions:** Automatiza o pipeline de CI/CD (Integração e Entrega Contínuas), assegurando deploys rápidos e confiáveis a cada atualização.
* **Kanban (Trello):** Metodologia de gestão de projetos que ajuda a visualizar o fluxo de trabalho, organizar tarefas e manter o foco no progresso.

-----

### Funcionalidades (Endpoints)

O microsserviço de usuários expõe os seguintes endpoints REST para gerenciamento de dados:

* **`POST /users`**: Cria um novo usuário.
* **`POST /users/login`**: Realiza a autenticação do usuário e gera um token JWT.
* **`GET /users`**: Busca um usuário por e-mail.
* **`PUT /users`**: Atualiza os dados principais de um usuário, como nome e senha.
* **`DELETE /users/{email}`**: Exclui um usuário do sistema.
* **`POST /users/address`**: Adiciona um novo endereço a um usuário.
* **`PUT /users/address`**: Atualiza um endereço existente de um usuário.
* **`POST /users/phone`**: Adiciona um novo telefone a um usuário.
* **`PUT /users/phone`**: Atualiza um telefone existente de um usuário.

-----

## Próximos Passos

O projeto está em constante evolução. As próximas etapas incluem o desenvolvimento de outros serviços essenciais para o ecossistema da API:

* **BFF (Back-End For Front-End):** Um microsserviço dedicado a otimizar a comunicação entre a API e as interfaces de usuário, simplificando as interações e melhorando a performance.
* **Comunicação entre APIs (Feign):** Implementação de comunicação entre os microsserviços usando o Feign, facilitando as chamadas e a integração.
* **Microsserviço de Tarefas:** Responsável por toda a lógica de gerenciamento de tarefas. Utilizará **MongoDB** como banco de dados NoSQL para garantir flexibilidade e escalabilidade.
* **Microsserviço de Notificação:** Um serviço que usará um **Cron Job** para verificar, a cada 5 minutos, as tarefas pendentes e enviar notificações por e-mail. O **Thymeleaf** será usado para a criação de templates de e-mail dinâmicos e elegantes.

-----

## Como Executar o Projeto Localmente

**Pré-requisitos:**

* JDK 17 ou superior
* Docker e Docker Compose
* Acesso a um cliente de banco de dados para PostgreSQL (opcional)

**Passos:**

1.  Clone este repositório:
    ```bash
    git clone git@github.com:DiegoReso/microservice-user.git
    cd seu-projeto
    ```
2.  Configure suas variáveis de ambiente, como credenciais do banco de dados, em um arquivo `.env` ou nas configurações do seu ambiente de desenvolvimento.
3.  Execute a aplicação usando Gradle:
    ```bash
    ./gradlew bootRun
    ```
4.  A API estará disponível em `http://localhost:8080` (porta padrão do Spring Boot).
