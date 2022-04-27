package com.vti.controller;

import java.util.List;

import com.vti.entites.Account;
import com.vti.services.AccountService;

public class AccountController {
	
	AccountService service;
	
	public AccountController() {
		service = new AccountService();
	}
	
	public List<Account> getAllAccount() {
		List<Account> listAccounts = service.getAllAccount();
		
		return listAccounts;
		
	}

	public Account getAccountById(int id) throws Exception {
		return service.getAccountById(id);
	}

	public Account deleteAccountById(int id) throws Exception {
		return service.deleteAccountById(id);
		
	}
	
}
