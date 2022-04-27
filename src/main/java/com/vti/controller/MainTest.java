package com.vti.controller;

import java.util.List;

import com.vti.entites.Account;

public class MainTest {

	//frontend
	public static void main(String[] args) {
		AccountController controller = new AccountController();
		
//		List<Account> listAccounts = controller.getAllAccount();
//		
//		for (Account account : listAccounts) {
//			System.out.println(account.stringFormat());
//		}
		
		
		//lay acocunt theo id
		Account account;
		try {
			account = controller.getAccountById(-10);
			System.out.println(account);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	


}
