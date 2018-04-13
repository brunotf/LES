<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="edu.model.Medico, java.text.SimpleDateFormat, java.util.LinkedHashSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Gestão de médicos</title>
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
</head>
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
<body>
	<%
		String msg = (String) session.getAttribute("MESSAGE");
		Medico medicoAtual = (Medico) session.getAttribute("MEDICOATUAL");
		session.setAttribute("MEDICOATUAL", null);
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (medicoAtual == null) {
			medicoAtual = new Medico();
		}

		String esp = medicoAtual.getEspecialidade();
	%>
	<h1>Hospital B.O.</h1>
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
	<h2>Gestão de Médicos</h2>
	<div class="container">
		<form action="./MedicoController" method="post">
			<div class="row">
				<div class="col-sm-4">
					<label>ID</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtId" id="id" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Nome</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtNome" id="nome" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>CRM</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtCRM" id="crm" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Especialidade</label>
				</div>
				<div class="col-sm-8">
					<select type="text" name="txtEspecialidade" id="especialidade">
						<option value="clinic"
							<%=esp.equals("clinic") ? "selected=\"selected\"" : ""%>>Clínico
							Geral</option>
						<option value="pedi"
							<%=esp.equals("pedi") ? "selected=\"selected\"" : ""%>>Pediatra</option>
						<option value="cardio"
							<%=esp.equals("cardio") ? "selected=\"selected\"" : ""%>>Cardiologista</option>
						<option value="neuro"
							<%=esp.equals("neuro") ? "selected=\"selected\"" : ""%>>Neurologista</option>
						<option value="orto"
							<%=esp.equals("orto") ? "selected=\"selected\"" : ""%>>Ortopedista</option>
						<option value="gine"
							<%=esp.equals("gine") ? "selected=\"selected\"" : ""%>>Ginecologista</option>
						<option value="uro"
							<%=esp.equals("uro") ? "selected=\"selected\"" : ""%>>Urologista</option>
						<option value="cirur"
							<%=esp.equals("cirur") ? "selected=\"selected\"" : ""%>>Cirurgião</option>
						<option value="nefro"
							<%=esp.equals("nefro") ? "selected=\"selected\"" : ""%>>Nefrologista</option>
						<option value="anest"
							<%=esp.equals("anest") ? "selected=\"selected\"" : ""%>>Anestesista</option>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Data de Admissão</label>
				</div>
				<div class="col-sm-8">
					<input type="Date" name="txtData" id="data" />
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Turno</label>
				</div>
				<div class="col-sm-8">
					<input type="radio" name="txtTurno" value="Manhã" id="manha" />
					Manhã <input type="radio" name="txtTurno" value="Tarde" id="tarde" />
					Tarde <input type="radio" name="txtTurno" value="Noite" id="noite" />
					Noite
				</div>
			</div>

			<div class="row">
				<div class="col-sm-8">
					<button class="btn btn-primary" type="submit" name="cmd"
						value="adicionar">Adicionar</button>
					<button class="btn btn-primary" type="submit" name="cmd"
						value="pesquisar">Pesquisar</button>
					<button class="btn btn-primary" type="submit" name="cmd"
						value="atualizar">Atualizar</button>
					<button class="btn btn-primary" type="submit" name="cmd"
						value="excluir">Excluir</button>
				</div>
			</div>

		</form>
	</div>
	<%
		LinkedHashSet<Medico> listaMedicos = (LinkedHashSet<Medico>) session.getAttribute("LISTA_MEDICOS");
		session.setAttribute("LISTA_MEDICOS", null);
		if (listaMedicos != null && listaMedicos.size() > 0) {
	%>
	<h2>Lista dos médicos</h2>
	<table class="table table-striped" id="tabela">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Nome</th>
			<th scope="col">CRM</th>
			<th scope="col">Especialidade</th>
			<th scope="col">Turno</th>
		</tr>
		<%
			for (Medico m : listaMedicos) {
		%>
		<tr>
			<th scope="row" onclick="pegarValor"><%=m.getId()%></th>
			<td onclick="pegarValor"><%=m.getNome()%></td>
			<td><%=m.getCrm()%></td>
			<td><%=m.getEspecialidade()%></td>
			<td><%=m.getTurno()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<%
		}
	%>

	<script>
		function pegarValor() {

		    var medico = document.querySelectorAll("td");

			
			var i = 15;
			
			alert(i);
		}
	</script>


</body>
</html>