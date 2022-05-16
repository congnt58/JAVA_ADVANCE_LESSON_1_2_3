package com.vti.entites.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.entites.Account;

public class AccountDTO {
	
	@JsonProperty(value = "account_id")
	private int id;
	private String username;
	private String email;
	private String fullname;
	@JsonProperty(value = "department_name")
	private String dpName;
	
	public AccountDTO(int id, String username, String email, String fullname, String dpName) {
		super();
		this.id= id;
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.dpName = dpName;
	}
	
	public AccountDTO(Account account) {
		this.id = account.getId();
		this.username = account.getUsername();
		this.email = account.getEmail();
		this.fullname = account.getFullName();
		this.dpName = account.getDepartment() != null ? account.getDepartment().getName() : "null";
	}
	
	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public int getId() {
		return id;
	}


	public void setId(int idAccount) {
		this.id = idAccount;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDpName() {
		return dpName;
	}
	public void setDpName(String dpName) {
		this.dpName = dpName;
	}
	
}	
