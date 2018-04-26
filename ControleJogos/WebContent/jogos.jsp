<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Jogo, java.util.LinkedList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Controle de jogos</title>
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
<body onload="limparCampos()">
	<%
		String msg = (String) session.getAttribute("MESSAGE");
		Jogo jogoAtual = (Jogo) session.getAttribute("JOGOATUAL");
		session.setAttribute("JOGOATUAL", null);
		
		if (jogoAtual == null) {
			jogoAtual = new Jogo();
		}
		
		String dif = jogoAtual.getDificuldade();
	%>
	<h1>Lista de Jogos Raíz</h1>
	<hr />
	<%
		if (msg != null) {
	%>
	<div class="alert alert-sucess" role="alert">
		<%=msg%>
	</div>
	<%
		session.setAttribute("MESSAGE", null);
		}
	%>

	<hr />
	<div class="container">
		<form action="./JogosController" method="post">
			<div class="row">
				<div class="col-sm-4 text-right">
					<label>ID</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtId" id="id" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-4 text-right">
					<label>Título</label>
				</div>
				<div class="col-sm-8">
					<input type="text" name="txtTitulo" id="titulo" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-4 text-right">
					<label>Dificuldade</label>
				</div>
				<div class="col-sm-8">
					<select type="text" name="txtDificuldade" id="dificuldade">
						<option value="Nutella" >Nutella</option>
						<option value="Fácil" >Fácil</option>
						<option value="Médio" >Médio</option>
						<option value="Difícil" >Difícil</option>
						<option value="Raíz" >Raíz</option>	
					</select>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-10 text-center">
					<button class="btn btn-primary" type="submit" name="cmd" value="adicionar">Adicionar</button>
					<button class="btn btn-primary" type="submit" name="cmd" value="pesquisar">Pesquisar</button>
				</div>
			</div>
		</form>
	</div>
	
	<hr />
	
	<%
		LinkedList<Jogo> listaJogo = (LinkedList<Jogo>) session.getAttribute("LISTAJOGO");
		session.setAttribute("LISTAJOGO", null);
		if (listaJogo != null && listaJogo.size() > 0) {
	%>
	
		<table class="table table-striped" id="tabela">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Título</th>
			<th scope="col">Dificuldade</th>
		</tr>
		<% 
		
			for (Jogo j : listaJogo) { 
			
		%>
		<tr>
			<th scope="row"><%=j.getId()%></th>
			<td><%=j.getTitulo()%></td>
			<td><%=j.getDificuldade()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<%
		}
	%>
	
	<script>
		var tabela = document.getElementById('tabela');
		
		var comboBox = document.getElementById('dificuldade');
		
		for (var i = 1; i < tabela.rows.length; i++) {
			tabela.rows[i].onclick = function () {
				document.getElementById('id').value = this.cells[0].innerHTML;
				document.getElementById('titulo').value = this.cells[1].innerHTML;
				if (this.cells[2].innerHTML == "Nutella") {
					comboBox.selectedIndex = "0";
				}
				if (this.cells[2].innerHTML == "Fácil") {
					comboBox.selectedIndex = "1";
				}
				if (this.cells[2].innerHTML == "Médio") {
					comboBox.selectedIndex = "2";
				}
				if (this.cells[2].innerHTML == "Difícil") {
					comboBox.selectedIndex = "3";
				}
				if (this.cells[2].innerHTML == "Raíz") {
					comboBox.selectedIndex = "4";
				}
			};
		}
		
		function limparCampos() {
			document.getElementById('id').value = ""
			document.getElementById('titulo').value = ""
			document.getElementById('diciuldade').value = ""
		}
	</script>
</body>
</html>