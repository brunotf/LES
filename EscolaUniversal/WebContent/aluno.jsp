<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.model.Aluno, java.util.HashSet "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E.E. Universal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<STYLE>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</STYLE>
</head>

<body>
	<%
		String msg = (String) session.getAttribute("MESSAGE");
		Aluno alunoAtual = (Aluno) session.getAttribute("ALUNOS");
		session.setAttribute("ALUNOS", null);
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (alunoAtual == null) {
			alunoAtual = new Aluno();
		}
	%>
	<h1>Gest�o de alunos</h1>
	<hr />
	<%
		if (msg != null) {
	%>
	<div class="alert alert-success" role="alert">
		<%=msg%>
	</div>
	<%
		session.setAttribute("MESSAGE", null);
		}
	%>
	<hr />
	<div class="container">
		<form action="./AlunoController" method="post">
			<div class="row">
				<div class="col-sm-4">
					<label>ID</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtId" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Nome</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtNome" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>RA</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtRa" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Idade</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtIdade" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Sexo</label>
				</div>
				<div class="col-sm-8">
					<input type="radio" name="txtSexo" value="Feminino" /> Feminino <input
						type="radio" name="txtSexo" value="Masculino" /> Masculino
				</div>
			</div>

			<div class="row">
				<div class="col-sm-8">
					<button class="btn btn-primary" type="submit" name="cmd"
						value="adicionar">Adicionar</button>
					<button class="btn btn-primary" type="submit" name="cmd"
						value="pesquisar">Pesquisar</button>
				</div>
			</div>

		</form>
	</div>

	<%
		HashSet<Aluno> listaAlunos = (HashSet<Aluno>) session.getAttribute("LISTA_ALUNOS");
		session.setAttribute("LISTA_ALUNOS", null);
		if (listaAlunos != null && listaAlunos.size() > 0) {
	%>
	<h2>Lista dos alunos</h2>
	<table>
		<tr>
			<th>ID</th>
			<th>RA</th>
			<th>Nome</th>
			<th>Idade</th>
			<th>Sexo</th>
		</tr>
		<%
			for (Aluno a : listaAlunos) {
		%>
		<tr>
			<td><%=a.getId()%></td>
			<td><%=a.getRa()%></td>
			<td><%=a.getNome()%></td>
			<td><%=a.getIdade()%></td>
			<td><%=a.getSexo()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<%
		}
	%>

</body>
</html>