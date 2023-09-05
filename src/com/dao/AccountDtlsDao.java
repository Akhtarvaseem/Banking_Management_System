package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connections.DbConnection;
import com.daoInterface.AccountDtlsDaoInterface;
import com.dto.AccountOpration;
import com.dto.CustomerDetails;
import com.service.AccountDtlsService;

public class AccountDtlsDao implements AccountDtlsDaoInterface{

	Connection con=DbConnection.getConnection();
	int flag=0;
	
	@Override
	public boolean addAccountDtls(AccountOpration opration) {
		String insert="insert into accountdetails(ac_id,ac_number,ifsc_code) values(?,?,?)";
		try {
			PreparedStatement pr=con.prepareStatement(insert);
			pr.setInt(1, opration.getAc_id());
			pr.setString(2, opration.getAc_number());
			pr.setString(3, opration.getIfsc_code());
			
		    flag =pr.executeUpdate();
			
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
	public List<AccountOpration> getAllDtls() {
		
		List<AccountOpration> ls=new ArrayList<AccountOpration>();
		
		
		String select="select * from accountdetails";
		try {
			PreparedStatement pr=con.prepareStatement(select);
			ResultSet rs=pr.executeQuery();
			
			UserDtlsDao dtl=new UserDtlsDao();
			
			List<CustomerDetails> lst=dtl.getAllUserDtl();
			
			
			while(rs.next()) 
			{
				AccountOpration opration=new AccountOpration();	
				
				opration.setAc_id(rs.getInt(1));
				opration.setAc_number(rs.getString(2));
				opration.setIfsc_code(rs.getString(3));
				
				for (CustomerDetails Details : lst) {
					
					if(Details.getC_id()==opration.getAc_id()) {
						opration.setCustDetails(Details);
					}
				}
				ls.add(opration);
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return ls;
	}
	
	
	//set account number and ifsc code 
	public static void setAccount() {
		AccountDtlsService service = new AccountDtlsService();
		 
		 UserDtlsDao userDao=new UserDtlsDao();
		 AccountOpration details=new AccountOpration();
		 
		 List<CustomerDetails> ls1=userDao.getAllUserDtl();
		
		String ifsc="PNCB00TH";
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 10_000_000_000L;
		String strNum=Long.toString(number);
		
		
		details.setAc_number(strNum);
		details.setIfsc_code(ifsc);
		
		int temp=123;
		for (CustomerDetails customer : ls1) {
			
			details.setAc_id(customer.getC_id());
			
			details.setCustDetails(customer);
			
		}
		
		service.addAccountDtls(details);
	}
	
	
	
	// show user id and account number at time of registered
	
	@Override
	public void getAcId(String num) {
		
		List<AccountOpration> list=getAllDtls();

		System.out.println("___________________________________________________");
		System.out.println("  Please collect your id and account number ! ");
		System.out.println("___________________________________________________");
		for (AccountOpration ac : list) 
			
		{
			
			if((ac.getCustDetails().getPh_no()).equals(num)) {
				
				
				System.out.println("Your account id is :"+ac.getAc_id());
				System.out.println("Your account number is :"+ac.getAc_number());
				
			}
			
			
		}
		System.out.println();
		
		
		
	}

}
