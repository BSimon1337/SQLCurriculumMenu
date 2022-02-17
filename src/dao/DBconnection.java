package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	private final static String URL = "jdbc:mysql://localhost:3306/teams";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Cueball12!";
	
	private static Connection connection;
	private static DBconnection instance;
	
	private DBconnection(Connection connection) {
		this.connection = connection;
		
	}
	public static Connection getConnection() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBconnection(connection);
				System.out.println("Connection successful.");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DBconnection.connection;
	}
}
