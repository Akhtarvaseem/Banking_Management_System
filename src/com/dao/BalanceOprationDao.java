package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.connections.DbConnection;
import com.daoInterface.BalanceOprationInterface;
import com.dto.AccountOpration;
import com.dto.transaction;
import com.service.AccountDtlsService;

public class BalanceOprationDao implements BalanceOprationInterface {

	static Connection con = DbConnection.getConnection();
	Scanner sc=new Scanner(System.in);

//	List<AccountOpration> list = getBal();

	AccountDtlsService service = new AccountDtlsService();
	List<AccountOpration> ls = service.getAllDtls();

	transaction trs = new transaction();
	int p_id = 0;
	long pre_trans = 0;
    String msg=null;
    
    LocalDate date=LocalDate.now();
    String strDate=date.toString();
    
    LocalTime time=LocalTime.now();
    String strTime=time.toString();
    
    
	@Override
	public boolean addBalance(AccountOpration balance) {
		int flag = 0;
		List<AccountOpration> list = getBal();
		String update = "update accountdetails set balance=? where pin_num=?";
		try {

			PreparedStatement pr = con.prepareStatement(update);
			
			for (AccountOpration acc : list) {
				if(acc.getBalance()==0 && acc.getPin_num()==balance.getPin_num()) {
					pr.setLong(1, balance.getBalance());
					pr.setInt(2, balance.getPin_num());
					flag = pr.executeUpdate();
					
				}
				else {
					flag=0;
				}
			}

		} catch (SQLException e) {

			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {

			return false;
		}

	}

	
	@Override
	public boolean withdrowBalance(AccountOpration balance, int pin) {
		List<AccountOpration> list = getBal();
		long bal = 0;
		int c_id = 0;
		int flag = 0;
		String str="withdraw";
		String update = "update accountdetails set balance=? where pin_num=?";

		for (AccountOpration bl : list) {

			if (pin == bl.getPin_num() && bl.getBalance() >= balance.getBalance()) {
				bal = bl.getBalance() - balance.getBalance();
				c_id = bl.getPin_num();

				// transection
				trs.setId(c_id);
				trs.setBalance(balance.getBalance());
				trs.setMsg(str);

				// set transaction
				p_id = trs.getId();
				pre_trans = trs.getBalance();
				msg=trs.getMsg();
				setTrans(p_id, pre_trans,msg,strDate,strTime);

			}
		}

		try {

			PreparedStatement pr = con.prepareStatement(update);
			pr.setLong(1, bal);
			pr.setInt(2, c_id);

			flag = pr.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e);
		}

		if (flag > 0) {
			return true;
		} else {

			return false;
		}
	}

	
	
