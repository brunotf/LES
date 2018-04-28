package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.util.DBUtil;

public class ProxId {
	public int proximoId() throws SQLException {
		Connection con = DBUtil.getInstance().getConnection();
		String sql = "SELECT MAX(id) + 1 AS proximo_id FROM aluno";
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