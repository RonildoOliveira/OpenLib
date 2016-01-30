package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static String urlPostgree = "jdbc:postgresql://localhost:5432/openlib";
	private static String superUser = "postgres";
	private static String password = "postgres";
	
	public static Connection getConnection(){
		try {
			System.out.println("OPEN");
			return DriverManager.getConnection(urlPostgree,superUser,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}