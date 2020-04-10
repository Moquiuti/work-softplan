/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  moquiuti
 * Created: 3 de abr. de 2020
 */
create table pessoa
(
    id          bigint not null  constraint pessoa_pkey primary key,
    nome        varchar(255),
    email  varchar(255),
    imagem   bigint
        constraint fk_pessoa_imagem_id
            references imagem,
    cpf         varchar(11),
    nascimento         date,
    ativo           boolean

);

create table imagem
(
    id             bigint not null constraint imagem_pkey primary key,
    datacadastro   timestamp,
    titulo         varchar(100),
    tamanho        varchar(255),
    base64         varchar
);

