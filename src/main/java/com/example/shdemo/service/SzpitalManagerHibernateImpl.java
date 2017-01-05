package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.*;

@Component
@Transactional
public class SzpitalManagerHibernateImpl implements SzpitalManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void addBadanie(Badanie badanie) {
		badanie.setId(0);
		sessionFactory.getCurrentSession().persist(badanie);
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Badanie> getAllBadania() {
		return sessionFactory.getCurrentSession().getNamedQuery("Badanie.getAllBadania").list();
	}
	
	@Override
	public Badanie getOneBadanie(long id) {
		return (Badanie) sessionFactory.getCurrentSession().getNamedQuery("Badanie.getOneBadanie").setLong("id", id).uniqueResult();	
	}
	
	
}
