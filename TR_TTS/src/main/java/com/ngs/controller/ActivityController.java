package com.ngs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ngs.dao.implementation.ActivitiesDaoImpl;
import com.ngs.dao.implementation.LoginDaoImpl;
import com.ngs.dao.implementation.RollsDaoImpl;
import com.ngs.model.Activities;
import com.ngs.service.HtmlServiceOPD;


@Controller
@SessionAttributes("user")
public class ActivityController {
	
	
	@Autowired
	private ActivitiesDaoImpl activityDao;
	
	@Autowired
	private RollsDaoImpl rollsDao;
	
	@Autowired
	private LoginDaoImpl loginDao;

	

	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public @ResponseBody String getUserActivities(@ModelAttribute("rollid") int id) {
		
	HtmlServiceOPD html = new HtmlServiceOPD();
	
	//List<Activities> activities =   activityDao.findActivitiesByRollID(id);
	//String activities[] = activityDao.findActivitiesStringByRollID(id);
	//String menu = html.generateUserMenu(activities);
	List<Activities> activities = activityDao.findActivitiesByRollID(id); 
	
	String menu = html.getUserMenu(activities);
	 
	return menu;
	}
	
	
	// bellow two mehtod was abondoned due to it's further imporeved common map methods used in bllow.
	
	/**
	@RequestMapping(value = "/ShowActivityPlan", method = RequestMethod.GET)
	public ModelAndView showActivityPlanner(Map<String, Object> model, HttpServletRequest request) throws ExceptionService {
		HtmlService html = new HtmlService();
		ModelAndView view = new ModelAndView();	
		view.setViewName("admin/CommonMechanism");
		// Complete the left side
				// get left object list
				List<Rolls> rolls = rollsDao.getAllRolls();
				// createa a html table with rolls
				String rolltable = html.generateRollsTable(rolls);
				view.addObject("select-one-items", rolltable);
				view.addObject("one-item-name", "System Rolls");
		// Complete the right side
				// get all available section categories
				List<Category> categories =  activityDao.getAllCategories();
				view.addObject("categories",categories);
				
				// get right object lsit
				List<Activities> activities =  activityDao.getAllActivities(); // this will get active Activities
				//convert acgtivites list to a table
				String activityTable = html.generateActivitiesTable(activities);
				view.addObject("select-many-items", activityTable);
				view.addObject("many-items-names", "Select Activities");
		
	
	
		return view;
	}
	
	
	
	
	// save the new roll activity ampping
	
	@RequestMapping(value = "/saveRollActivityMapping", method = RequestMethod.GET)
	public ModelAndView saveRollActivityMapping(String single, String multiple, HttpServletRequest request) throws ExceptionService {
		//Required Services
		MyDateUtil date = new MyDateUtil();
		HtmlService html = new HtmlService();
		HttpSession httpSession = request.getSession();
		SysUser user = (SysUser)httpSession.getAttribute("user");
		
		// update the log
		Log log = new Log();
		log.setLogin(loginDao.getLoginByID(user.getLoginID()));
		log.setTime(date.getDateNow());
		log.setDescription("Person id "+user.getPerson()+" has saved new roll - activity mapping ");
		log = loginDao.saveOrupdate(log);
		
		// make arrays for mapping
		String[] singleItems = single.split("\\|");
		String[] multipleItems = multiple.split("\\|");
		
		// Open Hibernate sessions
		
		
		for(int i=0; i< singleItems.length;i++){
			// get the roll from id
			Rolls roll =  rollsDao.findByRollID(Integer.parseInt(singleItems[i]));
				// first delete all records in RollHasActivities bellongs to a Roll
					boolean deleted = rollsDao.deleteAllRollHasActivities(roll);
			
				for(int x=0; i< multipleItems.length;i++){
					Activities activity = activityDao.getActivityByID(Integer.parseInt(multipleItems[i]));
						
						// save the changes
					    RollHasActivities rha = new RollHasActivities();
					    rha.setRolls(roll);
					    rha.setActivities(activity);
					    rha.setStatus(1);
					    
					    // save the RollHasActivities
					    rha = rollsDao.saveOrUpdate(rha);
						
					
				}
		
			
		}

		
		return new ModelAndView("redirect:/ShowActivityPlan.do");
	
	}
	
	**/

		

}
