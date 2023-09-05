package com.daoInterface;

import com.dto.CustomerDetails;

public interface UpdationProcessInterface {
	
	public boolean updateAllInfo(CustomerDetails details);
	public boolean updateUserName(CustomerDetails details);
	public boolean updateUserAddress(CustomerDetails details);
	public boolean updateUserPhoneNumber(CustomerDetails details);
	public boolean updateUserGender(CustomerDetails details);
	public boolean updateUserAccType(CustomerDetails details);
	public boolean updateUserEmail(CustomerDetails details);
	
}
