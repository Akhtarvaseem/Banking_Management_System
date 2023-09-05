package com.service;

import java.util.List;


import com.dao.UserDtlsDao;
import com.dto.CustomerDetails;

public class UserDtlsService {

	UserDtlsDao dao=new UserDtlsDao();
	public Boolean addUserDtl(CustomerDetails details) {
		
		return dao.addUserDtl(details);
	}
	
	public boolean updateUserDtl(CustomerDetails details) {
		return dao.updateUserDtl(details);
	}
	
	public boolean deleteUserDtl(CustomerDetails details) {
		return dao.deleteUserDtl(details);
	}
	
	public List<CustomerDetails> getAllUserDtl() {
		
		return dao.getAllUserDtl();
	}
}
