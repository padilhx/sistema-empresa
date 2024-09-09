# Sistema Empresa

Um sistema de gerenciamento para empresas que permite a realização de transações entre clientes e empresas, com suporte para notificações e callbacks via webhooks.

## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Testes](#testes)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Descrição

O **Sistema Empresa** é um projeto desenvolvido em Java com Spring Boot para facilitar o gerenciamento financeiro de empresas e seus clientes. Ele permite a realização de transações (depósitos e saques) e o envio de notificações via e-mail e callbacks via webhook para empresas.

## Funcionalidades

- Realização de transações financeiras entre clientes e empresas.
- Suporte para dois tipos de transações: **Depósito** e **Saque**.
- Envio de notificações automáticas para clientes após uma transação.
- Callback para empresas via webhook, enviando dados detalhados da transação.
- Manipulação de erros robusta e tratamento de exceções.

## Pré-requisitos

- **Java 11** ou superior.
- **Maven 3.6** ou superior.
- **Spring Boot 2.5** ou superior.
- Dependências adicionais listadas no arquivo `pom.xml`.

## Instalação

Siga estas etapas para configurar o projeto localmente:

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/sistema-empresa.git
    cd sistema-empresa
    ```

2. Compile o projeto usando o Maven:
    ```bash
    mvn clean install
    ```

3. Execute o projeto:
    ```bash
    mvn spring-boot:run
    ```

## Uso

- A API estará disponível por padrão em `http://localhost:8080`.
- Use ferramentas como `Postman` ou `cURL` para enviar requisições.

### Exemplos de Requisições

**Realizar uma Transação:**

```bash
POST /transacoes/realizar
Content-Type: application/json

{
  "clienteId": 1,
  "empresaId": 2,
  "valor": 100.50,
  "tipo": "DEPOSITO"
}
```

##Resposta esperada 
``` 
{
  "mensagem": "Transação realizada com sucesso"
}

```

## Testes

Para executar os testes, use o seguinte comando:

```bash
mvn test

