package edu.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.AlunoDAO;
import edu.dao.AlunoDAOImpl;
import edu.model.Aluno;

/**
 * Servlet implementation class AlunoController
 */
@WebServlet("/AlunoController")
public class AlunoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlunoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter()
				.append("<html><body>Você deve acessar a página <a href=\"./aluno.jsp\">aluno.jsp</a></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		TreeSet<Aluno> listaAlunos = (TreeSet<Aluno>)
				getServletContext().getAttribute("LISTA_ALUNOS");

		if (txtCmd.contains("adicionar")) {
			Aluno a = new Aluno();

			a.setId(Integer.parseInt(txtId));
			a.setRa(txtRa);
			a.setNome(txtNome);
			a.setIdade(Integer.parseInt(txtIdade));
			a.setSexo(txtSexo);

			try {
				aDao.adicionar(a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			message = String.format("%s foi cadastrado com sucesso.\n", a.toString());

		} else if (txtCmd.contains("pesquisar")) {
			try {
				listaAlunos = aDao.pesquisar();
				request.getSession().setAttribute("LISTA_ALUNOS", listaAlunos);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.getSession().setAttribute("MESSAGE", message);

		response.sendRedirect("./aluno.jsp");

	}

}
