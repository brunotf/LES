CREATE TABLE medico (
	id int primary key AUTO_INCREMENT, 
	nome varchar(100) NOT NULL, 
	crm varchar(20) NOT NULL, 
	especialidade VARCHAR(40) NOT NULL, 
	dtAdmissao VARCHAR(50),
	turno VARCHAR(10)
);