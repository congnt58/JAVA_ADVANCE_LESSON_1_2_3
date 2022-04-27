package com.vti.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Account")
public class Account {

	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "Username")
	private String username;

	@Column(name = "Fullname")
	private String fullName;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Transient
	private String gioiTinh;

	// bắt buộc phải có hàm khởi tạo rỗng
	public Account() {
		super();
	}

	public Account(String name, String fullName2, String email2) {
		this.username = name;
		this.fullName = fullName2;
		this.email = email2;
	}

	// và phải có hàm getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", username=" + username + ", fullName=" + fullName
				+ ", createDate=" + createDate + "]";
	}

	public String stringFormat() {
		return String.format("%5d | %15s | %20s | %-30s | %20s", id, username, fullName, email,
				createDate != null ? createDate.toString() : "null");
	}

}
