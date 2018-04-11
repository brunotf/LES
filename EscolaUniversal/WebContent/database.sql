CREATE DATABASE escola;
use escola;
CREATE TABLE aluno (
	id int primary key AUTO_INCREMENT, 
	ra varchar(20), 
	nome varchar(150), 
	idade int, 
	sexo varchar(10)
);