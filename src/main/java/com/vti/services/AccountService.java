package com.vti.services;

import java.util.List;

import com.vti.entites.Account;
import com.vti.repository.AccountRepository;

public class AccountService {
	
	AccountRepository repository;
	
	public AccountService() {
		repository = new AccountRepository();
	}

	public List<Account> getAllAccount() {
		return repository.getAllAccount();
	}

	public Account getAccountById(int id) throws Exception {
		if(id < 0) {
			throw new Exception("ID khong hop le");
		}
		return repository.getById(id);
	}

	public Account deleteAccountById(int id) throws Exception {
		if(id < 0) {
			throw new Exception("ID khong hop le");
		}
		return repository.deleteAccountById(id);
	}

}
