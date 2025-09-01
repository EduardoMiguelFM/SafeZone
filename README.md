# 🚨 SafeZone

A **SafeZone** é uma aplicação RESTful desenvolvida em Java com foco em **respostas a situações de emergência**, como desastres naturais. Seu objetivo é **registrar alertas críticos**, indicar **áreas seguras acessíveis** e **gerenciar a localização das ocorrências**.

---

## 🎯 Objetivo

Auxiliar pessoas e instituições a gerenciar riscos em momentos de extrema urgência, como enchentes, deslizamentos ou situações climáticas perigosas, com:
- Cadastro e controle de **alertas**
- Gerenciamento de **áreas seguras**
- Consulta e registro de **localizações afetadas**
- Interface com documentação automática via Swagger
- Integração com API externa de clima para decisões baseadas em dados

---

## 🧱 Arquitetura e Organização do Projeto

O projeto está organizado seguindo o padrão **Camadas**:
SafeZone  
├── controller             # Camada de Controllers (REST)  
├── dto                   # DTOs de entrada e saída  
├── model                 # Entidades de domínio  
├── repository            # Repositórios JPA  
├── service               # Lógica de negócio  
├── config                # Configurações como ModelMapper e Security  
└── resources             # application.properties e migrations

---

## ☁️ Integração com API Externa de Clima

A aplicação **SafeZone** se conecta com uma API externa de clima para obter dados em tempo real sobre:

- **Temperatura**
- **Condições climáticas atuais**
- **Previsões de risco de desastre**

Esses dados são consumidos automaticamente ao cadastrar um novo alerta e enriquecem o sistema com informações confiáveis e atualizadas.

---

## 📊 Estatísticas e Dashboards

A API de clima também serve como base para a geração de **estatísticas** e **dashboards** utilizados por órgãos de segurança e pela população. Os principais usos incluem:

- Identificação de **regiões com maior frequência de desastres**
- Monitoramento de **tendências climáticas locais**
- Análises visuais de alertas emitidos por tipo e região
- Suporte à **tomada de decisão para evacuação ou prevenção**

Essas visualizações são integradas ao sistema por meio de endpoints específicos com **retorno agregado** e podem ser acessadas por ferramentas de BI e painéis personalizados.

---

## 💻 Tecnologias e Dependências

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security + JWT
- Hibernate Validator
- PostgreSQL
- ModelMapper
- OpenAPI (Swagger)
- API externa de clima

---

## 🛠️ Como executar localmente

1. **Clone o projeto**  
git clone https://github.com/seu-usuario/SafeZone.git  
cd SafeZone

2. **Configure o banco PostgreSQL no `application.properties` (se rodar sem Docker)**
spring.datasource.url=jdbc:postgresql://localhost:5432/safezone
spring.datasource.username=postgres
spring.datasource.password=dudu0602

3. **Rode a aplicação**

4. **Acesse a documentação Swagger**
http://localhost:8080/swagger-ui.html

---

## 📌 Exemplos de Endpoints

### 👤 LOGIN
- POST /auth/login → Login com geração de token JWT
{
  "email": "SafeZoneAdm@safezone.com.br",
  "senha": "SafeZone123"
}

### 👤 UsuarioController
- GET /usuarios → Lista todos os usuários com paginação
- GET /usuarios/{id} → Retorna os detalhes de um usuário pelo ID
- POST /usuarios → Cadastra um novo usuário
- PUT /usuarios/{id} → Atualiza um usuário existente
- DELETE /usuarios/{id} → Remove um usuário

### 📍 LocalizacaoController
- GET /localizacoes → Lista todas as localizações
- POST /localizacoes → Cadastra uma nova localização
- PUT /localizacoes/{id} → Atualiza uma localização
- DELETE /localizacoes/{id} → Remove uma localização

### 🛟 AreaSeguraController
- GET /areas-seguras → Lista áreas seguras
- POST /areas-seguras → Cadastra uma nova área segura
- PUT /areas-seguras/{id} → Atualiza uma área segura
- DELETE /areas-seguras/{id} → Remove uma área segura

### 🔔 AlertaController
- GET /alertas → Lista todos os alertas
- POST /alertas → Cadastra um novo alerta
- DELETE /alertas/{id} → Remove um alerta

### 📊 EstatisticaController
- GET /estatisticas/quantidade-alertas → Retorna total de alertas por tipo
- GET /estatisticas/media-temperatura → Retorna média de temperatura dos alertas
- GET /estatisticas/usuarios-ativos → Retorna usuários com mais alertas registrados

---

## 📽️ Vídeos

| Tipo           | Link                |
|----------------|---------------------|
| 🎬 Demonstração da Solução |[ _(Video Demonstrando)_](https://youtu.be/nWsTCHr96GM) |
| 🗣️ Pitch (até 3 minutos)     |[ _(Pitch)_](https://youtu.be/s8PfVH_Bmac) |

---

## 👥 Equipe

- Eduardo Miguel Forato Monteiro – RM 555871
- Cícero Gabriel Oliveira Serafim – RM 556996
- Alice Teixeira Caldeira - RM 556293

---

## 🐳 Docker e Docker Compose

A aplicação SafeZone foi containerizada utilizando Docker e Docker Compose, garantindo ambientes padronizados e reprodutíveis, além de simplificar o deploy.

### 🔹 Pré-requisitos
- Docker Desktop (Windows/Mac) ou Docker Engine (Linux)
- Docker Compose V2 (já incluído no Docker Desktop)

Verifique se está instalado:
docker --version
docker compose version

### 🔹 Como rodar com Docker Compose
1. Clone o projeto
git clone https://github.com/seu-usuario/SafeZone.git
cd SafeZone

2. Suba os containers
docker compose up -d --build

3. Verifique se os serviços estão rodando
docker ps
👉 Deve aparecer:
- safezone_app (Spring Boot)
- safezone_db (PostgreSQL)

4. Acesse a aplicação
- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html

5. Derrube os containers
docker compose down

---

### 🔹 Estrutura do docker-compose.yml
services:
  db:
    image: postgres:15
    container_name: safezone_db
    restart: always
    environment:
      POSTGRES_USER: safezone
      POSTGRES_PASSWORD: safezone123
      POSTGRES_DB: safezonedb
    ports:
      - "5433:5432"
    networks:
      - safezone_net
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U safezone -d safezonedb"]
      interval: 10s
      retries: 5

  app:
    build: .
    container_name: safezone_app
    depends_on:
      db:
        condition: service_healthy
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/safezonedb
      SPRING_DATASOURCE_USERNAME: safezone
      SPRING_DATASOURCE_PASSWORD: safezone123
    ports:
      - "8080:8080"
    networks:
      - safezone_net
    restart: always
    user: "1000:1000"

volumes:
  db_data:

networks:
  safezone_net:

---

### 🔹 Troubleshooting
- ❌ Erro: porta 5432 já está em uso  
  👉 Pare outros Postgres locais ou altere a porta no docker-compose.yml.

- ❌ Banco não sobe  
  👉 Confira logs:
  docker logs safezone_db

- ❌ Aplicação não conecta no banco  
  👉 Certifique-se que a URL no application.properties usa db como host:
  spring.datasource.url=jdbc:postgresql://db:5432/safezonedb
