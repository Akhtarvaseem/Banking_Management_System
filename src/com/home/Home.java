package com.home;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.controller.LoginController;
import com.dao.AccountDtlsDao;
import com.dto.CustomerDetails;
import com.service.UserDtlsService;

public class Home {

	public static void getHome() {
		
		Scanner sc = new Scanner(System.in);
		
		Pattern pattern = Pattern.compile("^\\d{10}$");
		
		Pattern patern2 = Pattern.compile("^(.+)@(.+)$");

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();

		String strDate = date.toString();
		String strTime = time.toString();
		boolean b = true;
		while (true) {
			System.out.println("================");
			System.out.println("1.register user \n2.login");
			System.out.println("================");
			System.out.println("Enter your choice : ");
			UserDtlsService service = new UserDtlsService();

			 System.out.println();
			int ch = sc.nextInt();

			switch (ch) {
			case 1: {
				CustomerDetails details = new CustomerDetails();
				
				// set full name 
				System.out.println("Enter your  name :");
				String s_name=sc.nextLine();
				s_name=sc.nextLine();
				details.setName(s_name);

				// set full address
				System.out.println("Enter Your full address :");
				String s_address=sc.nextLine();
//				s_address=sc.nextLine();
				
				details.setAddress(s_address);

				// adding pattern matching for phone number

				while (b) {
					System.out.println("Enter your phone number :");
					String ph = sc.next();

					if (pattern.matcher(ph).find()) {
						String ph_num = ph;
						details.setPh_no(ph_num);
						b = false;
					} else {

						System.out.println("Please enter valid phone number !");

					}
				}

				// set gender
				System.out.println("Enter  gender :");
				details.setGender(sc.next());

				
				// saving or current type account 
				System.out.println("Which type account you want to open ? please select :");
				System.out.println("1.Saving \n2.Current");
				int c=sc.nextInt();
				switch (c) {
				case 1:
				{
					details.setAc_type("Saving");
					break;
				}
				case 2:
				{
					details.setAc_type("Current");
					break;
				}
				default:
					details.setAc_type("Saving");
					break;
				}
				
			

				// adding pattern matching for email

				while (b == false) {
					System.out.println("Enter your email :");
					String em = sc.next();

					if (patern2.matcher(em).find()) {
						String email = em;
						details.setEmail(email);
						b = true;
					} else {

						System.out.println("Please enter valid email ! i.e. john@gmail.com");
					}
				}

				// set password 
				System.out.println("Enter password :");
				details.setC_pass(sc.next());

				// set time and date
				details.setDate(strDate);
				details.setTime(strTime);

				Boolean f = service.addUserDtl(details);

				if (f == true) {
					System.out.println("=========================================================");
					System.out.println("||       * RGISTRATION HAS BEEN SUCCESSFULLY DONE *     ||");
					System.out.println("=========================================================");
				} else {
					System.out.println("please try again ...");
				}

				
				    // showing  account number 
					AccountDtlsDao d = new AccountDtlsDao();
					
					
					
					// method 
					d.getAcId(details.getPh_no());
					
				
				break;
			}

			case 2:

			{
				// login
				
				System.out.println("**********************");
				System.out.println("# Welcome to login #");
				System.out.println("**********************");
				LoginController log = new LoginController();
				log.getLogin();

				break;
			}

		
			default:

				System.out.println("Please enter valid number !");
				break;
			}

		}

	}

	public static void main(String[] args) {
		getHome();
	}
}
