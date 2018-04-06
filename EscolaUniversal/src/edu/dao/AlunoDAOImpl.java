package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import edu.model.Aluno;
import edu.util.DBUtil;

public class AlunoDAOImpl implements AlunoDAO {

	private Connection con;

	@Override
	public void adicionar(Aluno a) throws SQLException {
		con = DBUtil.getInstance().getConnection();
		String sql = "INSERT INTO aluno(id, ra, nome, idade, sexo) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, a.getId());
		ps.setString(2, a.getRa());
		ps.setString(3, a.getNome());
		ps.setInt(4, a.getIdade());
		ps.setNString(5, a.getSexo());
		ps.execute();
		System.out.println(a + "adicionado com sucesso.");
		ps.close();	
	}

	@Override
	public TreeSet<Aluno> pesquisar() throws SQLException {
		con = DBUtil.getInstance().getConnection();
		String sql = "SELECT * FROM aluno";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		TreeSet<Aluno> lista = new TreeSet<>();

		while (rs.next()) {
			Aluno a = new Aluno();
			a.setId(rs.getInt("id"));
			a.setRa(rs.getString("ra"));
			a.setNome(rs.getString("nome"));
			a.setIdade(rs.getInt("idade"));
			a.setSexo(rs.getString("sexo"));

			lista.add(a);
		}

		return lista;
	}

}
