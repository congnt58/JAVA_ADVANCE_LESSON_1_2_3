package com.vti.services;

import java.util.List;

import com.vti.entites.Account;
import com.vti.entites.fillter.AccountFillter;
import com.vti.entites.form.AccountCreateForm;
import com.vti.exception.UpdateAccountException;

public interface IAccService {

	List<Account> getAllAccount(int page, int size, AccountFillter fillter);

	Account getAccountById(int id) throws Exception;

	Account deleteAccountById(int id) throws Exception;

	void createAccount(AccountCreateForm form);

	Account updateAccount(int id, AccountCreateForm form) throws UpdateAccountException;
	
}
