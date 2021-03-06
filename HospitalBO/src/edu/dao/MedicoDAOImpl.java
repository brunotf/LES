package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import edu.model.Medico;
import edu.util.DBUtil;

public class MedicoDAOImpl implements MedicoDAO {
	private Connection con;

	@Override
	public void adicionar(Medico m) {
		con = DBUtil.getInstance().getConnection();

		String sql = "INSERT INTO medico(id, nome, crm, especialidade, dtAdmissao, turno) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setLong(1, m.getId());
			ps.setString(2, m.getNome());
			ps.setString(3, m.getCrm());
			ps.setString(4, m.getEspecialidade());
			ps.setString(5, m.getDtAdmissao());
			ps.setString(6, m.getTurno());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void atualizar(Medico m) {
		con = DBUtil.getInstance().getConnection();

		String sql = "UPDATE medico SET nome = ?, especialidade = ?, dtAdmissao = ?, turno = ?" + " WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, m.getNome());
			ps.setString(2, m.getEspecialidade());
			ps.setString(5, m.getDtAdmissao());
			ps.setString(4, m.getTurno());
			ps.setLong(5, m.getId());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void excluir(Medico m) {
		con = DBUtil.getInstance().getConnection();

		String sql = "DELETE FROM medico WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setLong(1, m.getId());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public LinkedHashSet<Medico> pesquisar(String txtNome) {
		LinkedHashSet<Medico> lista = new LinkedHashSet<>();

		con = DBUtil.getInstance().getConnection();

		String sql = "SELECT * FROM medico WHERE nome like ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, "%" + txtNome + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Medico m = new Medico();

				m.setId(rs.getLong("id"));
				m.setNome(rs.getString("nome"));
				m.setCrm(rs.getString("crm"));
				m.setEspecialidade(rs.getString("especialidade"));
				m.setDtAdmissao(rs.getString("DtAdmissao"));
				m.setTurno(rs.getString("turno"));

				lista.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return lista;
	}

	@Override
	public long proximoId() {
		con = DBUtil.getInstance().getConnection();

		String sql = "SELECT MAX(id) + 1 AS proximo_id FROM medico";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			return rs.next() ? rs.getInt("proximo_id") : 1;
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return 0;
	}
}
