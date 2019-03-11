package com.ngs.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.dao.interfaces.UserDao;
import com.ngs.model.Login;
import com.ngs.model.Rolls;
import com.ngs.model.User;

//import com.ngs.model.User;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	@Override
	@Transactional
	public List<User> searchUserByParam(String param, String value) {
		// get a list of person according to given param and value
		System.out.println("################param " +param+"  value is "+value );
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		if(param.equals("id")){
		criteria.add( Restrictions.eq(param, value) );	
		}else{
		criteria.add( Restrictions.like(param, value, MatchMode.ANYWHERE) );
		}
		criteria.addOrder(Order.asc("firstname"));
		criteria.addOrder(Order.asc("familyname"));
		criteria.setMaxResults(10);
		
		return criteria.list();
	}


	@Override
	@Transactional
	public User getUserByID(int id)throws Exception {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user; 
	}

	
	

	@Override
	@Transactional
	public List<User> getAllUser(int roll) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("roll", roll));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}


	@Override
	@Transactional
	public List<User> searchUser(String value, String param) throws Exception {
				System.out.println("################param " +param+"  value is "+value );
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add( Restrictions.like(param, value, MatchMode.ANYWHERE) );
				
				criteria.addOrder(Order.asc("firstname"));
				criteria.addOrder(Order.asc("lastname"));
				
				return criteria.list();
	}


	@Override
	@Transactional
	public List<Rolls> getAllRoles() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Rolls.class);
		//criteria.add(Restrictions.eq("roll", roll));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@Override
	@Transactional
	public Login saveOrUpdate(Login login){
		
		System.out.println("login#######"+login);
		
		Session session = sessionFactory.getCurrentSession();
		
		//for password bcript is used in spring mvc
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(login.getPassword());
		System.out.println("encrioted########"+hashedPassword);
		login.setPassword(hashedPassword);		
		
		if(login.getId()==null){
			session.save(login);
		}else{
			session.update(login);
		}
		
		return login;
	}
}
