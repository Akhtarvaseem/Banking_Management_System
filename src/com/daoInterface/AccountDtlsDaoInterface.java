package com.daoInterface;

import java.util.List;

import com.dto.AccountOpration;

public interface AccountDtlsDaoInterface {

	public boolean addAccountDtls(AccountOpration opration);
	
	public List<AccountOpration> getAllDtls();
	public void getAcId(String num);
}
