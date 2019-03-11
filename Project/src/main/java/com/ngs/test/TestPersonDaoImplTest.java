package com.ngs.test;


//import org.testng.Assert;
import org.testng.annotations.Test;

//import com.ngs.dao.implementation.PersonDaoImpl;
//import com.ngs.model.Person;
import com.ngs.model.User;

public class TestPersonDaoImplTest {
	
	//PersonDaoImpl personDao = new PersonDaoImpl();
	User person =new User();
	
	
@Test
public void TestPersonGetByID(){

	User person = null;
	try {
	//	person = personDao.getPersonByID("1");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   // double salary = empBusinessLogic.calculateYearlySalary(employee);
   // Assert.assertEquals("tharanga", person, "8000");
}
	
	
}
