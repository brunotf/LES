<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="edu.model.Medico, java.text.SimpleDateFormat, java.util.LinkedHashSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Gest�o de m�dicos</title>
<link rel="stylesheet" href="./resources/bootstrap.min.css" />
<script src="./resources/jquery-3.3.1.js"></script>
<script src="./resources/popper.min.js"></script>
<script src="./resources/bootstrap.min.js"></script>
<style>
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
</style>
</head>
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
	<h2>Gest�o de M�dicos</h2>
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
							<%=esp.equals("clinic") ? "selected=\"selected\"" : ""%>>Cl�nico
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
							<%=esp.equals("cirur") ? "selected=\"selected\"" : ""%>>Cirurgi�o</option>
						<option value="nefro"
							<%=esp.equals("nefro") ? "selected=\"selected\"" : ""%>>Nefrologista</option>
						<option value="anest"
							<%=esp.equals("anest") ? "selected=\"selected\"" : ""%>>Anestesista</option>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<label>Data de Admiss�o</label>
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
					<input type="radio" name="txtTurno" value="Manh�" id="manha" />
					Manh� <input type="radio" name="txtTurno" value="Tarde" id="tarde" />
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
	<%LinkedHashSet<Medico> listaMedicos = (LinkedHashSet<Medico>) session.getAttribute("LISTA_MEDICOS");
			session.setAttribute("LISTA_MEDICOS", null);
			if (listaMedicos != null && listaMedicos.size() > 0) {%>
	<h2>Lista dos m�dicos</h2>
	<table class="table table-striped" id="tabela">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Nome</th>
			<th scope="col">CRM</th>
			<th scope="col">Especialidade</th>
			<th scope="col">Turno</th>
		</tr>
		<%for (Medico m : listaMedicos) {%>
		<tr>
			<th scope="row"><%=m.getId()%></th>
			<td><%=m.getNome()%></td>
			<td><%=m.getCrm()%></td>
			<td><%=m.getEspecialidade()%></td>
			<td><%=m.getTurno()%></td>
		</tr>
		<%}%>

	</table>
	<%}%>

</body>
</html>