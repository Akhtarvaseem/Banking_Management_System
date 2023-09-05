package com.service;

import java.util.List;

import com.dao.AccountDtlsDao;
import com.dto.AccountOpration;

public class AccountDtlsService {

	AccountDtlsDao dao= new AccountDtlsDao();
	
public boolean addAccountDtls(AccountOpration opration) {
		
		
		return dao.addAccountDtls(opration);
	}

	
	public boolean updateAccountDtls(AccountOpration opration) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteAccountDtls(AccountOpration opration) {
		// TODO Auto-generated method stub
		return false;
	}


	public List<AccountOpration> getAllDtls() {
		
		return dao.getAllDtls();
	}
}
