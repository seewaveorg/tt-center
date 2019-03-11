package com.ngs.service;

import java.awt.geom.Area;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.model.Login;
//import com.ngs.model.Person;





public class DbService {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	/**
	protected Session getSession(){
		Session session;
		session = getSessionFactory().getCurrentSession();
		
		return session;
	}
	**/
	@Transactional
	protected Login getLogin(String username){
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Login as L where L.username = :uname");// here should be something else than list()
		query.setString("uname", username);
		List<Login> list = query.list();
		return (list.isEmpty() ? null : list.get(0));
	}
	

	
	@Transactional
	public SysUser getUserDetails(String username) throws Exception{
		
		Session session = getSessionFactory().getCurrentSession();
		
		
		SysUser user = new SysUser();
		
		Login login = getLogin( username);
		user.setUsername(login.getUsername());
		user.setUser(login.getUser().getId());///////////////////////////////////////
		user.setName(login.getUser().getFirstname());/////////////////////
		user.setId(login.getId());
		user.setRolle(login.getRolls().getName());
		user.setRolleid(login.getRolls().getId());
		user.setLoginID(login.getId());
		user.setCompany(login.getUser().getCompany());
	
		return user;
		
	}
	
	
	/**
	@Transactional
	public List<AreaGenderCount> getAreaGenderWiseCount() throws Exception{
		
		Query query = getSession().getNamedQuery("spGetAreaGenderCount");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = (List<Object[]>) query.list();
				
		List<AreaGenderCount> areaGenderCountList = new ArrayList<AreaGenderCount>();
		for(Object[] row : rows){
		    AreaGenderCount areaGenderCount = new AreaGenderCount();
		    areaGenderCount.setArea((String) row[0]);
		    areaGenderCount.setMaleCount(((BigDecimal) row[1]).intValue());
		    areaGenderCount.setFemaleCount(((BigDecimal) row[2]).intValue());
		    
		    areaGenderCountList.add(areaGenderCount);
		}
		return areaGenderCountList;
	}
	
	
	@Transactional
	public List<AdCount> getAdWiseCount() throws Exception{
		
		Query query = getSession().getNamedQuery("spGetAdCount");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = (List<Object[]>) query.list();
				
		List<AdCount> adCountList = new ArrayList<AdCount>();
		for(Object[] row : rows){
		    AdCount adCount = new AdCount();
		    adCount.setAdName("Etisalat Data");
		    adCount.setCount(((BigDecimal) row[0]).intValue());
		    adCountList.add(adCount);
		    
		    adCount = new AdCount();
		    adCount.setAdName("Etisalat IDD");
		    adCount.setCount(((BigDecimal) row[1]).intValue());
		    adCountList.add(adCount);
		}
		return adCountList;
	}
	**/
}