	@Override
	public boolean depositBalance(AccountOpration balance, int pin) {
		List<AccountOpration> list = getBal();
		long bal = balance.getBalance();
		int c_id = 0;
		int flag = 0;
		String str="deposit";
		
		String update = "update accountdetails set balance=? where pin_num=?";

		if (bal != 0) {

			for (AccountOpration bl : list) {

				if (pin == bl.getPin_num()) {
					bal += bl.getBalance();
					c_id = bl.getPin_num();

					// transaction set
					trs.setId(c_id);
					trs.setBalance(balance.getBalance());
					trs.setMsg(str);

					// set transaction
					p_id = trs.getId();
					pre_trans = trs.getBalance();
					msg=trs.getMsg();
					setTrans(p_id, pre_trans,msg,strDate,strTime);

				}
			}
		} else {
			System.out.println("Plese enter valid amount !");

		}

		try {

			PreparedStatement pr = con.prepareStatement(update);
			pr.setLong(1, bal);
			pr.setInt(2, c_id);
			flag = pr.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (flag > 0) {
			return true;
		} else {

			return false;
		}
	}

	
	
	// p2p transaction 
	public boolean p2pDeposit(AccountOpration balance) {
		List<AccountOpration> list = getBal();
		long w_bal = 0;
		long d_bal = 0;
		int f=0;
		String g_b="update accountdetails set balance=? where ac_number=?";
		String s_b="update accountdetails set balance=? where ac_number=?";
		
		System.out.println("enter your account number to credit :");
		String accn=sc.next();
		
		for (AccountOpration ac : list) {
			
			if((ac.getAc_number()).equals(balance.getAc_number())) {
				
				w_bal=ac.getBalance()-balance.getBalance();
				
				String str="withdraw";
				// set trnasaction
				trs.setId(ac.getPin_num());
				trs.setBalance(balance.getBalance());
				trs.setMsg(str);

				// set transaction
				p_id = trs.getId();
				pre_trans = trs.getBalance();
				msg=trs.getMsg();
				setTrans(p_id, pre_trans,msg,strDate,strTime);
				
			}
			
			// 
			if((ac.getAc_number()).equals(accn)) {
				
				d_bal=ac.getBalance()+balance.getBalance();
				
				String str="deposit";
				// set trnasaction
				trs.setId(ac.getPin_num());
				trs.setBalance(balance.getBalance());
				trs.setMsg(str);

				// set transaction
				p_id = trs.getId();
				pre_trans = trs.getBalance();
				msg=trs.getMsg();
				setTrans(p_id, pre_trans,msg,strDate,strTime);
			}
		}
		
		try {
			PreparedStatement pre=con.prepareStatement(g_b);
			PreparedStatement pre2=con.prepareStatement(s_b);
			
			if(w_bal>0 && d_bal>0) {
				
				// from account with account
				pre.setLong(1, w_bal);
				pre.setString(2, balance.getAc_number());
				
				
				// to account 
				pre2.setLong(1, d_bal);
				pre2.setString(2, accn);
				pre2.executeUpdate();
				
				f=pre.executeUpdate();
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(f>0) {
			return true;
		}
		else {
			return false;	
		}
		
	}
	
	
	// known for all details in account table 
	@Override
	public List<AccountOpration> getBal() {

		List<AccountOpration> list1 = new ArrayList<AccountOpration>();

		String select = "select * from accountdetails";

		try {
			PreparedStatement pr = con.prepareStatement(select);
			ResultSet rs = pr.executeQuery();

			while (rs.next()) {
				AccountOpration ap = new AccountOpration();
				ap.setAc_id(rs.getInt(1));
				ap.setAc_number(rs.getString(2));
				ap.setAc_type(rs.getString(3));
				ap.setBalance(rs.getLong(4));
				ap.setPin_num(rs.getInt(5));

				list1.add(ap);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list1;

	}

	
	// set previous transaction 
	public static void setTrans(int p, long trns, String msg2, String strDate2, String strTime2) {
		String insert = "insert into transaction values(?,?,?,?,?)";

		try {
			PreparedStatement pr = con.prepareStatement(insert);
			pr.setInt(1, p);
			pr.setLong(2, trns);
			pr.setString(3, msg2);
			pr.setString(4, strDate2);
			pr.setString(5, strTime2);
			pr.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

	}

	// for admin to know how many transaction are done in bank 
	@Override
	public List<transaction> getAllTrans() {
		String select = "select * from transaction ";
		List< transaction> lis=new ArrayList<transaction>();
		
		try {
			PreparedStatement pre = con.prepareStatement(select);
			
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				transaction tr=new transaction();
				tr.setId(rs.getInt(1));
				tr.setBalance(rs.getLong(2));
				lis.add(tr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lis;
	}

	
	// for user to know your previous transaction using id 
	@SuppressWarnings("unused")
	@Override
	public List<transaction> getTrans(int id) {
		String select = "select * from transaction where c_id=?";
		List< transaction> lis=new ArrayList<transaction>();
		
		boolean flag = true;
		try {
			PreparedStatement pre = con.prepareStatement(select);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				transaction tr=new transaction();
				tr.setId(rs.getInt(1));
				tr.setBalance(rs.getLong(2));
				tr.setMsg(rs.getString(3));
				tr.setDate(rs.getString(4));
				tr.setTime(rs.getString(5));
				lis.add(tr);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if(lis!=null) {
			return lis;
		}
		else {
			return null;
		}
		

	}
	
	
	// user user pin_number for transaction 
	@Override
	public boolean setPin(AccountOpration opration) {
		List<AccountOpration> list = getBal();

		int flag=0;
		String set_pin="update accountdetails set pin_num=? where ac_number=?";
		
		try {
			PreparedStatement pr=con.prepareStatement(set_pin);
			
				for (AccountOpration acc : list) {
					
					if(acc.getPin_num()==0) {
						pr.setInt(1, opration.getPin_num());	
						pr.setString(2, opration.getAc_number());
						flag=pr.executeUpdate();
					}
				}
			
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		if(flag>0) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
