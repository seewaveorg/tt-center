package com.ngs.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ngs.dao.implementation.ActivitiesDaoImpl;
import com.ngs.dao.implementation.LoginDaoImpl;
import com.ngs.dao.interfaces.LoginDao;
import com.ngs.model.Activities;
import com.ngs.model.Log;
import com.ngs.model.Login;
import com.ngs.model.Rolls;


public class MyLoginDetailsService implements UserDetailsService {

	//@Autowired
	private LoginDaoImpl loginDao;
	//@Autowired	
	private ActivitiesDaoImpl activitiesDao;
	//@Autowired
	//private SessionFactory sessionFactory;
	

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		com.ngs.model.Login login = loginDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority( login.getRolls());
		//GrantedAuthority authorities = (GrantedAuthority) buildUserAuthority(login.getRolls());
		
		return buildUserForAuthentication(login, authorities);

	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.ngs.model.Login login, List<GrantedAuthority> authorities) {
		return new User(login.getUsername(), login.getPassword(), login.getStatus()==1, true, true, true, authorities);
		
	}
	

	//private List<GrantedAuthority> buildUserAuthority(Set<Rolls> userRoles) {
	private List<GrantedAuthority> buildUserAuthority(Rolls rolls) {
		List<Activities> activitiesList = new ArrayList<Activities>();
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		System.out.println("Roll id is"+rolls.getId());
		
		//ActivitiesDaoImpl activityDao = new ActivitiesDaoImpl();
		//activityDao.setSessionFactory(sessionFactory);
		
		if(activitiesDao.equals(null)){
			System.out.println("activity dao is null");
		}
		
		if(rolls.getId().equals(null)){
			System.out.println(" roll id is null");
		}
		
		if(activitiesDao.findActivitiesByRollID(1).equals(null)){
			System.out.println(" paramerter 1 sum is null");
		}
		
		
		if(activitiesDao.findActivitiesByRollID(rolls.getId()).equals(null)){
			System.out.println("sum is null");
		}
		else{
		 activitiesList = activitiesDao.findActivitiesByRollID(rolls.getId()); 
		}
		
		//com.ngs.model.Rolls userRole = rollDao.findByRollID( rolls.getId());
		//System.out.println("roll name is "+userRole.getName());
		//setAuths.add(new SimpleGrantedAuthority(rolls.getName()));
		
		// Build user's authorities
		for (Activities activity : activitiesList) {
			setAuths.add(new SimpleGrantedAuthority(activity.getActivitiy()));
		}
		
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		
		
		return Result;
	}

	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDaoImpl loginDao) {
		this.loginDao = loginDao;
	}

	public ActivitiesDaoImpl getActivitiesDao() {
		return activitiesDao;
	}

	public void setActivitiesDao(ActivitiesDaoImpl activitiesDao) {
		this.activitiesDao = activitiesDao;
	}

		
	


}


