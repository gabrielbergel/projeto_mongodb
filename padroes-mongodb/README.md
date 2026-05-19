# Atividade Integradora — Padrões Criacionais + MongoDB Atlas

Projeto Java com Maven integrando padrões criacionais com MongoDB Atlas.

## Padrões usados

- Singleton
- Factory Method

## Collections usadas

- usuarios
- fretes
- pedidos

## Antes de executar

Abra o arquivo:

```txt
src/main/java/com/example/database/MongoConnection.java
```

E troque a URI do MongoDB Atlas:

```java
String uri = "mongodb+srv://SEU_USUARIO:SUA_SENHA@SEU_CLUSTER.mongodb.net/?retryWrites=true&w=majority";
```

Substitua:

- `SEU_USUARIO`
- `SUA_SENHA`
- `SEU_CLUSTER`

pelos dados do seu MongoDB Atlas.

## Como executar

No terminal, dentro da pasta do projeto:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.example.Main"
```

Se preferir, execute a classe `Main.java` diretamente pela IDE.

## O que o projeto faz

### Singleton + MongoDB

- Cria cache global de usuários.
- Busca primeiro no cache.
- Se não encontrar, busca no MongoDB.
- Implementa:
  - `addUser()`
  - `removeUser()`
  - `getUserByUsername()`
- Possui expiração simples de cache.

### Factory Method + MongoDB

- Salva fretes na collection `fretes`.
- Cria tipos de transporte:
  - Caminhão
  - Navio
  - Drone
  - Trem
- Usa cache Singleton para buscar valor de frete.
- Grava pedidos na collection `pedidos`.

## Perguntas de Reflexão

### Qual padrão foi mais fácil de integrar ao banco?

O Singleton foi o mais fácil, pois centralizou o cache e o acesso compartilhado aos dados.

### Como o GitHub Copilot ajudou?

Ajudou sugerindo trechos repetitivos de código, métodos de CRUD e estruturação das classes.

### Qual padrão apresentou maior complexidade?

O Factory Method foi mais complexo, pois exigiu mais classes e uma separação maior de responsabilidades.
