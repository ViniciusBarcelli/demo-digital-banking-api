create table cliente (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    sobrenome varchar(60) not null,
    email varchar(255) not null,
    nascimento date not null,
    cpf varchar(11),

    primary key (id)
);