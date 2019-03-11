package com.ngs.controller;

import java.security.Key;

import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ngs.dao.implementation.LoginDaoImpl;
import com.ngs.dao.implementation.TtSystemDaoImpl;
import com.ngs.model.Log;
import com.ngs.model.Login;
import com.ngs.model.User;
import com.ngs.service.DbService;
import com.ngs.service.ExceptionService;
import com.ngs.service.SysUser;
import com.ngs.util.FileValidator;
import com.ngs.util.MyDateUtil;

@Controller
@SessionAttributes("user")
public class MainController {
	
	
	static Logger log4j = Logger.getLogger(MainController.class);
	

	@Autowired
	private DbService dBConnector;
	

	@Autowired
	public TtSystemDaoImpl TtSystemDao;
	
	@Autowired
	private LoginDaoImpl loginDaoImpl;
	
	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	@Autowired
	FileValidator fileValidator;

	
	@Autowired
	private LoginDaoImpl loginDao;
	

	Key key;
	IvParameterSpec iv;
	
	
	
	 
		@RequestMapping(value = "/welcome**", method = RequestMethod.GET)
		public ModelAndView welcomePage(HttpServletRequest request) throws ExceptionService {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
			System.out.println("Checking time out issue step 1 ");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("Checking time out issue stop 2 "+auth.toString());
			MyDateUtil date = new MyDateUtil();
			ModelAndView view = new ModelAndView();
			//com.ngs.users.model.UserDetails user;
			com.ngs.service.SysUser user;
		
			// these were commmented due to autowired has implemented
			//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-database.xml");
	   	   // ActivitiesDaoImpl activitDao =  applicationContext.getBean("activitiesDao", ActivitiesDaoImpl.class);
	   	    
			//this method didnt work becose at initiation beans in spring-database has not initiated
			//SpringServiceLocator svn = new SpringServiceLocator();
			//ActivitiesDaoImpl activitiesDao =(ActivitiesDaoImpl) svn.getBean("activitiesDao", com.ngs.dao.implementation.ActivitiesDaoImpl.class);
		
			
			try {
				
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("############### auth.getName parameter is hear " +auth.getName());
				System.out.println("############### Authorities are "+auth.getAuthorities());
				//System.out.println("############### Details are "+auth.getDetails());
				// above auth.getDetails() can be used for loggin functions.
				

				// warning, debug, error
				log4j.info("New user login "+auth.getName());
				
			
				
				
				// this part is added to redirect to login page when the session has expired
				if(auth.getName()=="anonymousUser"){
					HttpSession session = request.getSession();  
				    session.invalidate();
					view.setViewName("login");
					return view;
				}
				//com.ngs.model.Login login = auth.getClass();
				user = (com.ngs.service.SysUser) dBConnector.getUserDetails(auth.getName());
				
				
				System.out.println("Checking time out issue stop 3 "+user.getName());
				
				// how to get session object in spring mvc. (spring mvc inject it to controllers )
				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession();
				String sessionID = session.getId();
				

				// this is where we can use to log some one has logged in to the system.
					// save the log of logn
					//is client behind something?
					String ipAddress = request.getHeader("X-FORWARDED-FOR");  
					if (ipAddress == null) {  
						   ipAddress = request.getRemoteAddr();  
					}
					//
					Login login = loginDao.getLoginByID(user.getLoginID());
					
					Log logobj = loginDao.findLogBySessionID(sessionID , "System Login");
					if(logobj == null){
						
						Log log = new Log();	
						log.setDescription(sessionID);
						log.setLogin(login);
						log.setRegtime(date.getDateNow());
						log.setUser(user.getUser());
						log.setTasktype("System Login");
						log.setIp(ipAddress);
						log = loginDao.saveOrupdate(log);
						System.out.println("user------------>"+user.getUser());
						
					} 
					
		
					// end of loging functiosn

				
			} catch (Exception e) {
				e.printStackTrace();
				
				log4j.error(e.getMessage());
				
				//if any error found redirect to a error page
				throw new ExceptionService ("This is a message from second controller");
				//return model;
			}
			
			view.addObject("user", user);
			
		
						view.setViewName("ttSystem/index");
						view.addObject("title", " Welcome to NGS_TT System ");
						return view;
		
			
		}
		
		  
	
	//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-database.xml");
	
	
	
	@RequestMapping(value ="/qdisplay", produces="application/json")
	@ResponseBody
	public  String test() {
			/**
			// get the occupied sits and current patient sitting
			List<Sits> list = doctorDao.currentlyOccupiedSits();
			JsonObject result ="";
			for(Sits sit:list){	
	        result = Json.createObjectBuilder()
	                .add("position", "Dade")
	                .add("age", 23)
	                .add("married", false)
	                .build();
			}
	        return result.toString();
	      **/
		return null;
	}
	
	
	
	
	@RequestMapping(value = "/showHelp", method = RequestMethod.GET)
	public ModelAndView showHelp( HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("help/inquirey");
		return view;
	}
	
