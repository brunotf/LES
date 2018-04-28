package edu.services;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.AlunoDAO;
import edu.dao.AlunoDAOImpl;
import edu.model.Aluno;

@WebServlet("/AlunoController")
public class AlunoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlunoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter()
				.append("<html><body>Você deve acessar a página <a href=\"./aluno.jsp\">aluno.jsp</a></body></html>");
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String txtId = request.getParameter("txtId");
		
		String txtRa = request.getParameter("txtRa");
		
		String txtNome = request.getParameter("txtNome");
		
		String txtIdade = request.getParameter("txtIdade");
		
		String txtSexo = request.getParameter("txtSexo");

		String txtCmd = request.getParameter("cmd");

		AlunoDAO aDao = new AlunoDAOImpl();

		String message = null;
		LinkedList<Aluno> listaAlunos = (LinkedList<Aluno>) getServletContext().getAttribute("LISTA_ALUNOS");

		if (txtCmd.contains("adicionar")) {
			Aluno a = new Aluno();

			a.setId(Integer.parseInt(txtId));
			a.setRa(txtRa);
			a.setNome(txtNome);
			a.setIdade(Integer.parseInt(txtIdade));
			a.setSexo(txtSexo);

			aDao.adicionar(a);
			
			message = String.format("%s foi cadastrado com sucesso.\n", a.toString());
		} else if (txtCmd.contains("pesquisar")) {
			listaAlunos = aDao.pesquisar();
			
			request.getSession().setAttribute("LISTA_ALUNOS", listaAlunos);
		} else if (txtCmd.contains("atualizar")) {
			Aluno a = new Aluno();

			a.setId(Integer.parseInt(txtId));
			a.setRa(txtRa);
			a.setNome(txtNome);
			a.setIdade(Integer.parseInt(txtIdade));
			a.setSexo(txtSexo);

			aDao.atualizar(a);

			message = String.format("%s foi atualizado com sucesso.\n", a.toString());
		} else if (txtCmd.contains(txtCmd)) {
			Aluno a = new Aluno();

			a.setId(Integer.parseInt(txtId));
			a.setNome(txtNome);
			a.setRa(txtRa);

			aDao.excluir(a);

			message = String.format("%s foi excluído com sucesso.\n", a.toString());
		}

		request.getSession().setAttribute("MESSAGE", message);

		response.sendRedirect("./aluno.jsp");

	}
}
