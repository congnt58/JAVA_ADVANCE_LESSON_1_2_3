package com.vti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entites.Account;
import com.vti.entites.fillter.AccountFillter;
import com.vti.entites.form.AccountCreateForm;
import com.vti.exception.UpdateAccountException;
import com.vti.repository.AccountRepository;
import com.vti.repository.DepartmentRepository;

@Service
public class AccountService implements IAccService{
	
	@Autowired
	AccountRepository repository;
	@Autowired
	DepartmentRepository dpRepository;
	
	
	public AccountService() {
	}

	@Override
	public List<Account> getAllAccount(int page, int size, AccountFillter fillter) {
		if (page < 1) {
			page = 1;
		}
		if (size == 0) {
			size = 20;
		}		
		return repository.getAllAccount(page, size, fillter);
	}
	
//	public List<Department> getAllDepartment() {
//		List<Department> list =  dpRepository.getAll();
//		return list;
//	}

	@Override
	public Account getAccountById(int id) throws Exception {
		if(id < 0) {
			throw new Exception("ID khong hop le");
		}
		return repository.getById(id);
	}

	@Override
	public Account deleteAccountById(int id) throws Exception {
		if(id < 0) {
			throw new Exception("ID khong hop le");
		}
		return repository.deleteAccountById(id);
	}

	@Override
	public void createAccount(AccountCreateForm form) {
		//TODO check email hop le hay ko
		
		repository.createAccount(form.getUsername(), form.getFullname(), form.getEmail());
		
	}

	@Override
	public Account updateAccount(int id, AccountCreateForm form) throws UpdateAccountException {
		//tim account có id trùng với id truyền vào
		Account account = repository.getById(id);
		//nếu mà không tìm thì ném ra 1 cái lỗi
		if(account == null) {
			throw new UpdateAccountException("Update thất bại => Không tìm thấy id cần update");
		}
		if (form.getUsername() != null) {
			account.setUsername(form.getUsername());
		}
		if(form.getFullname() != null) {
			account.setFullName(form.getFullname());
		}
		if(form.getEmail() != null) {
			account.setEmail(form.getEmail());
		}
		
		repository.updateAccount(account);
		
		return account;
	}

}
