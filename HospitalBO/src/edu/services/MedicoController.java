package edu.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.MedicoDAO;
import edu.dao.MedicoDAOImpl;
import edu.model.Medico;

/**
 * Servlet implementation class MedicoController
 */
@WebServlet("/MedicoController")
public class MedicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MedicoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(
				"<html><body>Você deve acessar a página <a href=\"./medicos.jsp\">medicos.jsp</a></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String txtId = request.getParameter("txtId");
		String txtNome = request.getParameter("txtNome");
		String txtCRM = request.getParameter("txtCRM");
		String txtData = request.getParameter("txtData");
		String txtEspecialidade = request.getParameter("txtEspecialidade");
		String txtTurno = request.getParameter("txtTurno");

		String txtCmd = request.getParameter("cmd");

		MedicoDAO mDao = new MedicoDAOImpl();
		
		LinkedHashSet<Medico> listaMedicos;

		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		listaMedicos = (LinkedHashSet<Medico>) getServletContext().getAttribute("LISTA_MEDICOS");

		String message = null;

		if ("adicionar".equals(txtCmd)) {
			Medico m = new Medico();

			m.setId(Long.parseLong(txtId));
			m.setNome(txtNome);
			m.setCrm(txtCRM);
			m.setEspecialidade(txtEspecialidade);
			m.setTurno(txtTurno);
			m.setDtAdmissao(txtData);
			try {
				mDao.adicionar(m);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			message = String.format("Foi cadastrado o médico %s\n", m.toString());

		} else if ("pesquisar".equals(txtCmd)) {

			try {
				listaMedicos = mDao.pesquisar();
				request.getSession().setAttribute("LISTA_MEDICOS", listaMedicos);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if ("excluir".equals(txtCmd)) {
			Medico m = new Medico();

			m.setId(Long.parseLong(txtId));
			m.setNome(txtNome);
			m.setCrm(txtCRM);

			try {
				mDao.excluir(m);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			message = String.format("Foi excluído o médico %s\n", m.toString());
		} else if ("atualizar".equals(txtCmd)) {

			Medico m = new Medico();

			m.setId(Long.parseLong(txtId));
			m.setNome(txtNome);
			m.setCrm(txtCRM);
			m.setEspecialidade(txtEspecialidade);
			m.setTurno(txtTurno);
			m.setDtAdmissao(txtData);
			try {
				mDao.atualizar(m);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.getSession().setAttribute("MESSAGE", message);

		response.sendRedirect("./medicos.jsp");

	}

}
