# sboot-account-transactions

Aplicação permite a criação de novas contas, consultá-las e realizar transações.

## Executar aplicação

Para executar a aplicação é necessário ter instalado em sua máquina o Docker e o Docker Compose
  * Abra o terminal e vá até a pasta docker que se encontra na raíz do projeto
  * Digite o seguinte comando para criar e iniciar o banco de dados MariaDB e a aplicação backend:
> docker-compose up -d

## Utilizando os serviços

Através do link http://localhost:8080/swagger-ui.html você terá acesso as APIs e poderá utilizar os serviços. 
Abaixo segue o "curl" dos métodos que podem ser utilizados no Postman apenas copiando-o.
### Criação de conta

Método POST - http://localhost:8080/v1/accounts

> curl --location --request POST 'localhost:8080/v1/accounts' \
> --header 'Content-Type: application/json' \
> --data-raw '{
>     "document_number": "32548436549",
>     "credit_limit": 1000
> }'

Possíveis códigos de status da resposta:
  * 201 - Conta criada com sucesso
  * 412 - Dados da conta inválidos
  * 422 - Conta já cadastrada
  * 500 - Erros internos
    
### Localizar conta pelo accountId

Método GET - http://localhost:8080/v1/accounts/{accountId}

> curl --location --request GET 'localhost:8080/v1/accounts/1' \
> --header 'Content-Type: application/json'

Possíveis códigos de status da resposta:
  * 200 - Conta encontrada e a representação da conta no body da resposta
  * 204 - Conta não encontrada
  * 500 - Erros internos

### Criação de uma transação

Cria uma transação para uma conta cadastrada anteriormente.
As transações disponíveis são:
  * 1 - Compra à vista
  * 2 - Compra parcelada
  * 3 - Saque
  * 4 - Pagamento

Método POST - http://localhost:8080/v1/transactions

> curl --location --request POST 'localhost:8080/v1/transactions' \
> --header 'Content-Type: application/json' \
> --data-raw '{
>     "account_id": 1,
>     "aperation_type_id": 1,
>     "amount": 101.12
> }'

Possíveis códigos de status da resposta:
  * 201 - Transação criada com sucesso
  * 412 - Conta ou Operação não encontradas
  * 500 - Erros internos