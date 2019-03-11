package com.ngs.dao.interfaces;

import java.util.List;

import com.ngs.model.Login;
import com.ngs.model.Rolls;
import com.ngs.model.User;

public interface UserDao {
	
	List<User> searchUserByParam(String param, String value);
	List<User> getAllUser(int rollID);
	List<Rolls> getAllRoles()throws Exception;
	User getUserByID(int id)throws Exception;
	List<User> searchUser(String value,String param)throws Exception;
	Login saveOrUpdate(Login login);
	
	
	

}
