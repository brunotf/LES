package edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBUtil {

	private final static String USERNAME = "root";
	private final static String PASSWORD = "";
	private final static String URLDB = "jdbc:mysql://localhost:3306/escola";
	private static DBUtil instancia;
	private Connection con;

	private DBUtil() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URLDB, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static DBUtil getInstance() {
		if (instancia == null) {
			instancia = new DBUtil();
		}
		return instancia;
	}

	public Connection getConnection() {
		return con;
	}

}