	@RequestMapping(value = "/showError", method = RequestMethod.GET)
	public ModelAndView showError( HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("error/showError");
		return view;
	}
	

	@RequestMapping(value = "/showAbout", method = RequestMethod.GET)
	public ModelAndView showAbout( HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("help/about");
		return view;
	}
	
	@RequestMapping(value = "/getInnerDiv", method = RequestMethod.GET)
	public ModelAndView loadInnertDiv(@ModelAttribute("rollid") int rollid, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		if(rollid==1){ // admin
			view.setViewName("commonInner");
			view.addObject("msg", "Admin page loaded successfully");
		}else if(rollid==4){ // Nurse
			view.setViewName("commonInner");
			//view.setViewName("lab/nurseInner");
		}else if(rollid==7){ // receptionist
			view.setViewName("reception/receptionInner");
		//}else if(rollid==5){ // pharmacist
			// patient interface should have added to this.
			//List<Patients> patients = patientDao.serachPatientForPrescriptions();
			//HtmlService htm = new HtmlService();
			//String inner= htm.createPatientQForControllPannel(patients);
			//view.addObject("inner", inner);
		//	view.setViewName("pharmacy/pharmacyInner");
			
		}else{
			view.setViewName("commonInner");
		}
		System.out.println("Loading email page from controller ");
		String message = " Ready to send an email !";
		return view;
	}

	
	
	
	//later ia added this functions to view mail sending inteface
	
		@RequestMapping(value = "/contactByEmail", method = RequestMethod.GET)
		public ModelAndView contactUsByEmail() {
			System.out.println("Loading email page from controller ");
			String message = " Ready to send an email !";
			return new ModelAndView("contactus", "msg", message);
		}
		
		
		
		
		//show the help file
		
			@RequestMapping(value = "/getHelp", method = RequestMethod.GET)
			public ModelAndView showHelpFile() {
				ModelAndView view = new ModelAndView();
				view.setViewName("help");
				String message = "Please use contact us , if you want to get more help !";
				return view;
			}	

	//XXXXXXXXXXXXXXXXXXXXXXXXX LOGIN XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	@RequestMapping(value = { "/", "/login" })
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) throws ExceptionService {
		MyDateUtil date = new MyDateUtil();
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			//model.addObject("msg", );
			
		}

		if (logout != null) {
			
			// if doctors login out make sit logout also
			SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
			if(currentUser != null){
					if(currentUser.getRolleid()== 3){ // this is a doctor
						
						User person = null;
						
						
					}
					
					
					// for all users save the logout time log
					Login login = loginDaoImpl.getLoginByID(currentUser.getLoginID());
					Log log = new Log();
					log.setDescription(" Person "+currentUser.getUser()+" logged out at "+date.getNow() );
					log.setLogin(login);
					log.setUser(currentUser.getUser());
					log.setTasktype("Log out");
					log.setRegtime(date.getDateNow());
					
					log = loginDaoImpl.saveOrupdate(log);
					
			
			} // check currnt user is not null
				

			//request.getSession(false).invalidate();
			model.addObject("msg", "You've been logged out successfully.");
		}
		// invalidate the session
		HttpSession session = request.getSession();  
	    session.invalidate();
		model.setViewName("login");

		return model;

	}
	
	
 //######################################## log out #####################################################
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(SessionStatus sessionstatus) {
		
		sessionstatus.setComplete();
		System.out.println("####log out #####1");
		ModelAndView model = new ModelAndView();
		System.out.println("####log out #####2");
		model.setViewName("login");
		
		System.out.println("####log out #####3");
		return model;
	
	}
	
	
	
	@RequestMapping(value = { "/denied**" }, method = RequestMethod.GET)
	public ModelAndView denied() {

		ModelAndView model = new ModelAndView();
		
		model.addObject("error", "Access denied! Please login with a privileged account to access this!");
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = { "/expired**" }, method = RequestMethod.GET)
	public ModelAndView expired() {

		ModelAndView model = new ModelAndView();
		
		model.addObject("error", "Session expired. Please login again!");
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = { "/invalid**" })
	public ModelAndView invalid() {

		ModelAndView model = new ModelAndView();
		
		model.addObject("error", "Invalid session. Please login!");
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = { "/error**" })
	public ModelAndView error() {

		ModelAndView model = new ModelAndView();
		
		model.addObject("error", "Error in processing request. Please login!");
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			//System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("customErrorpage");
		return model;

	}
	
	// My error handling ################################################################
	
	@ExceptionHandler(ExceptionService.class)
    public ModelAndView handleCustomException(ExceptionService ex) {

        System.out.println("Handling exception by Customer Error page !");

        ModelAndView model = new ModelAndView("customErrorpage");

        model.addObject("exception", ex);
        model.addObject("error", ex);

        return model;

    }
	
}
