package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entites.Account;
import com.vti.untils.HibernateUntil;

public class AccountRepository {

	public List<Account> getAllAccount() {
		Session session = null;

		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlQuery = "FROM Account"; // Câu lệnh query trong Hibernate => HQL
			Query<Account> query = session.createQuery(hqlQuery);
			List<Account> listResult = query.list();
			return listResult;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Account getById(int id) {
		Session session = null;
		try {
			session = HibernateUntil.getFactory().openSession();
			String hqlQuery = "FROM Account A WHERE A.id = ?2"; // Câu lệnh query trong Hibernate => HQL
			// String hqlQuery = "FROM Account A WHERE A.id = :accountID";
			// String hqlQuery = "FROM Account A WHERE A.id = " + id;
			// String hqlQuery = String.format("FROM Account A WHERE A.id = %d", id);
			Query<Account> query = session.createQuery(hqlQuery);
			query.setParameter(2, id);
			Account account = query.uniqueResult();
			System.err.println(account.stringFormat());

			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// insert dữ liệu
	public void createAccount(String name, String fullName, String email) {
		Session session = HibernateUntil.getFactory().openSession();

		Account account = new Account(name, fullName, email);

		session.save(account);

		session.close();
	}

	// update dữ liệu
	public void updateAccount(int id, String name, String fullName, String email) {
		Session session = HibernateUntil.getFactory().openSession();

		session.beginTransaction();

		String hqlQuery = "FROM Account A WHERE A.id = " + id;
		Query<Account> query = session.createQuery(hqlQuery);
		Account account = query.uniqueResult();

		if (account == null) {
			System.err.println("Không tìm thấy account phù hợp.");
			return; // dừng hàm
		}

		if (name != null) {
			account.setUsername(name);
		}
		if (fullName != null) {
			account.setFullName(fullName);
		}
		if (email != null) {
			account.setEmail(email);
		}
		// session.save(account);
		session.getTransaction().commit();

		session.close();
	}

}
