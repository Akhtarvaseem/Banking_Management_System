package com.service;

import java.util.List;
import java.util.Scanner;

import com.controller.BalanceOprationController;
import com.dao.AccountDtlsDao;
import com.dao.BalanceOprationDao;
import com.dao.LoginDao;
import com.dto.AccountOpration;
import com.dto.transaction;


public class BlanceOprationService {

	BalanceOprationDao dao=new BalanceOprationDao();

	public boolean addBalance(AccountOpration balance) {
		
		
		return dao.addBalance(balance);
	}


	public boolean withdrowBalance(AccountOpration balance,int id) {
		
		return dao.withdrowBalance(balance, id);
	}

	
	public boolean depositBalance(AccountOpration balance, int id) {
		
		return dao.depositBalance(balance, id);
	}
	
	
	public List<transaction> getAllTrans() {
		// TODO Auto-generated method stub
		return dao.getAllTrans();
	}



	public List<transaction> getTrans(int id) {
		
		return dao.getTrans(id);
	}
	
public static boolean getDetailsById() 
{
	
	Scanner sc=new Scanner(System.in);
	
	boolean f=false;
	
	AccountDtlsDao da= new AccountDtlsDao();
	List<AccountOpration> list=da.getAllDtls();

	BalanceOprationDao bd=new BalanceOprationDao();
	List<AccountOpration> list1=bd.getBal();
	
//	System.out.println("Enter  id :");
//	int id=sc.nextInt();
	
//	int id = LoginDao.id;
	
	int id = 0;
	id = LoginDao.id;
	
	if(id==0) {
		System.out.println("Enter  id :");
		id=sc.nextInt();
	}
	
	for (AccountOpration op : list) {
		
		if(op.getAc_id()==id) {
			
			for (AccountOpration bn : list1) {
				if(bn.getAc_id()==op.getAc_id()) {

					System.out.println("=============================================================\n");
					System.out.println("Account holder name :"+op.getCustDetails().getName());
					System.out.println("User email  :"+op.getCustDetails().getEmail());
					System.out.println("Account holder address :"+op.getCustDetails().getAddress());
					System.out.println("Gender :"+op.getCustDetails().getGender());
					System.out.println("Phone number :"+op.getCustDetails().getPh_no());
					System.out.println("Account type :"+op.getCustDetails().getAc_type());
					System.out.println("Account Number :"+op.getAc_number());
					System.out.println("Date & time "+op.getCustDetails().getDate()+" "+op.getCustDetails().getTime());
					System.out.println("Ifsc code :"+op.getIfsc_code());
					System.out.println("Balance :"+bn.getBalance());
					System.out.println("=============================================================");
				}
			}
	
			f=true;
		}	
		
	}
	return f;
	}
}
