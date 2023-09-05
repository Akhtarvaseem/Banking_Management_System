package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.connections.DbConnection;
import com.daoInterface.UpdationProcessInterface;
import com.dto.CustomerDetails;

public class UpdationProcessDao implements UpdationProcessInterface {

	Connection con = DbConnection.getConnection();

	@Override
	public boolean updateAllInfo(CustomerDetails details) {
		String update = "update userdetails set name=?, address=?,ph_no=?,gender=? ,acc_type=?, email=?  where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);

			ps.setString(1, details.getName());
			ps.setString(2, details.getAddress());
			ps.setString(3, details.getPh_no());
			ps.setString(4, details.getGender());
			ps.setString(5,details.getAc_type());
			ps.setString(6, details.getEmail());
			ps.setInt(7, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

//			e.printStackTrace();
			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

// update only name
	@Override
	public boolean updateUserName(CustomerDetails details) {

		String update = "update userdetails set name=?  where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);

			ps.setString(1, details.getName());

			ps.setInt(2, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

//			e.printStackTrace();
			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	// update user address
	@Override
	public boolean updateUserAddress(CustomerDetails details) {

		String update = "update userdetails set  address=? where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);

			ps.setString(1, details.getAddress());
			
			ps.setInt(2, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

//			e.printStackTrace();
			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	// update user phone number 
	@Override
	public boolean updateUserPhoneNumber(CustomerDetails details) {

		String update = "update userdetails set ph_no=? where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);

			
			ps.setString(1, details.getPh_no());
			ps.setInt(2, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	// update user gender
	@Override
	public boolean updateUserGender(CustomerDetails details) {

		String update = "update userdetails set gender=?  where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);
;
			ps.setString(1, details.getGender());
			ps.setInt(2, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

//			e.printStackTrace();
			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	// update user account type
	@Override
	public boolean updateUserAccType(CustomerDetails details) {

		String update = "update userdetails set acc_type=? where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);

		
			ps.setString(1, details.getAc_type());
		

			ps.setInt(2, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

//			e.printStackTrace();
			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	// update User email
	@Override
	public boolean updateUserEmail(CustomerDetails details) {

		String update = "update userdetails set  email=? where id=?";
		int flag = 0;
		try {
			PreparedStatement ps = con.prepareStatement(update);

			
			ps.setString(1, details.getEmail());

			ps.setInt(2, details.getC_id());

			flag = ps.executeUpdate();

		} catch (SQLException e) {

//			e.printStackTrace();
			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

}
