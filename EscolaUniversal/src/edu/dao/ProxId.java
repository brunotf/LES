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
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}
}