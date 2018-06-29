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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = request.getParameter("cmd");

		if ("adicionar".equals(cmd)) {
			adicionar(request, response);

		} else if ("pesquisar".equals(cmd)) {
			pesquisar(request, response);
		}
	}

	private void adicionar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String txtId = request.getParameter("txtId");
		String txtTitulo = request.getParameter("txtTitulo");
		String txtDificuldade = request.getParameter("txtDificuldade");

		String message = null;

		Jogo jogo = new Jogo();

		JogoDAO dao = new JogoDAOImpl();

		jogo.setId(Integer.parseInt(txtId));
		jogo.setTitulo(txtTitulo);
		jogo.setDificuldade(txtDificuldade);

		dao.adicionar(jogo);
		
		message = String.format("Foi adicionado o jogo %s\n", jogo.toString());

		request.getSession().setAttribute("MESSAGE", message);

		response.sendRedirect("./jogos.jsp");

	}

	@SuppressWarnings("unchecked")
	private void pesquisar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String txtTitulo = request.getParameter("txtTitulo");

		JogoDAO dao = new JogoDAOImpl();
		
		LinkedList<Jogo> listaJogo = new LinkedList<>();

		listaJogo = (LinkedList<Jogo>) getServletContext().getAttribute("LISTAJOGO");
	

		if (listaJogo == null) {
			listaJogo = new LinkedList<>();
		}
		
		listaJogo = dao.pesquisar(txtTitulo);

		request.getSession().setAttribute("LISTAJOGO", listaJogo);

		response.sendRedirect("./jogos.jsp");
	}
}
