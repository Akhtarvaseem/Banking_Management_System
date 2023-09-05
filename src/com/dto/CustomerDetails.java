package com.dto;



public class CustomerDetails {

	private int c_id;
	private String name;
	private String address;
	private String ph_no;
	private String gender;
	private String ac_type;
	private String c_pass;
	private String date;
	private String time;
	private String email;
	
	private AccountOpration opration;
	
	
	
public CustomerDetails() {
	
}
	


	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPh_no() {
		return ph_no;
	}

	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAc_type() {
		return ac_type;
	}

	public void setAc_type(String ac_type) {
		this.ac_type = ac_type;
	}

	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getC_pass() {
		return c_pass;
	}

	public void setC_pass(String c_pass) {
		this.c_pass = c_pass;
	}
	
	

	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public AccountOpration getOpration() {
		return opration;
	}

	public void setOpration(AccountOpration opration) {
		this.opration = opration;
	}

	

	
	
	
	
}
