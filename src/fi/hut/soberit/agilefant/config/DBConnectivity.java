package fi.hut.soberit.agilefant.config;

import java.sql.*;

public class DBConnectivity {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/agilefant";
	/*static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/agilefant";*/
	/*static final String DB_URL = "jdbc:mysql://localhost:3306/agilefant";*/
	static final String USER = "agilefant";
	static final String PASS = "agilefant";

	public static Connection dataBaseConnect() {
		
		System.out.println("Connecting to database...XXXXXXXXXXXXXXXXXXXXXXXXXXX6666");
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connecting to database...");
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}  
		return conn;
	}	   
}

