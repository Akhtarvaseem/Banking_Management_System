package com.daoInterface;

public interface LoginInterface {

	public   boolean userLogin(String emailId,String CPassword);
	public   boolean userChangePassword(String custName,String newPassword);
	public boolean adminLogin(String adminName, String adminPassword);
	public  boolean adminChangePassword(String adminUserName, String adminNewPassword);
}
