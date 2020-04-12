# work-softplan
Avaliação Técnica SAJADV:
Desenvolver uma aplicação de gerenciamento de pessoas. Deve ser levado em consideração o seguinte cenário: a
aplicação é um módulo ou micro serviço de uma aplicação maior, que pode ser consumida de várias formas, via
integração, interfaces web, interfaces mobile, etc. Então as regras e requisitos devem ser centralizados de uma forma 
que isso seja possível. Além disso, é preciso também levar em consideração que esse módulo ou micro serviço deve ser 
altamente escalável de maneira simples. Também, a aplicação deve está preparada para internacionalização.

## Requisitos
Pessoa
* Consultar
> * usuário pode consultar as pessoas de maneira paginada
> * usuário pode consultar pessoas com os seguintes filtros:
> > * Nome
> > * Cpf (formatado e não formatado)
> > * Data de nascimento
> > * E-mail
> > * usuário pode editar o registro a partir do resultado da consulta
> > * usuário pode remover o registro a partir do resultado da consulta
* Cadastrar, Editar
> * O usuário pode cadastrar/editar pessoa, informando os seguintes dados:
> > * Nome
> > * Cpf
> > * E-mail
> > * Foto
> > * Data de Nascimento
> * Ao cadastrar/editar pessoa, o sistema deve validar as seguintes informações:
> > * Nome obrigatório
> > * Nome deve possuir no máximo 150 caracteres
> > * Cpf obrigatório
> > * Cpf deve ser um cpf válido
> > * E-mail obrigatório
> > * E-mail deve ser um e-mail válido
> > * E-mail deve possuir no máximo 400 caracteres
> > * Cpf não pode ser duplicado, ou seja, não pode ser cadastrado dois responsáveis com o mesmo cpf
> > * Arquivo da foto não deve ter mais de 1mb de tamanho
> > * Arquivo da foto deve ter extensão de imagem válida (png, bmp, jpeg, etc)

## Pré-Requisitos

Necessário ter o [docker](https://docs.docker.com/) instalado na máquina;

### Execução

Na raiz do projeto(pasta work-softplan), abrir o prompt e digitar o seguinte:

```
docker-compose up
```

pressionar enter e aguardar o ambiente ser preparado, quando finalizado, acessar pelo link: 

```
http://localhost:4200/
```

Ao acessar o link do pgadmin: 

```
http://localhost:16543/
```

E não encontrar o server criado não se preocupe, mesmo que já tenha executado alguma ação de inserção!
Vamos lá... 
Basta clicar com o botão direito sobre **Serves** e na opção **Create** selecionar **Server...**
1. Na aba **General**: 
> * no campo **Name** insira: _postgres_ .
2. Na aba **Connection**
> * No campo **Hostname/address** insira: _postgres_
> * No campo **Username** insira: _postgres_
> *	No campo **Password** insira: _postgresql_
> * pressione **Enter** 

Pronto! já será possível identificar dentro do schema public do banco as tabelas criadas, e mesmo que já tenha inserido valores
antes desse procedimento os valores já estarão lá.

## Construído com 

* [Spring](https://start.spring.io/) - Criação da estrutura do Backend
* [Maven](https://maven.apache.org/) - Gerenciamento de Dependências
* [JAVA 14](https://www.oracle.com/java/technologies/javase-downloads.html#JDK14) - Linguagem de Codificação do Backend
* [Angular CLI: 8.3.5](https://www.npmjs.com/package/@angular/cli/v/8.3.5) - Linguagem de Codificação do Frontend
* [Postgresql](https://www.postgresql.org/download/) - Banco de Dados


## Contribuição

Solicitações de pull são bem vindas, assim como críticas e sugestões.

Atualize conforme apropriado.
