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
```
SafeZone  
├── controller             # Camada de Controllers (REST)  
├── dto                   # DTOs de entrada e saída  
├── model                 # Entidades de domínio  
├── repository            # Repositórios JPA  
├── service               # Lógica de negócio  
├── config                # Configurações como ModelMapper e Security  
└── resources             # application.properties e migrations
```
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

2. **Configure o banco PostgreSQL no `application.properties`**
```
spring.datasource.url=jdbc:postgresql://localhost:5432/safezone
spring.datasource.username=postgres
spring.datasource.password=dudu0602
```

4. **Rode a aplicação**

5. **Acesse a documentação Swagger**
http://localhost:8080/swagger-ui.html

---

## 📌 Exemplos de Endpoints

### 👤 UsuarioController
- `GET /usuarios` → Lista todos os usuários com paginação (Pageable)
- `GET /usuarios/{id}` → Retorna os detalhes de um usuário pelo ID
- `GET /usuarios/email/{email}` → Busca usuário pelo e-mail
- `GET /usuarios/filtro?nome=joao` → Filtra usuários por nome (parcial)
- `POST /usuarios` → Cadastra um novo usuário
```json
{
  "nome": "Maria Eduarda",
  "email": "madu@email.com",
  "senha": "Senha123",
  "telefone": "11999887766"
}
```
- `PUT /usuarios/{id}` → Atualiza um usuário existente
- `DELETE /usuarios/{id}` → Remove um usuário

### 📍 LocalizacaoController
- `GET /localizacoes` → Lista todas as localizações com paginação
- `GET /localizacoes/{id}` → Retorna os detalhes de uma localização pelo ID
- `POST /localizacoes` → Cadastra uma nova localização
```json
{
  "cidade": "São Paulo",
  "estado": "SP",
  "bairro": "Centro",
  "rua": "Rua A",
  "cep": "01000-000"
}
```
- `PUT /localizacoes/{id}` → Atualiza uma localização
- `DELETE /localizacoes/{id}` → Remove uma localização

### 🛟 AreaSeguraController
- `GET /areas-seguras` → Lista áreas seguras com paginação e filtros por cidade, estado, tipo e capacidade mínima
  - Exemplo: `GET /areas-seguras?cidade=Guarulhos&estado=SP&tipo=GINASIO&capacidadeMin=100`
- `GET /areas-seguras/{id}` → Retorna os detalhes de uma área segura pelo ID
- `POST /areas-seguras` → Cadastra uma nova área segura
```json
{
  "nome": "Ginásio Central",
  "endereco": "Rua Av. Central",
  "responsavel": "Marco Aurelio",
  "telefone": "11939807764",
  "tipo": "GINASIO",
  "capacidade": 300,
  "localizacaoId": 1
}
```
- `PUT /areas-seguras/{id}` → Atualiza uma área segura existente
- `DELETE /areas-seguras/{id}` → Remove uma área segura

### 🔔 AlertaController
- `GET /alertas` → Lista todos os alertas com paginação
- `GET /alertas/{id}` → Retorna os detalhes de um alerta por ID
- `POST /alertas` → Cadastra um novo alerta
```json
{
  "descricao": "Enchente detectada",
  "tipoDesastre": "ENCHENTE",
  "nivel": "ALTO",
  "localizacaoId": 1
}
```
- `DELETE /alertas/{id}` → Remove um alerta

### 📊 EstatisticaController
- `GET /estatisticas/quantidade-alertas` → Retorna total de alertas por tipo
- `GET /estatisticas/media-temperatura` → Retorna média de temperatura dos alertas
- `GET /estatisticas/usuarios-ativos` → Retorna usuários com mais alertas registrados

---

## 📽️ Vídeos

| Tipo           | Link                |
|----------------|---------------------|
| 🎬 Demonstração da Solução | _(Inserir link do YouTube)_ |
| 🗣️ Pitch (até 3 minutos)     | _(Inserir link do YouTube)_ |

---

## 👥 Equipe

- Eduardo Miguel Forato Monteiro – RM 555871
- Cícero Gabriel Oliveira Serafim – RM 556996
- Alice Teixeira Caldeira - RM 556293
