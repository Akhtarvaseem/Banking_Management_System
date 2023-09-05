package com.daoInterface;

import java.util.List;

import com.dto.AccountOpration;
import com.dto.transaction;


public interface BalanceOprationInterface {

	
	public boolean addBalance(AccountOpration balance);
	public boolean withdrowBalance(AccountOpration balance,int id);
	public boolean depositBalance(AccountOpration balance,int id);
	public List<AccountOpration> getBal();
    
	public List<transaction> getAllTrans();
	
	 public List<transaction> getTrans(int id);
	 public boolean setPin(AccountOpration opration3);
 
	
}
