package com.controller;


import java.util.List;
import java.util.Scanner;

import com.dao.BalanceOprationDao;
import com.dao.LoginDao;
import com.dto.AccountOpration;
import com.dto.transaction;
import com.exception.BalanceException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidUserId;
import com.home.Home;
import com.service.AccountDtlsService;
import com.service.BlanceOprationService;

public class BalanceOprationController {

	
	
public	static int tmp_pin=0;
public	static String temp_acc_num=null;
	
	// Permitted  to user login
	public static void balanceOpration() {
		
		
		Scanner sc=new Scanner(System.in);
		AccountOpration opr=new AccountOpration();
	    BlanceOprationService service=new BlanceOprationService();
	    BalanceOprationDao dao=new BalanceOprationDao();
	    
		List<AccountOpration> lss=dao.getBal();
	    
		// Assign in temp pin
		
					for (AccountOpration acc : lss) {
						
						if(acc.getAc_id()==LoginDao.id ) {
							
							tmp_pin=acc.getPin_num();
							temp_acc_num=acc.getAc_number();
						}
							
					}
		
	    while(true) {
		System.out.println("\nEnter your choice :");
		
		System.out.println("---------------------------------------");
		System.out.println("0.set pin \n1.deposit in account \n2.add balance \n3.withdrow balance \n4.show my account details\n5.show previous transaction\n6.logout");
		System.out.println("----------------------------------------");
		int ch= sc.nextInt();
		switch (ch) {
		case 0:{
			
			boolean f=false;
			int pin_num=0;
			System.out.println("Please enter your choice to create / update pin .");
			System.out.println("1.new User \n2.change pin number ");
			int c=sc.nextInt();
			
			switch (c) {
			case 1:
			{
				System.out.println("Enter your account  number :");
				String ac_num =sc.next();
				
				System.out.println("Enter here Pin Number :");
				pin_num=sc.nextInt();
				
			     
			    	 
					if(tmp_pin==0) {
						
						opr.setAc_number(ac_num);
						opr.setPin_num(pin_num);
						 f=dao.setPin(opr);
					}
			    	 
				
				
				break;
			}
			case 2:
			{
				System.out.println("Enter your account  number :");
				String ac_num =sc.next();
				
				System.out.println("Enter here Pin Number :");
			    pin_num=sc.nextInt();
				
			    if(tmp_pin!=0) {
			    	opr.setAc_number(ac_num);
			    	opr.setPin_num(pin_num);
			    	f=dao.setPin(opr);
			    }
				break;
			}

			default:
				break;
			}
			
			
			if(f==true) {
				System.out.println(" pin created successfully ! ");
			}
			else {
				System.out.println("Sorry ! your pin already created .");
			}
			
			// Assign in temp pin
			

				tmp_pin=pin_num;

			break;
		}
		case 1:
		{
			// deposit
			
			
			
//			System.out.println("enter your account number to credit. :");
//			String acc_n=sc.next();
			
			String acc_n=temp_acc_num;
			
			
			System.out.println("Enter ammount :");
			long am=sc.nextLong();
			
			
			
			opr.setAc_number(acc_n);
			opr.setBalance(am);
			
			boolean f=dao.p2pDeposit(opr);
			
			if(f==true) {
				System.out.println("balance add successfully ");
			}
			else {
				System.out.println("please enetr correct account   ");
			}
			
			break;
		}
		case 2:
		{
		//  add balance 
//			System.out.println("Enter your account pin number:");
//			int id =sc.nextInt();
			int id =tmp_pin;
			
			System.out.println("Enter ammount :");
			long am=sc.nextLong();
			
			 opr.setBalance(am);
			
			boolean f=service.depositBalance(opr, id);
			
			try {
				if(f==true)
				{
					System.out.println("balance deposit successfully ");
				}
				else {
					throw new BalanceException("Somethingwent wrong Please ! try again. ");
				}
				
			} catch (Exception e) {
				
				System.out.println(e);
			}
			
			break;
		}
		case 3:
		{
			
		// Withdraw

//			System.out.println("Enter your account pin number :");
//			int id =sc.nextInt();
			
			int id =tmp_pin;
			
			System.out.println("Enter amount :");
			long am=sc.nextLong();
			
			 opr.setBalance(am);
			
			boolean f= service.withdrowBalance(opr, id);
			
			try {
				if(f==true) {
					System.out.println("balance withdrow successfully ");
				}
				else {
					throw new InsufficientBalanceException(" Insfficient Balance ");
				}
				
			} catch (Exception e) {
				
				System.out.println(e);
			}
			break;
		}
		case 4:
		{

			// get user details using id 
			boolean f=BlanceOprationService.getDetailsById();
			if(f==false) {
				System.out.println("please enter correct pin :");
			}
            
			
			break;
		}
		case 5:
		{
			
			// transaction 
			
//			System.out.println(" Enter pin number to know your previous transaction ");
//			int id=sc.nextInt();
			int id =tmp_pin;
			
//			AccountDtlsService sr = new AccountDtlsService();

			List<transaction> ls=service.getTrans(id);
			

			try {
				if(!ls.isEmpty()) {
		
					System.out.format("%7s %14s %14s %14s %25s ", "trans_id", "pre_balance","type_bal ","date ","time");
					System.out.println();
					
					for (transaction tr : ls) {  					
						
						System.out.format("%7s %14s %14s %17s %25s ",tr.getId(),tr.getBalance(),tr.getMsg(),tr.getDate(),tr.getTime() );
						System.out.println();
					}
					System.out.println();
				}
				else {
					
					throw new InvalidUserId("Id not persent ! ");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
			
			
			break;
		}
		case 6:
		{
			// logout

			System.out.println("You want to logout: Enter Y otherwise N.");
			String s=sc.next();
			if(s.equals("Y"))
			{
				System.out.println("Thank you for visiting !\n");
				
//				System.exit(0);
				Home.getHome();
			}
			
			
			break;
		}

		default:
			break;
		}
	   }
	}
}
