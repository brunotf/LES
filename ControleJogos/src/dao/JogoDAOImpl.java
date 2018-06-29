package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import modelo.Jogo;
import util.DBUtil;

public class JogoDAOImpl implements JogoDAO {
	private Connection con;

	@Override
	public void adicionar(Jogo jogo) {
		con = DBUtil.getInstance().getConnection();

		String sql = "INSERT INTO jogo(id, titulo, dificuldade) VALUES(?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, jogo.getId());
			ps.setString(2, jogo.getTitulo());
			ps.setString(3, jogo.getDificuldade());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public LinkedList<Jogo> pesquisar(String titulo) {
		LinkedList<Jogo> lista = new LinkedList<>();

		con = DBUtil.getInstance().getConnection();

		String sql = "SELECT * FROM jogo WHERE titulo LIKE ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + titulo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Jogo jogo = new Jogo();
				jogo.setId(rs.getInt("id"));
				jogo.setTitulo(rs.getString("titulo"));
				jogo.setDificuldade(rs.getString("dificuldade"));

				lista.add(jogo);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return lista;
	}

}
