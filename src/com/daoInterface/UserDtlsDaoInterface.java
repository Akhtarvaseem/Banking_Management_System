package com.daoInterface;

import java.util.List;

import com.dto.CustomerDetails;

public interface UserDtlsDaoInterface {

	public boolean addUserDtl(CustomerDetails details);
	public boolean updateUserDtl(CustomerDetails details);
	public boolean deleteUserDtl(CustomerDetails details);
	public List<CustomerDetails> getAllUserDtl();
	
}
