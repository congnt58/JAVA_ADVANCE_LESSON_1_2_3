package com.vti.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.function.ServerResponse.SseBuilder;

import com.vti.entites.Account;
import com.vti.entites.fillter.AccountFillter;
import com.vti.entites.form.AccountCreateForm;
import com.vti.utils.HibernateUtil;

@Repository
public class AccountRepository {

	public List<Account> getAllAccounts() {
		Session session = null;

		try {
			// để truy vấn csdl cần tạo ra 1 session
			session = HibernateUtil.getFactory().openSession();
			// tạo ra câu lệnh Hibernate query
			String hqlQuery = "FROM Account";
			Query<Account> query = session.createQuery(hqlQuery);
			List<Account> listAccounts = query.list();
			return listAccounts;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public Account getAccountByID(int id) {
		Session session = null;

		try {
			// để truy vấn csdl cần tạo ra 1 session
			session = HibernateUtil.getFactory().openSession();
			// tạo ra câu lệnh Hibernate query
			String hqlQuery = "FROM Account A WHERE A.id = " + id;
			
			Query<Account> query = session.createQuery(hqlQuery);
			Account accounts = query.getSingleResult();
			
			//cach 2:
			//Account account = session.get(Account.class, id);
			
			return accounts;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}
	
	
	public void deleteAccountBuyId(int id) {
		Session session = null;

		try {
			// để truy vấn csdl cần tạo ra 1 session
			session = HibernateUtil.getFactory().openSession();
			// tạo ra câu lệnh Hibernate query
			String hqlQuery = "FROM Account A WHERE A.id = " + id;
			
			Query<Account> query = session.createQuery(hqlQuery);
			Account account = query.getSingleResult();
			
			//cach 2:
			//Account account = session.get(Account.class, id);
			session.beginTransaction();
			session.delete(account);
			session.getTransaction().commit();
			System.out.println("delete thanh cong");
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	public void updateAccount(int id, String usernameNew, String emailNew) {
		Session session = null;

		try {
			// để truy vấn csdl cần tạo ra 1 session
			session = HibernateUtil.getFactory().openSession();
			// tạo ra câu lệnh Hibernate query
			String hqlQuery = "FROM Account A WHERE A.id = " + id;
			
			Query<Account> query = session.createQuery(hqlQuery);
			Account account = query.getSingleResult();
			
			//cach 2:
			//Account account = session.get(Account.class, id);
			session.beginTransaction();
			account.setUsername(usernameNew);
			account.setEmail(emailNew);
			session.getTransaction().commit();
			System.out.println("update thanh cong");
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	public void updateAccount(Account account) {
		Session session = null;
		try {
			// để truy vấn csdl cần tạo ra 1 session
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.update(account);
			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Lấy tất cả Account trong csdl - có phân trang
	 * 
	 * @param page số trang
	 * @param size số lượng phần tử trong 1 trang
	 * @return
	 */
	public List<Account> getAllAccount(int page, int size, AccountFillter fillter) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();

			String hqlQuery = "FROM Account"; // Câu lệnh query trong Hibernate => HQL

			if (fillter != null) {
				if (fillter.getEmail() != null) {
					hqlQuery = "FROM Account A WHERE A.email LIKE '%" + fillter.getEmail() + "%'";
				}

				if (fillter.getUsername() != null) {
					hqlQuery = "FROM Account A WHERE A.username = '" + fillter.getUsername() + "'";
				}

				if (fillter.getEmail() != null && fillter.getUsername() != null) {
					hqlQuery = "FROM Account A WHERE A.username = '" + fillter.getUsername() + "'"
							+ " OR A.email LIKE '%" + fillter.getEmail() + "%'";
				}
			}

			System.err.println("--->" + hqlQuery);

			int offset = (page - 1) * size;
			// int limit = size;

			@SuppressWarnings("unchecked")
			Query<Account> query = session.createQuery(hqlQuery);
			query.setFirstResult(offset);
			query.setMaxResults(size);

			List<Account> listResult = query.list();

			return listResult;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// id = 2, name = Cong
	public Account getById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			// String hqlQuery = "FROM Account A WHERE A.id = ?1 AND A.fullName = ?2"; //
			// Câu lệnh query trong Hibernate => HQL

			// String hqlQuery = "FROM Account A WHERE A.id = :accountID";
//			String hqlQuery = "FROM Account A WHERE A.id = " + id +" AND A.fullName = '" + name +"'";
//			String hqlQuery = String.format("FROM Account A WHERE A.id = %d AND A.fullName = '%s'", id , name);
//			
//			Query<Account> query = session.createQuery(hqlQuery);
//			query.setParameter(1, id);
//			query.setParameter(2, name);

//			Account account = query.uniqueResult();

//			Criteria criteria = session.createCriteria(Account.class);
//			List resuList = criteria.list();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Account> cr = cb.createQuery(Account.class);
			Root<Account> root = cr.from(Account.class);

			Query<Account> query = session.createQuery(cr.select(root).where(cb.equal(root.get("id"), id)));

			return query.uniqueResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// insert dữ liệu
	public Account createAccount(String name, String fullName, String email) {
		Session session = HibernateUtil.getFactory().openSession();

		Account account = new Account(name, fullName, email);

		session.save(account);
		session.close();

		// chạy qua save đúng.
		return account;

	}

	// update dữ liệu
	public void updateAccount(int id, String name, String fullName, String email) {
		Session session = HibernateUtil.getFactory().openSession();

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

	public Account deleteAccountById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();

			// lấy dữ liệu trong data base
			Account account = session.load(Account.class, id);
			if (account == null) {
				return null;
			}
			session.beginTransaction();
			session.delete(account);
			session.getTransaction().commit();
			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
