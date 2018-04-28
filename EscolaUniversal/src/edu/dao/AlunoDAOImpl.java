package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import edu.model.Aluno;
import edu.util.DBUtil;

public class AlunoDAOImpl implements AlunoDAO {
	private Connection CON;

	@Override
	public void adicionar(Aluno a) {
		CON = DBUtil.getInstance().getConnection();

		String sql = "INSERT INTO aluno(id, ra, nome, idade, sexo) VALUES(?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = CON.prepareStatement(sql);

			ps.setInt(1, a.getId());
			ps.setString(2, a.getRa());
			ps.setString(3, a.getNome());
			ps.setInt(4, a.getIdade());
			ps.setNString(5, a.getSexo());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public LinkedList<Aluno> pesquisar() {
		LinkedList<Aluno> lista = new LinkedList<>();

		CON = DBUtil.getInstance().getConnection();

		String sql = "SELECT * FROM aluno";

		try {
			PreparedStatement ps = CON.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Aluno a = new Aluno();

				a.setId(rs.getInt("id"));
				a.setRa(rs.getString("ra"));
				a.setNome(rs.getString("nome"));
				a.setIdade(rs.getInt("idade"));
				a.setSexo(rs.getString("sexo"));

				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return lista;
	}

	@Override
	public void atualizar(Aluno a) {
		CON = DBUtil.getInstance().getConnection();

		String sql = "UPDATE aluno SET nome = ?, ra = ?, idade = ?, sexo = ? WHERE id = ?";

		try {
			PreparedStatement ps = CON.prepareStatement(sql);

			ps.setString(1, a.getNome());
			ps.setString(2, a.getRa());
			ps.setInt(3, a.getIdade());
			ps.setString(4, a.getSexo());
			ps.setInt(5, a.getId());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}

	}

	@Override
	public void excluir(Aluno a) {
		CON = DBUtil.getInstance().getConnection();

		String sql = "DELETE FROM aluno WHERE id = ?";

		try {
			PreparedStatement ps = CON.prepareStatement(sql);

			ps.setInt(1, a.getId());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}
}
