package com.ngs.dao.interfaces;

import java.util.List;

//import com.ngs.model.Alergies;
import com.ngs.model.Login;
//import com.ngs.model.Person;
//import com.ngs.model.PersonHasDeceases;
//import com.ngs.model.PersonHasSocialhabits;
import com.ngs.model.Rolls;
import com.ngs.model.User;
//import com.ngs.model.Socialhabits;

public interface PersonDao {
	
	List<User> searchPersonByParam(String param, String value);
	List<User> getAllPerson(int hospitalID, int rollID);
	//List<Alergies> getAllAlergies()throws Exception;
	//List<Socialhabits> getAllSocialHabits()throws Exception;
	
	//List<Alergies> getAllergiesForAPerson(String personID) throws Exception;
	//List<PersonHasDeceases> getPersonHasDeceaseForPerson(String personID)throws Exception;
	//List<PersonHasSocialhabits> getPersonHasSocialForPerson(String personID)throws Exception;
	
	User getPersonByID(int id)throws Exception;
	Integer getLastInsertedPerson()throws Exception;
	
	
	User saveOrUpdate(User person,String command)throws Exception;
	//Alergies saveOrUpdate(Alergies alergies)throws Exception;
	//PersonHasSocialhabits saveOrUpdate(PersonHasSocialhabits phsa)throws Exception;
	

}
