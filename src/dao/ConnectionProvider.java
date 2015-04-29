package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionProvider {

	private static final String URL = "jdbc:postgresql://localhost:5432/totvs";
	private static final String USER = "padrao";
	private static final String PASSWORD = "password1";

	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null) { 
			connection = DriverManager.getConnection(URL, USER, PASSWORD);			
		}

		return connection;

	}

}