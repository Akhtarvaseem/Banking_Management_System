package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connections.DbConnection;
import com.daoInterface.UserDtlsDaoInterface;
import com.dto.AccountOpration;
import com.dto.CustomerDetails;

public class UserDtlsDao implements UserDtlsDaoInterface{

	Connection con=DbConnection.getConnection();
	
	
	// its use for persist  user details in db.
	@Override
	public boolean addUserDtl(CustomerDetails details){
		
		String insert="insert into userdetails(name,address,ph_no,gender,acc_type,c_pass,date,time,email) values(?,?,?,?,?,?,?,?,?)";
		int flag = 0;
		try {
			PreparedStatement ps=con.prepareStatement(insert);
			
			ps.setString(1, details.getName());
			ps.setString(2, details.getAddress());
			ps.setString(3, details.getPh_no());
			ps.setString(4, details.getGender());
			ps.setString(5, details.getAc_type());
			ps.setString(6, details.getC_pass());
			ps.setString(7, details.getDate());
			ps.setString(8, details.getTime());
			ps.setString(9, details.getEmail());
			
		    flag=ps.executeUpdate();
		    AccountDtlsDao.setAccount();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(flag>0) {
			return true;
		}
		else {
			return false;
		}
	}


	
	// its allow only admin
	@Override
	public boolean updateUserDtl(CustomerDetails details) {
		
		String update="update userdetails set name=?, address=?,ph_no=?,gender=? where id=?";
		int flag = 0;
		try {
			PreparedStatement ps=con.prepareStatement(update);
			
			ps.setString(1, details.getName());
			ps.setString(2, details.getAddress());
			ps.setString(3, details.getPh_no());
			ps.setString(4, details.getGender());
			ps.setInt(5,details.getC_id());
			
		    flag=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(flag>0) {
			return true;
		}
		else {
			return false;
		}
	}


	
	@Override
	public boolean deleteUserDtl(CustomerDetails details) {

		String delete="delete from userdetails where id=?";
		String delete_ac="delete from accountdetails where ac_id=?";
		int flag = 0;
		int ac_id=0;
		try {
			PreparedStatement ps=con.prepareStatement(delete);
			ps.setInt(1,details.getC_id());
			
			// delete for account number and ifsc code
			ac_id=details.getC_id();
			PreparedStatement ps2=con.prepareStatement(delete_ac);
			ps2.setInt(1, ac_id);

		    flag=ps.executeUpdate();
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(flag>0) {
			return true;
		}
		else {
			return false;
		}
	}

	

	
	// show main page at a time login and register 
	@Override
	public List<CustomerDetails> getAllUserDtl() {
		
		String select="select * from userdetails";
		
		ArrayList<CustomerDetails> list=new ArrayList<CustomerDetails>();
		try {
			PreparedStatement ps=con.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				CustomerDetails details=new CustomerDetails(); 
				
				 details.setC_id(rs.getInt(1));
				 details.setName(rs.getString(2));
				 details.setAddress(rs.getString(3));
				 details.setPh_no(rs.getString(4));
				 details.setGender(rs.getString(5));
				 details.setAc_type(rs.getString(6));
				 details.setDate(rs.getString(8));
				 details.setTime(rs.getString(9));
				 details.setEmail(rs.getString(10));
				 
				 list.add(details);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}
	
}
