package com.ngs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ngs.dao.implementation.LoginDaoImpl;
import com.ngs.dao.implementation.UserDaoImpl;
import com.ngs.model.Login;
import com.ngs.model.Rolls;
import com.ngs.model.User;
import com.ngs.service.ExceptionService;
import com.ngs.service.HtmlGenerator;

@Controller
@SessionAttributes("user")
public class UserController {
	
	static Logger log4j = Logger.getLogger(UserController.class);
	
	
	
	@Autowired
	private LoginDaoImpl loginDaoImpl;
	
	/*@Autowired
	private PersonDaoImpl personDaoimpl;*/
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	
	
	@RequestMapping(value = "/getUserTable", method = RequestMethod.GET )	
	public @ResponseBody String getPersonTable (@ModelAttribute("value") String value, @ModelAttribute("param") String param, HttpServletResponse res) throws ExceptionService {
		
		System.out.println("param****"+param+" value*******"+value);
		HtmlGenerator html = new HtmlGenerator();
		String table = "";
		
		List<User> personList=null;
		try {
			personList = userDaoImpl.searchUser(value, param);
			
			System.out.println("person list########"+personList);
			table=html.generatePersonTable(personList);
			System.out.println("table#######"+table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	
	
	
	
	@RequestMapping(value = "/saverOrUpdateLogin", method = RequestMethod.POST )
	public  @ResponseBody String getPersonByID(@ModelAttribute("login") Login login, Map<String, Object> model) {
		try{
		//String redirect = "redirect:/welcome.do";
		System.out.println("controller login########"+login);
		login = userDaoImpl.saveOrUpdate(login);
		//return redirect;
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "Success";
	
	}

	
	
	
	// save a new user
	@RequestMapping(value = "/showUserCreation", method = RequestMethod.GET)
	public ModelAndView personPage(Map<String, Object> model, HttpServletRequest request) throws ExceptionService {
	// public String viewLogin(Map<String, Object> model) {
		ModelAndView view = new ModelAndView();
		view.setViewName("admin/userTable");		
		//create a Login object
		Login login = new Login();
		// add the Person object to model
		//model.addObject("newPerson", person);
		model.put("login", login);
		//return model;
		return view;
		
	}
	
	@RequestMapping(value = "/createLogin", method = RequestMethod.GET)
	public ModelAndView createLogin(HttpServletRequest request, @ModelAttribute("value") String value,
			BindingResult result, Map<String, Object> model) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("admin/createLogin");
		int id=Integer.parseInt(value);
		User person=null;
		try {
			
			person=userDaoImpl.getUserByID(id);
			String name=person.getFirstname();
			System.out.println("name##########"+name);
			model.put("name", name);
			
			List<Rolls> rollsList = null;

			
				rollsList = userDaoImpl.getAllRoles();
			
			Map<Integer, String> rolesOptionList = new HashMap<Integer, String>();

			for (Rolls rolls : rollsList) {

				rolesOptionList.put(rolls.getId(), rolls.getName());
			}

			model.put("roleList", rolesOptionList);
			//model.put("login", person);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create a Login object
		System.out.println("person name "+person.getId());
		Login login = new Login();
		login.setUser(person);
		model.put("login", login);
		// add the Person object to model
		//model.addObject("newPerson", person);
		
		//return model;
		return view;
		
	}
	
	
	
	


}
