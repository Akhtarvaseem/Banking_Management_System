package com.other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.connections.DbConnection;
import com.dao.UserDtlsDao;
import com.dto.AccountOpration;
import com.dto.CustomerDetails;
import com.service.AccountDtlsService;
import com.service.UserDtlsService;

public class FetchingId {

	
	public static void main(String[] args) {
		getAllDetail();
		
	}
	
	public static void getId() {
		Connection con=DbConnection.getConnection();
		String selectid="select * from accountdetails";
		int id=1;
		try {
			PreparedStatement pr=con.prepareStatement(selectid);
			
			ResultSet rs=pr.executeQuery();
			
			if(id==rs.getInt(1)) {
				System.out.println(rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void getAllDetail() {
				
		AccountDtlsService  sr=new AccountDtlsService();
		List<AccountOpration> ls=sr.getAllDtls();
		
		for (AccountOpration op : ls) {
			
			System.out.println("Account id :"+op.getAc_id());
			System.out.println("Account number :"+op.getAc_number());
			System.out.println("Ifsc code :"+op.getIfsc_code());
			
			
			System.out.println("---------------------------------------");
		}
		
	}
}
