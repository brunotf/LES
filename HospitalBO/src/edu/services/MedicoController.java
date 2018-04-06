package edu.services;

import java.io.IOException;
import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.HashSet;

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
		// String txtId = request.getParameter("txtId");
		// String txtNome = request.getParameter("txtNome");
		// String txtCRM = request.getParameter("txtCRM");
		// String txtData = request.getParameter("txtData");
		// String txtEspecialidade = request.getParameter("txtEspecialidade");
		// String txtTurno = request.getParameter("txtTurno");
		// System.out.println("Apertado botao : " + txtCmd);

		String txtCmd = request.getParameter("cmd");

		MedicoDAO mDao = new MedicoDAOImpl();

		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		HashSet<Medico> lista = (HashSet<Medico>) getServletContext().getAttribute("LISTA");

		if (lista == null) {
			lista = new HashSet<>();
			getServletContext().setAttribute("LISTA", lista);
		}

		try {
			long proxId = mDao.proximoId();
			getServletContext().setAttribute("PROXID", proxId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String message = null;
		if ("adicionar".equals(txtCmd)) {

			Medico m = new Medico();
			m.setId(Long.parseLong(request.getParameter("txtId")));
			m.setNome(request.getParameter("txtNome"));
			m.setCrm(request.getParameter("txtCRM"));
			m.setEspecialidade(request.getParameter("txtEspecialidade"));
			m.setTurno(request.getParameter("txtTurno"));
			m.setDtAdmissao(request.getParameter("txtData"));
			try {
				mDao.adicionar(m);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			message = String.format("Foi cadastrado o médico %s\n", m.toString());
		} else if ("pesquisar".equals(txtCmd)) {

			try {
				lista = mDao.consultar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (Medico medico : lista) {
				if (medico.getNome().contains(request.getParameter("txtNome"))) {

					message = String.format("Foi localizado o médico %s\n", medico.toString());
					request.getSession().setAttribute("MEDICOATUAL", medico);

				}
			}
		} else if ("excluir".equals(txtCmd)) {
			Medico m = new Medico();

			m.setId(Long.parseLong(request.getParameter("txtId")));
			m.setNome(request.getParameter("txtNome"));
			m.setCrm(request.getParameter("txtCRM"));

			try {
				mDao.excluir(m);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			message = String.format("Foi excluído o médico %s\n", m.toString());
		} else if ("atualizar".equals(txtCmd)) {

			Medico m = new Medico();

			m.setId(Long.parseLong(request.getParameter("txtId")));
			m.setNome(request.getParameter("txtNome"));
			m.setEspecialidade(request.getParameter("txtEspecialidade"));
			m.setTurno(request.getParameter("txtTurno"));
			m.setDtAdmissao(request.getParameter("txtData"));
			try {
				mDao.atualizar(m);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.getSession().setAttribute("MESSAGE", message);

		System.out.print(message);
		System.out.printf("Existem %d elementos na lista\n", lista.size());
		response.sendRedirect("./medicos.jsp");

	}

}
