package com.dto;

public class AccountOpration {

	private int ac_id;
	private String ac_number;
	private String ifsc_code;
	private String ac_type;
	private long balance;
	private int pin_num;
	
	
	private CustomerDetails custDetails;
	
	public AccountOpration() {
		
	}
	
	public AccountOpration(int ac_id, String ac_number, String ifsc_code, String ac_type) {
		super();
		this.ac_id = ac_id;
		this.ac_number = ac_number;
		this.ifsc_code = ifsc_code;
		this.ac_type = ac_type;
	}
	
	public int getAc_id() {
		return ac_id;
	}
	public void setAc_id(int ac_id) {
		this.ac_id = ac_id;
	}
	public String getAc_number() {
		return ac_number;
	}
	public void setAc_number(String ac_number) {
		this.ac_number = ac_number;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getAc_type() {
		return ac_type;
	}
	public void setAc_type(String ac_type) {
		this.ac_type = ac_type;
	}

	public CustomerDetails getCustDetails() {
		return custDetails;
	}

	public void setCustDetails(CustomerDetails custDetails) {
		this.custDetails = custDetails;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getPin_num() {
		return pin_num;
	}

	public void setPin_num(int pin_num) {
		this.pin_num = pin_num;
	}
	
	
	
}
