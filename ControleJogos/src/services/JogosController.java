package services;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JogoDAO;
import dao.JogoDAOImpl;
import modelo.Jogo;

@WebServlet("/JogosController")
public class JogosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JogosController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String txtId = request.getParameter("txtId");
		
		String txtTitulo = request.getParameter("txtTitulo");
		
		String txtDificuldade = request.getParameter("txtDificuldade");

		String cmd = request.getParameter("cmd");

		JogoDAO jDao = new JogoDAOImpl();

		LinkedList<Jogo> listaJogo;

		listaJogo = (LinkedList<Jogo>) getServletContext().getAttribute("LISTAJOGO");

		if (listaJogo == null) {
			listaJogo = new LinkedList<>();
		}

		String message = null;

		if ("adicionar".equals(cmd)) {
			Jogo j = new Jogo();
			
			j.setId(Integer.parseInt(txtId));
			j.setTitulo(txtTitulo);
			j.setDificuldade(txtDificuldade);
			
			jDao.adicionar(j);
			message = String.format("Foi adicionado o jogo %s\n", j.toString());
		} else if ("pesquisar".equals(cmd)) {
			listaJogo = jDao.pesquisar(txtTitulo);
			request.getSession().setAttribute("LISTAJOGO", listaJogo);
		}

		request.getSession().setAttribute("MESSAGE", message);

		response.sendRedirect("./jogos.jsp");
	}
}
