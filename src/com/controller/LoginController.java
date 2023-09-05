package com.controller;

import java.util.Scanner;

import com.dao.LoginDao;
import com.exception.InvalidUserNameException;

public class LoginController {

	public void getLogin() {
		
		Scanner sc=new Scanner(System.in);
		LoginDao log=new LoginDao();
		boolean flag=false;
		
		System.out.println("Enter user email ");
		String user_email=sc.next();
		
		System.out.println("Enter password");
		String pass=sc.next();
		
		flag= log.adminLogin(user_email, pass);
		
		
// validation check for login 
		
		try {	
			if(flag==true) {
				AccountDetailsController.accOpration();
			}
			
			
			flag=log.userLogin(user_email, pass);
			if(flag==true) {
				BalanceOprationController.balanceOpration();
			}
			else {
				System.out.println();
			    throw new InvalidUserNameException("Invalid userName or password ! Please try again.");
			}
		} catch (InvalidUserNameException e) {
			
			System.out.println(e);
			System.out.println(" ");
		}
	}
}
