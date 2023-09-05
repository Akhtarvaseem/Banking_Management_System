package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connections.DbConnection;
import com.daoInterface.LoginInterface;

public class LoginDao implements LoginInterface{
     
	 String user_admin="admin";
	 String pass_admin="admin";
	 
	public static int id=0;
	 
	Connection con=DbConnection.getConnection();
	
	@Override
	public boolean userLogin(String userName, String CPassword) {
		// email= userName
		String select="select * from userdetails where email=?";
		boolean f=false;
		try {
			PreparedStatement pre=con.prepareStatement(select);
			pre.setString(1, userName);
			
			ResultSet rs=pre.executeQuery();
			if(rs.next()) {

				String pas=rs.getString(7);
				id=rs.getInt(1);
				if(pas.equals(CPassword)) {
					f=true;
				}
			}
			
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		
		return f;
		
	}

	@Override
	public boolean userChangePassword(String custName, String newPassword) {
		
		return false;
	}

	// admin validate 
	@Override
	public boolean adminLogin(String adminName, String adminPassword) {
		
		if(adminName.equalsIgnoreCase(user_admin) && adminPassword.equalsIgnoreCase(pass_admin)) {
			return true;
		}
		else {	
			return false;
		}
	}

	@Override
	public boolean adminChangePassword(String adminUserName, String adminNewPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
