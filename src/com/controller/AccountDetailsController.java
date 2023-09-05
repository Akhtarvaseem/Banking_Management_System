package com.controller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.dao.UserDtlsDao;
import com.dto.AccountOpration;
import com.dto.CustomerDetails;
import com.home.Home;
import com.service.AccountDtlsService;
import com.service.BlanceOprationService;
import com.service.UserDtlsService;

public class AccountDetailsController {

	// only permitted to admin login
	public static void accOpration() {
		Scanner sc = new Scanner(System.in);

		AccountDtlsService service = new AccountDtlsService();

		while (true) {
			System.out.println("Enter your choice ");
			System.out.println(
					"1.update customer account \n2.delete customer account \n3.show all user information \n4.get by id \n5.logout");

			int ch = sc.nextInt();

			switch (ch) {

			case 1: {

				UpdationProcessControl up = new UpdationProcessControl();
				up.updateDetail();

				break;
			}
			case 2: {
				UserDtlsService service1 = new UserDtlsService();
				CustomerDetails detail = new CustomerDetails();

				System.out.println("Enter id for delete");
				detail.setC_id(sc.nextInt());

				boolean f = service1.deleteUserDtl(detail);
				if (f == true) {
					System.out.println("deleted");
				} else {
					System.err.println("error");
				}

				break;
			}

			case 3: {
				List<AccountOpration> ls = service.getAllDtls();

				System.out.format("%17s %14s %17s %15s %25s %13s %15s %17s %14s %20s %20s", "Account id",
						"Account number", "Ifsc code", "Account holder name", "Phone number is ", "Address ", "Gender",
						"Account type", "Date", "time", "User_email");
				System.out.println();
				for (AccountOpration op : ls) {
					System.out.format("%17s %14s %17s %17s %25s %17s %14s %14s %17s %22s %20s", op.getAc_id(),
							op.getAc_number(), op.getIfsc_code(), op.getCustDetails().getName(),
							op.getCustDetails().getPh_no(), op.getCustDetails().getAddress(),
							op.getCustDetails().getGender(), op.getCustDetails().getAc_type(),
							op.getCustDetails().getDate(), op.getCustDetails().getTime(),
							op.getCustDetails().getEmail());
					System.out.println();

				}
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;
			}
			case 4: {

				System.out.println("Know customar details by id ");
				BlanceOprationService ser = new BlanceOprationService();
				ser.getDetailsById();

				break;
			}
			case 5: {
//				Home.getHome();
				System.out.println("You are Logout. Thank you !");
				System.exit(0);
				

				break;
			}

			default:
				break;
			}

		}

	}
}
