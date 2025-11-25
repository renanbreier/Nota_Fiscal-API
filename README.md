# NotaFiscal-API

Uma **API REST** construída com **Java + Spring Boot**, que implementa
operações CRUD completas para emissão e gerenciamento de notas fiscais.
A arquitetura segue o padrão **MVC (Model-View-Controller)**.

------------------------------------------------------------------------

## 🚀 Funcionalidades

-   Criar, ler, atualizar e deletar notas fiscais
-   Validação básica de dados
-   Endpoints REST para consumir e gerenciar notas
-   Estrutura organizada em camadas (model, service, controller)
-   Exemplo de uso para backend de emissão de notas

------------------------------------------------------------------------

## 📦 Tecnologias utilizadas

-   Java 11+
-   Spring Boot
-   Maven

------------------------------------------------------------------------

## 📁 Estrutura do Projeto

    NotaFiscal-API/
    ├── src/
    │   ├── main/
    │   ├── java/
    │   │   └── com/renanbreier/notafiscal/
    │   │       ├── controller/
    │   │       ├── model/
    │   │       └── service/
    │   └── resources/
    │       └── application.properties
    ├── pom.xml
    └── README.md

------------------------------------------------------------------------

## 🛠️ Como executar

1.  Clone o repositório:

``` bash
git clone https://github.com/renanbreier/NotaFiscal-API.git
cd NotaFiscal-API
```

2.  Execute com Maven:

``` bash
mvn clean install
mvn spring-boot:run
```

A API iniciará em `http://localhost:8080`.

------------------------------------------------------------------------

## 📬 Endpoints de Exemplo

    Método   Endpoint      Descrição
    -------- ------------- ----------------------
    GET      /notas        Lista todas as notas
    GET      /notas/{id}   Busca nota por ID
    POST     /notas        Cria uma nota
    PUT      /notas/{id}   Atualiza nota
    DELETE   /notas/{id}   Remove nota

### Exemplo de payload (POST)

``` json
{
  "numero": "001",
  "dataEmissao": "2025-11-24",
  "valor": 1500.00,
  "cliente": "Empresa XYZ"
}
```

------------------------------------------------------------------------

## 📞 Contato

GitHub: https://github.com/renanbreier
LinkedIn: https://linkedin.com/in/renanbreier
