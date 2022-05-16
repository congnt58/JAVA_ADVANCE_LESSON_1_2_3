package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entites.Account;
import com.vti.entites.dto.AccountDTO;
import com.vti.entites.fillter.AccountFillter;
import com.vti.entites.form.AccountCreateForm;
import com.vti.exception.UpdateAccountException;
import com.vti.services.IAccService;

@RestController
@RequestMapping(value = "/v1/api")
@CrossOrigin("*")
public class AccountController {

	@Autowired
	IAccService service;

	public AccountController() {
	}

	@GetMapping(value = "/accounts")
	public List<AccountDTO> getAllAccount(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size, AccountFillter fillter) {
		List<Account> listAccounts = service.getAllAccount(page, size, fillter);
		
		//chuyển đổi 1 list Account thanh 1 list AccountDTO
		List<AccountDTO> listAccountDTOs = new ArrayList();
		
		for (Account account : listAccounts) {
			AccountDTO accountDTO = new AccountDTO(account.getId(), account.getUsername(), account.getEmail(), account.getFullName(),
					account.getDepartment() != null ? account.getDepartment().getName() : "null");
			listAccountDTOs.add(accountDTO);
		}
		return listAccountDTOs;

	}

	@GetMapping(value = "/accounts/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable(value = "id") int id) {
		try {
			Account account = service.getAccountById(id);
			AccountDTO accountDTO = new AccountDTO(account.getId(), account.getUsername(), account.getEmail(), account.getFullName(),
					account.getDepartment() != null ? account.getDepartment().getName() : "null");
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/accounts/{id}")
	public ResponseEntity<?> deleteAccountById(@PathVariable(value = "id") int id) {
		 try {
			service.deleteAccountById(id);
			return new ResponseEntity<String>("Delete account thanh cong", HttpStatus.OK);
		} catch (Exception e) {
		   return new ResponseEntity<String>("Detele account that bai : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/accounts")
	public ResponseEntity<?> createAccount(@RequestBody AccountCreateForm form){
		service.createAccount(form);
		return new ResponseEntity<String>("Tao account thanh cong", HttpStatus.OK);
	}
	
	@PutMapping(value = "/accounts/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable(value = "id") int id,
			@RequestBody AccountCreateForm form){
		try {
			Account	accountUpdate = service.updateAccount(id, form);
			
			AccountDTO accountDTO = new AccountDTO(accountUpdate);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
			
		} catch (UpdateAccountException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	

}
