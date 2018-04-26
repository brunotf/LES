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
	public void adicionar(Jogo j) {
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "INSERT INTO jogo(id, titulo, dificuldade) VALUES(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, j.getId());
			ps.setString(2, j.getTitulo());
			ps.setString(3, j.getDificuldade());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public LinkedList<Jogo> pesquisar(String titulo) {
		LinkedList<Jogo> lista = new LinkedList<>();
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "SELECT * FROM jogo WHERE titulo LIKE ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + titulo + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Jogo j = new Jogo();
				j.setId(rs.getInt("id"));
				j.setTitulo(rs.getString("titulo"));
				j.setDificuldade(rs.getString("dificuldade"));

				lista.add(j);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return lista;
	}
	
}
