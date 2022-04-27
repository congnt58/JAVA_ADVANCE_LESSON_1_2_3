package com.vti.untils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entites.Account;


public class HibernateUntil {
	private final static SessionFactory FACTORY;

	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		cfg.addAnnotatedClass(Account.class);

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

		FACTORY = cfg.buildSessionFactory(registry);
	}
	

	public static SessionFactory getFactory() {
		return FACTORY;
	}
}
