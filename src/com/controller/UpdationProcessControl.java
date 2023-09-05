package com.controller;

import java.sql.Connection;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.connections.DbConnection;
import com.dao.UpdationProcessDao;
import com.dto.CustomerDetails;
import com.exception.InvalidUserId;

public class UpdationProcessControl {

Connection con=DbConnection.getConnection();
	
	
	public void updateDetail() {
		
		Pattern pattern = Pattern.compile("^\\d{10}$");
		Pattern patern2=Pattern.compile("^(.+)@(.+)$");
		boolean b=true;
		boolean f;
		UpdationProcessDao udao= new UpdationProcessDao();
		CustomerDetails details = new CustomerDetails();
		Scanner sc=new Scanner(System.in);
		
		System.out.println(" What do you want to update please select ?");
		System.out.println();
		System.out.println("1.update name \n2.address \n3.email \n4.phone number \n5.gender \n6.account type \n7.update all info");
		
		int ch=sc.nextInt();
		
		switch (ch) {
		case 1:
		{
		// update name 
			
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
			 
			
			System.out.println("Enter customer name :");
			details.setName(sc.next());
		
			 f=	udao.updateUserName(details);
			 
			 try {
				 if(f==true) {
					 System.out.println("Update successfully ");
				 }
				 else {
					 throw new InvalidUserId("Invalid user Pin ! Please enter correct pin. ");
				 }
			} catch (InvalidUserId e) {
				System.out.println(e);
			}
			 
		 
			break;
			
		}
		case 2:
		{
			// update address 
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
			
			System.out.println("Enter customer address :");
			details.setAddress(sc.next());
			
			
			
		 f=	udao.updateUserAddress(details);
		 
		 try {
			 if(f==true) {
				 System.out.println("Update successfully ");
			 }
			 else {
				 throw new InvalidUserId("Invalid user id ! Please enter correct id. ");
			 }
		} catch (InvalidUserId e) {
			System.out.println(e);
		}
			
			break;
		}
		case 3:
		{
			// update email 
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
			
	
			// validate email 
			while(b) {
			System.out.println("Enter your email :");
			String em=sc.next();
			if(patern2.matcher(em).find()) {
				String email=em;
				details.setEmail(email);
				b=false;
			}
			else {
				System.out.println("Please enter valid email ! i.e. john@.com");
			}
			}
			
			
		 f=	udao.updateUserEmail(details);
		 
		 try {
			 if(f==true) {
				 System.out.println("Update successfully ");
			 }
			 else {
				 throw new InvalidUserId("Invalid user Pin ! Please enter correct pin. ");
			 }
		} catch (InvalidUserId e) {
			System.out.println(e);
		}
		 
			break;
		}
		case 4:
		{
			// update phone number 
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
			
		
			// add pattern phone number 
			while(b) {
				System.out.println("Enter customer phone number :");
				String ph=sc.next();
				
				if(pattern.matcher(ph).find()) {
					String ph_num=ph;
					details.setPh_no(ph_num);
					b=false;
				}else {
					
					System.out.println("Please enter valid phone number !");
				
				}
			}
			
			
			
			
		 f=	udao.updateUserPhoneNumber(details);
		 
		 try {
			 if(f==true) {
				 System.out.println("Update successfully ");
			 }
			 else {
				 throw new InvalidUserId("Invalid user Pin ! Please enter correct pin. ");
			 }
		} catch (InvalidUserId e) {
			System.out.println(e);
		}
			
			break;
		}
		case 5:
		{
			
			// update gender 
			
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
			
			System.out.println("Enter customer gender :");
			details.setGender(sc.next());
		
			
			
		 f=	udao.updateUserGender(details);
		 try {
			 if(f==true) {
				 System.out.println("Update successfully ");
			 }
			 else {
				 throw new InvalidUserId("Invalid user Pin ! Please enter correct pin. ");
			 }
		} catch (InvalidUserId e) {
			System.out.println(e);
		}
		 
			break;
		}
		case 6:
		{
		// update account type 
			
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
			
			System.out.println("Enter customer account type :");
			details.setAc_type(sc.next());
			
			
		
			
		 f=	udao.updateUserAccType(details);
		 
		 try {
			 if(f==true) {
				 System.out.println("Update successfully ");
			 }
			 else {
				 throw new InvalidUserId("Invalid user id ! Please enter correct id. ");
			 }
		} catch (InvalidUserId e) {
			System.out.println(e);
		}
			break;
		}
		case 7:
		{
			// update all user details
			
			boolean n=true;
			
			System.out.println("Enter id for update");
			details.setC_id(sc.nextInt());
		
			System.out.println("Enter customer name :");
			details.setName(sc.next());
			
			System.out.println("Enter customer address :");
			details.setAddress(sc.next());
			
			// add pattern phone number 
			while(b) {
				System.out.println("Enter customer phone number :");
				String ph=sc.next();
				
				if(pattern.matcher(ph).find()) {
					String ph_num=ph;
					details.setPh_no(ph_num);
					b=false;
				}else {
					
					System.out.println("Please enter valid phone number !");
				
				}
			}
			
			
			
			System.out.println("Enter customer gender :");
			details.setGender(sc.next());
			
			System.out.println("Enter customer account type :");
			details.setAc_type(sc.next());
	
			
			// validate email 
			while(n) {
				System.out.println("Enter your email :");
				String em=sc.next();
				if(patern2.matcher(em).find()) {
					String email=em;
					details.setEmail(email);
					n=false;
				}
				else {
					System.out.println("Please enter valid email ! i.e. john@.com");
				}
				}
			
			
		 f=	udao.updateAllInfo(details);
		 
		 try {
			 if(f==true) {
				 System.out.println("Update successfully ");
			 }
			 else {
				 throw new InvalidUserId("Invalid user Pin ! Please enter correct pin. ");
			 }
		} catch (InvalidUserId e) {
			System.out.println(e);
		}
			
			break;
		}

		default:
			break;
		}
	}

}
