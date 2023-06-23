/**
* Smartphone
* @author Gabriel Anastacio
*/
create database dbcelular;
show databases;
use dbcelular;
select dbcelular;

create table usuarios ( 
	iduser int primary key auto_increment,
	nome varchar(30) not null,
	login varchar(20) not null unique, 
	senha varchar(250) not null,
    perfil varchar(5) not null
);

insert into usuarios(nome,login,senha,perfil) values ('Administrador','admin',md5('admin'),'admin');

create table clientes (
	idcli int primary key auto_increment,
	nome varchar(30) not null, /*nome com 30 caracter e obrigatorio*/
	cpf varchar(11) not null unique,/*unique = nao aceita valores duplicados*/
    rg varchar(9) not null unique,
	endereco varchar(200) not null,
    numeroendereco varchar(5) not null,
    bairro varchar(50) not null,
	cidade varchar(50) not null,
    cep varchar(8) not null,
    uf varchar(2) not null,
    complemento varchar(50),
    telefone1 varchar(11) not null,
    telefone2 varchar(11),
    email varchar(100) not null
);

create table servicos(
	os int primary key auto_increment,
    idcli int not null,
    defeito varchar(200) not null,
    diagnostico varchar(200),
    statusOS varchar(40) not null,
    valor decimal(10,2),
    dataOS timestamp default current_timestamp,
    dataOSsaida date,
    marcaOS varchar(15) not null,
    modeloOS varchar(20) not null,
    imei varchar(15) not null,
    idtec int,
    usuario varchar (30) not null,
    foreign key(idcli) references clientes(idcli),
    foreign key(idtec) references tecnicos(idtec)
);

create table fornecedores (
	idforn int primary key auto_increment,
	razao varchar(50) not null, /*nome com 30 caracter e obrigatorio*/
	cnpj varchar(18) not null unique,/*unique = nao aceita valores duplicados*/
	endereco varchar(100) not null,
    numeroendereco varchar(5) not null,
    bairro varchar(50) not null,
	cidade varchar(50) not null,
    cep varchar(9) not null,
    uf varchar(2) not null,
    complemento varchar(50),
    telefone1 varchar(11) not null,
    telefone2 varchar(11) not null,
    email varchar(100) not null,
    site varchar(100)
);

create table tecnicos ( /* Tabela com campos*/
	idtec int primary key auto_increment,
	nome varchar(30) not null, /*nome com 30 caracter e obrigatorio*/
	fone varchar (15) unique not null
);

create table estoques (
	idprodut int primary key auto_increment,
    idforn int,
    produto varchar(70) not null,
	codebarra varchar(13) not null unique, /*nome com 30 caracter e obrigatorio*/
	unimedida varchar(10),
    localarm varchar(50),
    estoque int not null,
    estoquemin int not null,
	descricao varchar(200),/*unique = nao aceita valores duplicados*/
    validade date not null,
    valor decimal(10,2) not null,
	dataEntrada timestamp default current_timestamp,
    foreign key(idforn) references fornecedores(idforn)
);
