package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/bank_management_system";
			String user="root";
			String pass="python@123";
			
			con=DriverManager.getConnection(url, user, pass);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return con;
	}
	
	
}
