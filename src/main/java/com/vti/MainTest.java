package com.vti;

import java.util.List;

import com.vti.entites.Account;
import com.vti.repository.AccountRepository;

public class MainTest {

	public static void main(String[] args) {
		//in tất cả account
		AccountRepository repository = new AccountRepository();
		
//		List<Account> list = repository.getAllAccounts();
//		
//		//in ds account
//		for (Account account : list) {
//			System.out.println(account.stringFormat());
//		}
		
		Account account = repository.getAccountByID(1);
		
		System.out.println(account.stringFormat());
	}

}
