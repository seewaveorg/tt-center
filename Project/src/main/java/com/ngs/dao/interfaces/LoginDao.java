package com.ngs.dao.interfaces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ngs.model.Log;
import com.ngs.model.Login;
//import com.ngs.model.Person;
import com.ngs.model.User;

	

	public interface LoginDao {

		
		Login findByUserName(String username);
		Login getLoginByID(int id);
		//Login saveOrUpdate(Login login);
		List<Login> searchLobinByParam(String param, String value);	
		List<User> GetPersonsByRoll(int rollid, int hospitalid);
		
		
		Log saveOrupdate(Log log);
		Log findLogBySessionID(String description, String task);

	}