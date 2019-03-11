package com.ngs.dao.implementation;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.dao.interfaces.LoginDao;
import com.ngs.model.Log;
import com.ngs.model.Login;
//import com.ngs.model.Person;
import com.ngs.model.User;

public class LoginDaoImpl implements LoginDao{
	
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

	@SuppressWarnings("unchecked")
	@Transactional
	public Login findByUserName(String username) {
		
		List<Login> login = new ArrayList<Login>();

		login = getSessionFactory().getCurrentSession()
			.createQuery("from Login where username=?")
			.setParameter(0, username).list();

		if (login.size() > 0) {
		
			return login.get(0);
			
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public List<Login> searchLobinByParam(String param, String value) {
	Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Login.class);
		if(param.equals("id")|| param.equals("person.id")){
			int val = Integer.parseInt(value);
			System.err.println("################### param "+param+ " val is "+val);
			criteria.add(Restrictions.eq(param, val) );
		}else{
			criteria.add(Restrictions.like(param, value) );
		}
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(10);
		List<Login> result = criteria.list();
		
		return result;
	}

	@Override
	@Transactional
	public Login  getLoginByID(int id) {
	Session session = sessionFactory.getCurrentSession();
	return (Login) session.load(Login.class,id);
	
	}
	
	
	/*@Override
	@Transactional
	public Login saveOrUpdate(Login login){
		Session session = sessionFactory.getCurrentSession();
		
		//for password bcript is used in spring mvc
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(login.getPassword());
		login.setPassword(hashedPassword);		
		
		if(login.getId()==null){
			session.save(login);
		}else{
			session.update(login);
		}
		
		return login;
	}*/



	@Override
	@Transactional
	public Log saveOrupdate(Log log) {
		Session session = sessionFactory.getCurrentSession();
		if(log.getId()==null){
			
			session.save(log);
			
		}else{
			session.update(log);
		}
		
		return log;
	}

	@Override
	@Transactional
	public List<User> GetPersonsByRoll(int rollid , int hospitalid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Login.class);
		criteria.add(Restrictions.eq("hospital.id", hospitalid));
		criteria.add(Restrictions.eq("rolls.id", rollid));
		List<Login> loginlist = criteria.list();
		
		List<User> personlist = new ArrayList();
		for(Login login:loginlist){
			/////////////////////////////
			///////////////personlist.add(login.getUsers());/////////////////////////////////////////////
		}
		return personlist;
	}

	@Override
	@Transactional
	public Log findLogBySessionID(String description, String task) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Log.class);
		criteria.add(Restrictions.eq("description", description));
		criteria.add(Restrictions.eq("tasktype", task));
		List<Log> logs = criteria.list();
		if(logs.isEmpty()){
			return null;
		}else{
		return logs.get(0);
		}
	}

	
}
