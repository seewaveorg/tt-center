package com.ngs.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ngs.dao.implementation.TtSystemDaoImpl;
import com.ngs.dao.implementation.UserDaoImpl;
import com.ngs.model.Activities;
import com.ngs.model.Alarm;
import com.ngs.model.Branch;
import com.ngs.model.Brand;
import com.ngs.model.Company;
import com.ngs.model.Department;
import com.ngs.model.Heartbeat;
import com.ngs.model.Login;
import com.ngs.model.PersonType;
import com.ngs.model.RollHasActivities;
import com.ngs.model.Rolls;
import com.ngs.model.Systems;
import com.ngs.model.SystemsHasUser;
import com.ngs.model.Tt;
import com.ngs.model.TtUpdate;
import com.ngs.model.Types;
import com.ngs.model.User;
import com.ngs.model.UserHasDepartment;
import com.ngs.service.ExceptionService;
import com.ngs.service.HtmlGenerator;
import com.ngs.service.MailService;
import com.ngs.service.SysUser;
import com.ngs.util.MyDateUtil;

@Controller
@SessionAttributes("user")
public class TtController {

	static Logger log4j = Logger.getLogger(TtController.class);

	@Autowired
	public TtSystemDaoImpl TtSystemDao;

	@Autowired
	public UserDaoImpl userDaoimpl;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, Map<String, Object> model) throws Exception {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			System.out.println("session logout");

			view.setViewName("login?logout");

			return view;

		} else {

			// making the user menu

			List<RollHasActivities> activity = TtSystemDao.getActivitiesByRoll(currentUser.getRolleid());
			List<Integer> activityId = new ArrayList<>();

			for (RollHasActivities activityList : activity) {

				activityId.add(activityList.getActivities().getId());
			}

			HtmlGenerator html = new HtmlGenerator();
			String a = "";
			try {
				List<Activities> list = TtSystemDao.getActivities(activityId);
				// System.out.println("activitylist###################333" + list);
				a = html.generateActivityList(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println("-------->show troubleticket");
			ModelAndView view = new ModelAndView();

			// put the menu string to the view

			view.addObject("menu", a);

			view.setViewName("ttSystem/index");

			return view;
		}

	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%% dashboard");

			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			view.setViewName("");

			return view;

		} else {

			// System.out.println("-------->show troubleticket");
			ModelAndView view = new ModelAndView();

			view.setViewName("ttSystem/dashboard");

			return view;
		}

	}

	/*
	 * @RequestMapping(value = "/saveTroubleTicketUpdate", method =
	 * RequestMethod.POST) public ModelAndView
	 * saveTroubleTicketUpdate(HttpServletRequest
	 * request,@Valid @ModelAttribute("UpdateTt1") TtUpdate ttup, BindingResult
	 * result, Map<String, Object> model) {
	 * 
	 * ModelAndView view = new ModelAndView();
	 * 
	 * //Tt troubleTicket = new Tt();
	 * 
	 * String msg = ""; if (result.hasErrors()) { msg = "There are some errors  : "
	 * + result.getNestedPath() + " object " + result.getObjectName() +
	 * result.getAllErrors().toString(); log4j.error("Person Save Error : " + msg);
	 * // return "person"; return view; } else { SysUser currentUser = (SysUser)
	 * request.getSession().getAttribute("user"); User user; try { user =
	 * userDaoimpl.getUserByID(currentUser.getUser()); ttup.setUser(user); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * //ttup.setUser(user); MyDateUtil date = new MyDateUtil();
	 * ttup.setRegtime(date.getDateNow()); ttup=TtSystemDao.saveOrUpdate(ttup);
	 * view.setViewName("ttSystem/dashboard");
	 * 
	 * return view; } }
	 */

	@RequestMapping(value = "/troubleTicketUpdate", method = RequestMethod.GET)
	public ModelAndView troubleTicketUpdate(HttpServletRequest request, Map<String, Object> model)
			throws ExceptionService {
		System.out.println("trouble ticket show");
		TtUpdate ttupdate = new TtUpdate();
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {
			// SysUser currentUser = (SysUser)
			// request.getSession().getAttribute("user");
			User user = null;
			try {
				user = userDaoimpl.getUserByID(currentUser.getUser());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ttupdate.setUser(user);

			// --------------------------user
			// list----------------------------------

			List<User> userList = null;

			try {
				userList = TtSystemDao.getUsers(currentUser.getCompany().getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> userOptionList = new HashMap<Integer, String>();

			for (User users : userList) {

				userOptionList.put(users.getId(), users.getFirstname());
			}

			model.put("userList", userOptionList);
			// ------------------------------------------------------------------------
			model.put("UpdateTt", ttupdate);
			ModelAndView view = new ModelAndView();
			view.setViewName("ttSystem/update");
			return view;
		}

	}

	@RequestMapping(value = "/alarm", method = RequestMethod.GET)
	public ModelAndView alarm(HttpServletRequest request, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {
			// System.out.println("-------->show troubleticket");
			ModelAndView view = new ModelAndView();
			Alarm alarm = new Alarm();
			model.put("alarm", alarm);

			// --------types dropdown--------------------------------
			List<Types> typeList = null;

			try {
				typeList = TtSystemDao.getTypes();
			} catch (Exception e) {

				e.printStackTrace();
			}

			Map<Integer, String> TypeOptionList = new HashMap<Integer, String>();

			for (Types typeOption : typeList) {
				TypeOptionList.put(typeOption.getId(), typeOption.getName());

			}

			model.put("typelist", TypeOptionList);
			// -------------------------------------------------------

			// -----------------------------System drop down---------------

			List<Systems> systemList = null;

			try {
				systemList = TtSystemDao.getSystem(currentUser.getCompany().getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> systemOptionList = new HashMap<Integer, String>();

			for (Systems system : systemList) {

				systemOptionList.put(system.getId(), system.getName());
			}

			model.put("systemList", systemOptionList);
			// ------------------------------------------------------------

			view.setViewName("ttSystem/alarm");

			return view;
		}

	}

	@RequestMapping(value = "/saveAlarm", method = RequestMethod.POST)
	public ModelAndView saveAlarm(@Valid @ModelAttribute("alarm") Alarm alarm, BindingResult result,
			Map<String, Object> model) {

		ModelAndView view = new ModelAndView();
		String msg = "";
		if (result.hasErrors()) {
			msg = "There are some errors  : " + result.getNestedPath() + " object " + result.getObjectName()
					+ result.getAllErrors().toString();
			log4j.error("Person Save Error : " + msg);
			// return "person";
			return view;
		} else {
			try {

				MyDateUtil date = new MyDateUtil();
				System.out.println(date.getDateNow());
				alarm.setRegtime(date.getDateNow());
				alarm.setStatus(1);
				alarm = TtSystemDao.saveOrUpdate(alarm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			view.setViewName("ttSystem/index");

			return view;
		}
	}

	@RequestMapping(value = "/currentalarm", method = RequestMethod.GET)
	public ModelAndView showCurrentAlarmTable(HttpServletRequest request, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		ModelAndView view = new ModelAndView();

		if (currentUser == null) {
			view.setViewName("login?logout");
			return view;

		} else {

			view.setViewName("ttSystem/alarmTable");

			HtmlGenerator html = new HtmlGenerator();

			String table = "";
			try {
				List<Systems> systemList = TtSystemDao.getSystemByCompany(currentUser.getCompany().getId());
				// System.out.println("##############system list " + systemList);

				int size = 15;
				int param = 0;
				List<Alarm> list = TtSystemDao.getAlarmListBystatus(size, param, systemList.get(0).getId(), 1);// getAlarmsListForCompany(systemList);

				table = html.generateAlarmTable(list);

				String pagination = "<div class='pagination'><ul>"
						+ "<li class='disabled'><a href='#' onClick='loadOnClick(0)' >First</a></li>";
				int y = 0;
				int x = 0;
				for (int i = 0; i < TtSystemDao.getAlarmsListForCompanyandstatus(systemList.get(0).getId(), 1)
						.size(); i++) {

					if (i % 15 == 0) { // 15 records per page
						y = y + 1;
						x = i;
						pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x + ")' >"
								+ y + "</a></li>";
					}

				}
				pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
						+ ")' >Last</a></li>" + "</ul></div>";

				view.addObject("pagination", pagination);

				// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

			} catch (Exception e) {
				System.out.println("Error in  currentalarm tabale load");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.addObject("table", table);

			return view;

		}

	}

	@RequestMapping(value = "/showAlarmTable", method = RequestMethod.GET)
	public ModelAndView showAlarmTable(HttpServletRequest request, Map<String, Object> model) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {
			ModelAndView view = new ModelAndView();
			view.setViewName("ttSystem/alarmTable");
			HtmlGenerator html = new HtmlGenerator();
			String table = "";
			try {
				List<Systems> systemList = TtSystemDao.getSystemByCompany(currentUser.getCompany().getId());
				// get the result set

				////////////////////////////////// List<Alarm> list =
				////////////////////////////////// TtSystemDao.getAlarms();
				int size = 15;
				int param = 0;
				List<Alarm> list = TtSystemDao.searchAlarmForPagination(size, param, systemList);// getAlarmsListForCompany(systemList);

				table = html.generateAlarmTable(list);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			view.addObject("table", table);

			return view;
		}
	}

	// -------------------------------tticket----------------------------------------------------------
	@RequestMapping(value = "/tticket", method = RequestMethod.GET)
	public ModelAndView ShowTTicket(HttpServletRequest request, Map<String, Object> model) throws ExceptionService {

		Tt tt = new Tt();
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		int roll = currentUser.getRolleid();
		ModelAndView view = new ModelAndView();

		if (currentUser == null) {
			// ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {
			try {
				// SysUser currentUser = (SysUser)
				// request.getSession().getAttribute("user");
				User user = userDaoimpl.getUserByID(currentUser.getUser());

				tt.setUser(user);// setcurrent owner to tt table
				tt.setCompany(user.getCompany());

				if (roll == 19) {

					// --------------------------------Company Drop
					// down--------------------
					List<Company> companyList = null;

					// all companies
					companyList = TtSystemDao.getCompany();

					Map<Integer, String> companyOptionList = new HashMap<Integer, String>();

					for (Company company : companyList) {

						companyOptionList.put(company.getId(), company.getName());
					}

					model.put("companyList", companyOptionList);
					model.put("ticketsave", tt);

					view.setViewName("ttSystem/troubleticket");

					// -------------------------------------------------------------------------
				} else {
					// -----------------------------System drop
					// down---------------

					List<Systems> systemList = null;

					try {
						systemList = TtSystemDao.getSystem(currentUser.getCompany().getId());
					} catch (Exception e) {

						e.printStackTrace();
					}
					Map<Integer, String> systemOptionList = new HashMap<Integer, String>();

					for (Systems system : systemList) {

						systemOptionList.put(system.getId(), system.getName());
					}

					model.put("systemList", systemOptionList);
					// --------------------------------------------------------------------

					// --------------------------------Company Drop
					// down--------------------
					List<Company> companyList = null;

					try {
						companyList = TtSystemDao.getCompany();
					} catch (Exception e) {

						e.printStackTrace();
					}
					Map<Integer, String> companyOptionList = new HashMap<Integer, String>();

					for (Company company : companyList) {

						companyOptionList.put(company.getId(), company.getName());
					}

					model.put("companyList", companyOptionList);

					// -------------------------------------------------------------------------

					// ----------------------------department drop
					// down-------------------------
					List<Department> departmentList = null;

					try {
						departmentList = TtSystemDao.getDepartment(currentUser.getCompany().getId());
					} catch (Exception e) {

						e.printStackTrace();
					}
					Map<Integer, String> departmentOptionList = new HashMap<Integer, String>();

					for (Department department : departmentList) {

						departmentOptionList.put(department.getId(), department.getName());
					}

					model.put("departmentList", departmentOptionList);

					// -------------------------------------------------------------------------

					// --------------------------user drop
					// down----------------------------------

					List<User> userList = null;

					try {
						userList = TtSystemDao.getUsers(currentUser.getCompany().getId());
					} catch (Exception e) {

						e.printStackTrace();
					}
					Map<Integer, String> userOptionList = new HashMap<Integer, String>();

					for (User users : userList) {

						userOptionList.put(users.getId(), users.getFirstname());
					}

					model.put("userList", userOptionList);
					// ------------------------------------------------------------------------

					model.put("ticketsave", tt);

					// ModelAndView view = new ModelAndView();
					view.setViewName("ttSystem/troubleTicket2");

				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		} // end of else

		return view;
	}

	@RequestMapping(value = "/saveTticket", method = RequestMethod.POST)
	public ModelAndView savetticket(HttpServletRequest request, @Valid @ModelAttribute("ticketsave") Tt tt,
			BindingResult result, Map<String, Object> model) {

		MyDateUtil date = new MyDateUtil();
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {
			try {

				tt.setRegtime(date.getDateNow());
				tt = TtSystemDao.saveOrUpdate(tt);// save trouble ticket
				// ---------------------------send email to current owner in
				// transfer-----------------------------------

				if (tt.getStatus() == 2) {
					User user = userDaoimpl.getUserByID(tt.getUser().getId());
					String recipient = user.getEmail();
					String subject = "new trouble ticket";
					String message = tt.getComment();
					MailService mailservice = new MailService();
					mailservice.doSendEmail(recipient, subject, message);
				} else {
					System.out.println("not a transfer");
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
			ModelAndView view = new ModelAndView();

			view.setViewName("ttSystem/index");

			return view;
		}
	}
	// -------------------------------tticket
	// end----------------------------------------------------------

	// -------------------------------create drop
	// down------------------------------------------------------

	@RequestMapping(value = "/getArray", method = RequestMethod.GET)
	public ModelAndView getArray(HttpServletRequest request, @ModelAttribute("value") String value,
			BindingResult result, Map<String, Object> model) {

		System.out.println("open trouble ticket success" + value);
		Tt tt = new Tt();
		TtUpdate ttupdate = new TtUpdate();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		// User user=null;
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");// if session fail

			return view;
		} else {
			int roll = currentUser.getRolleid();
			ModelAndView view = new ModelAndView();

			// ---------------------------save trouble
			// ticket-------------------------------------------------------
			System.out.println("1st Test----------------------");
			MyDateUtil date = new MyDateUtil();

			try {

				User user = userDaoimpl.getUserByID(currentUser.getUser());

				tt.setUser(user);// set user to trouble ticket
				ttupdate.setUser(user);// set user to trouble ticket update

			} catch (Exception e2) {
				System.out.println("error in user object....");
				e2.printStackTrace();

			}
			tt.setRegtime(date.getDateNow());// set reg time to trouble ticket
			ttupdate.setRegtime(date.getDateNow());// set regtime to trouble
													// ticket update

			try {
				tt = TtSystemDao.saveOrUpdate(tt);// update tt
			} catch (Exception e) {

				e.printStackTrace();

			}

			ttupdate.setStatus(1);
			ttupdate.setTt(tt);
			// ttupdate.setComment("First time open the trouble ticket");
			ttupdate = TtSystemDao.saveOrUpdate(ttupdate);// run update(insert
															// to tt update
															// table)
			// ------------------------------------------------------------------------------------------------------

			String[] arr = value.replaceFirst("^,", "").split(",");// get
																	// selected
																	// alarm ids
																	// to int
																	// array
			int[] array = new int[arr.length];

			for (int i = 0; i < arr.length; i++) {
				array[i] = Integer.parseInt(arr[i]);

				// get a alrm object from its id
				try {
					Alarm alarm = TtSystemDao.getAlarmById(array[i]);
					// sys = alarm.getSystems();
					alarm.setTt(tt);
					alarm.setStatus(2);

					// save the alarm
					alarm = TtSystemDao.saveOrUpdate(alarm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			System.out.println("show back trouble ticket");

			// -----------------------------System drop down---------------

			List<Systems> systemList = null;

			try {
				if (roll == 19) {
					systemList = TtSystemDao.getSystem();
				} else {
					systemList = TtSystemDao.getSystem(currentUser.getCompany().getId());

				}

			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> systemOptionList = new HashMap<Integer, String>();

			for (Systems system : systemList) {

				systemOptionList.put(system.getId(), system.getName());
			}

			model.put("systemList", systemOptionList);
			// --------------------------------------------------------------------

			// --------------------------------Company Drop
			// down--------------------
			List<Company> companyList = null;

			try {
				if (roll == 19) {
					// all companies
					companyList = TtSystemDao.getCompany();
				} else {
					// his company
					companyList = TtSystemDao.getCompanyForUser(currentUser.getCompany().getId());

				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> companyOptionList = new HashMap<Integer, String>();

			for (Company company : companyList) {

				companyOptionList.put(company.getId(), company.getName());
			}

			model.put("companyList", companyOptionList);

			// -------------------------------------------------------------------------

			// ----------------------------department drop
			// down-------------------------
			List<Department> departmentList = null;

			try {
				departmentList = TtSystemDao.getDepartment(currentUser.getCompany().getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> departmentOptionList = new HashMap<Integer, String>();

			for (Department department : departmentList) {

				departmentOptionList.put(department.getId(), department.getName());
			}

			model.put("departmentList", departmentOptionList);

			// -------------------------------------------------------------------------

			// --------------------------user
			// list----------------------------------

			List<User> userList = null;

			try {
				userList = TtSystemDao.getUsers(currentUser.getCompany().getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> userOptionList = new HashMap<Integer, String>();

			for (User users : userList) {

				userOptionList.put(users.getId(), users.getFirstname());
			}

			model.put("userList", userOptionList);
			// ------------------------------------------------------------------------

			model.put("ticketsave", tt);

			view.setViewName("ttSystem/open_tt");

			return view;

		}

	}

	// -----------------------------------show trouble ticket
	// table-----------------------------------
	@RequestMapping(value = "/getTt", method = RequestMethod.GET)
	public ModelAndView showtroubleticketTable(HttpServletRequest request, Map<String, Object> model) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");// if session fail

			return view;
		} else {
			int rollId = currentUser.getRolleid();
			ModelAndView view = new ModelAndView();
			view.setViewName("ttSystem/tt_update");
			HtmlGenerator html = new HtmlGenerator();
			String table = "";
			// get the result set
			try {
				List<UserHasDepartment> userhasdepartmentList = TtSystemDao.getDepartmentByUser(currentUser.getUser());
				List<Tt> list = null;

				int size = 15;
				int param = 0;
				List<Integer> departmentId = new ArrayList<>();
				for (UserHasDepartment departmentList : userhasdepartmentList) {
					departmentId.add(departmentList.getDepartment().getId());
				}

				if (rollId == 19) {// super NOC
					// show all trouble tickets
					list = TtSystemDao.searchTtForPagination(size, param);

				} else if (rollId == 22) {// NOC
					// show all trouble tickets of the company
					int companyId = currentUser.getCompany().getId();
					list = TtSystemDao.searchTtForPagination(companyId, size, param);

				} else {
					list = TtSystemDao.searchTtForPagination(departmentId, size, param);// getTtByDepartment(departmentId);

				}
				// list = TtSystemDao.getTtByDepartment(departmentId);
				table = html.generateTroubleTicketTable(list);
				view.addObject("table", table);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return view;
		}

	}
	// ------------------------------------------------------------------------------------------------

	// --------------------------------------create
	// update---------------------------------------------

	@RequestMapping(value = "/getTtUpdate", method = RequestMethod.GET)
	public ModelAndView getTtUpdate(HttpServletRequest request, @ModelAttribute("value") String value,
			BindingResult result, Map<String, Object> model) {
		System.out.println("trouble ticket id------>" + value);
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");// if session fail

			return view;
		} else {
			int id = Integer.parseInt(value);
			Tt tt = new Tt();
			tt = TtSystemDao.getTroubleTicketById(id);
			// --------------------------user
			// list----------------------------------

			List<User> userList = null;

			try {
				userList = TtSystemDao.getUsers(currentUser.getCompany().getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> userOptionList = new HashMap<Integer, String>();

			for (User users : userList) {

				userOptionList.put(users.getId(), users.getFirstname());
			}

			model.put("userList", userOptionList);
			// ------------------------------------------------------------------------

			// }
			ModelAndView view = new ModelAndView();
			// model.put("UpdateTt", tt);
			model.put("UpdateTt", tt);
			view.setViewName("ttSystem/troubleticketupdate");
			// view.setViewName("ttSystem/dashboard");
			return view;

		}

		// ------------------------------------------------------------------------------------------------

	}

	// -----------------------------update
	// owner----------------------------------------------------------
	@RequestMapping(value = "/saveUpTt", method = RequestMethod.POST)
	public ModelAndView saveUpdateTt(@ModelAttribute("UpdateTt") Tt tt, HttpServletRequest request) {
		System.out.println("--------->update owner");
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		// User user=null;
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");// if session fail

			return view;
		} else {
			ModelAndView view = new ModelAndView();
			MyDateUtil date = new MyDateUtil();

			tt.setRegtime(date.getDateNow());

			try {
				tt = TtSystemDao.saveOrUpdate(tt);

				if (tt.getStatus() == 2) {

					User user = userDaoimpl.getUserByID(tt.getUser().getId());
					String recipient = user.getEmail();
					String subject = "new trouble ticket";
					String message = tt.getComment();
					MailService mailservice = new MailService();
					mailservice.doSendEmail(recipient, subject, message);
				} else {
					System.out.println("not a transfer");
				}
			} catch (Exception e) {
				System.out.println("error ttsystem controller saveorupdate------------");
				e.printStackTrace();
				System.out.println("error : " + e);
			}

			if (tt.getId() != null) {
				System.out.println("-------->run update");

			} else {

				System.out.println("------->no update");
			}

			// ModelAndView view = new ModelAndView();

			view.setViewName("ttSystem/index");

			return view;
		}

	}
	// --------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/ttUpdate", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String Ttupdate(@ModelAttribute("comment") String comment,
			@ModelAttribute("status") int status, @ModelAttribute("info") String info, @ModelAttribute("ttid") int ttid,
			HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		// User user=null;

		MyDateUtil date = new MyDateUtil();

		// SysUser currentUser = (SysUser)
		// request.getSession().getAttribute("user");
		User user = null;
		try {
			user = userDaoimpl.getUserByID(currentUser.getUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TtUpdate ttupdate = new TtUpdate();

		ttupdate.setUser(user);
		ttupdate.setComment(comment);
		ttupdate.setInfo(info);
		ttupdate.setRegtime(date.getDateNow());
		ttupdate.setTt(TtSystemDao.getTroubleTicketById(ttid));
		ttupdate.setStatus(status);
		ttupdate = TtSystemDao.saveOrUpdate(ttupdate);
		return "Successfully saved";

	}

	// ----------------------------------------getAlarmCount---------------------------------------

	@RequestMapping(value = "/getalarmCount", method = RequestMethod.GET)
	public @ResponseBody int alarmCount(HttpServletRequest request) {

		int alarm = TtSystemDao.getAlarmCount();

		return alarm;

	}

	// --------------------------------------------------------------------------------------------

	// --------------------------create side
	// bar---------------------------------------------------

	@RequestMapping(value = "/showSideBar", method = RequestMethod.GET)
	public @ResponseBody String showSideBar(HttpServletRequest request, Map<String, Object> model) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		List<RollHasActivities> activity = TtSystemDao.getActivitiesByRoll(currentUser.getRolleid());
		List<Integer> activityId = new ArrayList<>();

		for (RollHasActivities activityList : activity) {

			activityId.add(activityList.getActivities().getId());
		}

		HtmlGenerator html = new HtmlGenerator();
		String a = "";
		String menuString = "";
		JSONArray jsonarry = new JSONArray();
		try {
			List<Activities> list = TtSystemDao.getActivities(activityId);

			for (Activities activities : list) {

				JSONObject json = new JSONObject();

				json.put("actid", activities.getId());
				json.put("url", activities.getUrl());
				json.put("actname", activities.getActivitiy());

				jsonarry.put(json);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonarry.toString();

	}

	// ----------------------------------signout---------------------------------------------------

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ModelAndView signout(HttpServletRequest request, Map<String, Object> model) {

		ModelAndView view = new ModelAndView();

		view.setViewName("login?logout");

		return view;
	}

	// ----------------------------- pagination
	// -----------------------------------------------------

	@RequestMapping(value = "/GetAlarmsWithPagination", method = RequestMethod.GET)
	public @ResponseBody String GetAlarmTableForAdayWithPagination(@ModelAttribute("size") int size,
			@ModelAttribute("param") int param, HttpServletRequest request) throws ExceptionService {

		ModelAndView model = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		// User user=null;
		if (currentUser == null) {
			// ModelAndView view = new ModelAndView();

			model.setViewName("login?logout");// if session fail

			return "Please re-login to the system";
		} else {

			HtmlGenerator html = new HtmlGenerator();
			String table = "";

			List<Systems> systemList;
			try {
				systemList = TtSystemDao.getSystemByCompany(currentUser.getCompany().getId());
				// get the result set

				////////////////////////////////// List<Alarm> list =
				////////////////////////////////// TtSystemDao.getAlarms();
				List<Alarm> list = TtSystemDao.searchAlarmForPagination(size, param, systemList);
				table = html.generateAlarmTable(list);

				// pagination

				String pagination = "<div class='pagination'><ul>"
						+ "<li class='disabled'><a href='#' onClick='loadOnClick(0)' >First</a></li>";
				int y = 0;
				int x = 0;
				for (int i = 0; i < TtSystemDao.getAlarmsListForCompany(systemList).size(); i++) {

					if (i % 15 == 0) { // 20 records per page
						y = y + 1;
						x = i - 1;
						pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x + ")' >"
								+ y + "</a></li>";
					}

				}
				pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
						+ ")' >Last</a></li>" + "</ul></div>";

				model.addObject("pagination", pagination);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return table;

		}

	}

	@RequestMapping(value = "/GetTtWithPagination", method = RequestMethod.GET)
	public @ResponseBody String GetTtTableWithPagination(@ModelAttribute("size") int size,
			@ModelAttribute("param") int param, HttpServletRequest request) throws ExceptionService {

		ModelAndView model = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		// User user=null;
		if (currentUser == null) {
			// ModelAndView view = new ModelAndView();

			model.setViewName("login?logout");// if session fail

			return "Please re-login to the system";
		} else {
			int rollId = currentUser.getRolleid();
			// ModelAndView view = new ModelAndView();
			// view.setViewName("ttSystem/tt_update");
			HtmlGenerator html = new HtmlGenerator();
			String table = "";
			// get the result set
			try {
				List<UserHasDepartment> userhasdepartmentList = TtSystemDao.getDepartmentByUser(currentUser.getUser());
				List<Tt> list = null;

				// int size1=20;
				// int param1=0;
				List<Integer> departmentId = new ArrayList<>();
				for (UserHasDepartment departmentList : userhasdepartmentList) {
					departmentId.add(departmentList.getDepartment().getId());
				}

				// System.out.println("pagination test");

				if (rollId == 19) {// super NOC
					// show all trouble tickets

					list = TtSystemDao.searchTtForPagination(size, param);
					// xxxxxxxxxxxxxxxxxxxxxxxxxx
					// paginationxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
					String pagination = "<div class='pagination'><ul>"
							+ "<li class='disabled'><a href='#' onClick='loadOnClick(1)' >First</a></li>";
					int y = 0;
					int x = 0;
					for (int i = 0; i < TtSystemDao.getTroubleTicket().size(); i++) {

						if (i % 15 == 0) { // 20 records per page
							y = y + 1;
							x = i - 1;
							pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
									+ ")' >" + y + "</a></li>";
						}

					}
					pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
							+ ")' >Last</a></li>" + "</ul></div>";

					model.addObject("pagination", pagination);
					// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

				} else if (rollId == 22) {// NOC
					// show all trouble tickets of the company
					int companyId = currentUser.getCompany().getId();
					// list=TtSystemDao.getTtByCompany(companyId);
					list = TtSystemDao.searchTtForPagination(companyId, size, param);
					// xxxxxxxxxxxxxxxxxxxxxxxxxx
					// paginationxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
					String pagination = "<div class='pagination'><ul>"
							+ "<li class='disabled'><a href='#' onClick='loadOnClick(1)' >First</a></li>";
					int y = 0;
					int x = 0;
					for (int i = 0; i < TtSystemDao.getTtByCompany(companyId).size(); i++) {

						if (i % 15 == 0) { // 20 records per page
							y = y + 1;
							x = i - 1;
							pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
									+ ")' >" + y + "</a></li>";
						}

					}
					pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
							+ ")' >Last</a></li>" + "</ul></div>";

					model.addObject("pagination", pagination);
				} else {
					list = TtSystemDao.searchTtForPagination(departmentId, size, param);// getTtByDepartment(departmentId);

					String pagination = "<div class='pagination'><ul>"
							+ "<li class='disabled'><a href='#' onClick='loadOnClick(1)' >First</a></li>";
					int y = 0;
					int x = 0;
					for (int i = 0; i < TtSystemDao.getTtByDepartment(departmentId).size(); i++) {

						if (i % 15 == 0) { // 20 records per page
							y = y + 1;
							x = i - 1;
							pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
									+ ")' >" + y + "</a></li>";
						}

					}
					pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
							+ ")' >Last</a></li>" + "</ul></div>";

					model.addObject("pagination", pagination);
				}
				// list = TtSystemDao.getTtByDepartment(departmentId);
				table = html.generateTroubleTicketTable(list);
				// view.addObject("table", table);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return table;
		}

	}
	// -------------------------------------------- show Company Registration
	// --------------------------------------------------

	@RequestMapping(value = "/companyRegistration", method = RequestMethod.GET)
	public ModelAndView companyRegistration(HttpServletRequest request, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {

			// System.out.println("-------->show troubleticket");
			ModelAndView view = new ModelAndView();

			Company company = new Company();
			model.put("company", company);
			view.setViewName("ttSystem/companyRegistration");

			return view;
		}

	}

	@RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
	public ModelAndView saveCompany(HttpServletRequest request, @Valid @ModelAttribute("company") Company company,
			BindingResult result, Map<String, Object> model) {

		ModelAndView view = new ModelAndView();

		try {
			company.setStatus(1);
			company = TtSystemDao.saveOrUpdate(company);
			view.setViewName("ttSystem/index");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;

	}

	// ----------------------------------system
	// registration-----------------------------------------------------------
	@RequestMapping(value = "/systemRegistration", method = RequestMethod.GET)
	public ModelAndView systemRegistration(HttpServletRequest request, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {
			ModelAndView view = new ModelAndView();

			// --------------------------------Company Drop
			// down--------------------
			List<Company> companyList = null;

			try {
				companyList = TtSystemDao.getCompany();
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> companyOptionList = new HashMap<Integer, String>();

			companyOptionList.put(0, "Select Company");
			for (Company company : companyList) {

				companyOptionList.put(company.getId(), company.getName());
			}

			model.put("companyList", companyOptionList);

			List<Brand> brandlist = null;

			// getBrand

			try {
				brandlist = TtSystemDao.getBrand();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Map<Integer, String> brandOptionList = new HashMap<Integer, String>();

			for (Brand brand : brandlist) {
				brandOptionList.put(brand.getId(), brand.getName());
			}

			model.put("brandList", brandOptionList);

			// -------------------------------------------------------------------------

			Systems systems = new Systems();
			model.put("systems", systems);
			view.setViewName("ttSystem/systemRegistration");

			return view;
		}

	}

	@RequestMapping(value = "/saveSystems", method = RequestMethod.POST)
	public ModelAndView saveSystems(HttpServletRequest request, @Valid @ModelAttribute("systems") Systems systems,
			BindingResult result, Map<String, Object> model) {

		ModelAndView view = new ModelAndView();
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		try {
			systems.setStatus(1);
			systems.setCompany(currentUser.getCompany());
			systems = TtSystemDao.saveOrUpdate(systems);
			view.setViewName("ttSystem/index");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	// ----------------------------create
	// dropdown---------------------------------------------

	@RequestMapping(value = "/getValuesForSystemDropdowns", method = RequestMethod.GET)
	public @ResponseBody String getValuesForSystemDropdowns(HttpServletRequest request,
			@ModelAttribute("value") String value, BindingResult result, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		String str = value.replaceAll("\\D+", "");
		int id = Integer.parseInt(str);

		List<Systems> systemList = null;

		String option = "";

		HtmlGenerator html = new HtmlGenerator();

		try {
			// id=Integer.parseInt(str);
			Integer number = Integer.valueOf(id);

			// show system according to company
			systemList = TtSystemDao.getSystem(id);
			option = html.generateOptionList(systemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;

	}

	@RequestMapping(value = "/getValuesForUserDropdowns", method = RequestMethod.GET)
	public @ResponseBody String getValuesForUserDropdown(HttpServletRequest request,
			@ModelAttribute("value") String value, BindingResult result, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		String str = value.replaceAll("\\D+", "");
		int id = Integer.parseInt(str);

		List<User> userList = null;
		String option = "";
		try {
			userList = TtSystemDao.getUsers(id);

			HtmlGenerator html = new HtmlGenerator();

			option = html.generateUserList(userList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		/////////////////////////////////////////////////////////

		return option;

	}

	@RequestMapping(value = "/getValuesForDepartmentDropdowns", method = RequestMethod.GET)
	public @ResponseBody String getValuesForDepartmentDropdown(HttpServletRequest request,
			@ModelAttribute("value") String value, BindingResult result, Map<String, Object> model) {
		// SysUser currentUser = (SysUser)
		// request.getSession().getAttribute("user");

		String str = value.replaceAll("\\D+", "");
		int id = Integer.parseInt(str);

		List<Department> departmentList = null;
		String option = "";

		try {
			departmentList = TtSystemDao.getDepartment(id);
			HtmlGenerator html = new HtmlGenerator();

			option = html.generateDepartmentList(departmentList);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return option;

	}

	// ------------------------------------------ user Registration
	// --------------------------------------

	@RequestMapping(value = "/userRegistration", method = RequestMethod.GET)
	public ModelAndView userRegistration(HttpServletRequest request, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");

			return view;

		} else {

			// System.out.println("-------->show troubleticket");
			ModelAndView view = new ModelAndView();


			// --------------------------------Company Drop
			// down--------------------
			List<Company> companyList = null;

			try {
				companyList = TtSystemDao.getCompany();
			} catch (Exception e) {

				e.printStackTrace();
			}
			Map<Integer, String> companyOptionList = new HashMap<Integer, String>();
			
			companyOptionList.put(0, "Select Company");
			for (Company company : companyList) {
				companyOptionList.put(company.getId(), company.getName());
			}

			model.put("companyList", companyOptionList);

			// -------------------------------------------------------------------------
			view.setViewName("ttSystem/userRegistration");

			return view;
		}

	}

	@RequestMapping(value = "/UserReg", method = RequestMethod.GET)
	public @ResponseBody String UserReg(HttpServletRequest request, @ModelAttribute("comid") String comid,
			@ModelAttribute("title") String title, @ModelAttribute("initials") String initials,
			@ModelAttribute("fname") String fname, @ModelAttribute("lname") String lname,
			@ModelAttribute("dob") String dob, @ModelAttribute("add1") String add1, @ModelAttribute("add2") String add2,
			@ModelAttribute("add3") String add3, @ModelAttribute("nic") String nic,
			@ModelAttribute("mobile") String mobile, @ModelAttribute("email") String email,@ModelAttribute("gender") String gender) {
		
		String returnString = "error";

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser == null) {
			returnString = "logout";

		} else {
			try {
				Company company = new Company();
				PersonType persontype = new PersonType();
				User user = new User();
				
				MyDateUtil date = new MyDateUtil();
				
				company = TtSystemDao.getCompanyById(Integer.parseInt(comid));
				persontype.setId(6);

				
				user.setCompany(company);
				user.setPersonType(persontype);
				user.setTitle(title);
				user.setInitials(initials);
				user.setFirstname(fname);
				user.setLastname(lname);
				user.setAddressLine1(add1);
				user.setAddressLine2(add2);
				user.setAddressLine3(add3);
				user.setNic(nic);
				user.setMobile(mobile);
				user.setEmail(email);
				user.setGender(gender);
				user.setStatus(1);
				user.setRegtime(date.getDateNow());

				
				if (dob.equals(null) || dob.equals("") || dob == null || dob == "") {

				} else {
					SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
					Date birthdate = formatter.parse(dob);
					user.setBirthdate(birthdate);
				}
				
				user = TtSystemDao.saveOrUpdate(user);
				
				if(user.getId()!=0) {
					returnString = "save";
				}else {
					returnString = "not save";
				}
				

			} catch (Exception ex) {
				ex.printStackTrace();
				
				returnString = "error";
			}

		}

		return returnString;
	}


	// ------------------------------update user has
	// department------------------------------------
	@RequestMapping(value = "/updateUserHasDepartment", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String updateUserHasDepartment(@ModelAttribute("department") int department,
			@ModelAttribute("company") int company, HttpServletRequest request) {
		System.out.println("department#########" + department);
		System.out.println("company############" + company);

		try {
			UserHasDepartment userhasdepartment = new UserHasDepartment();
			List<Integer> id = TtSystemDao.getMaxVal();
			int uid = 0;
			int did = 0;
			for (int userId : id) {

				uid = userId;
				System.out.println("User id#########" + uid);

			}

			User person = TtSystemDao.getUserById(uid);
			Department dpart = TtSystemDao.getDepartmentById(department);
			System.out.println("person#########" + person);
			userhasdepartment.setUser(person);
			userhasdepartment.setDepartment(dpart);
			userhasdepartment.setStatus(1);

			userhasdepartment = TtSystemDao.saveOrUpdate(userhasdepartment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Update successfull";
	}

	// show main dashboard
	@RequestMapping(value = "/showDboard", method = RequestMethod.GET)
	public ModelAndView showDboard(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser == null) {
			System.out.println("fail..return login");
			view.setViewName("login");
		} else {
			view.setViewName("ttSystem/maindashboard");
		}
		return view;
	}

	// load main dashboard data
	@RequestMapping(value = "/loaddashboarddata", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String loadDashboardData(HttpServletRequest request) {

		System.out.println("##################### into the load dashboard data");
		ModelAndView view = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String com = null;
		if (currentUser == null) {
			System.out.println("fail..return login");
			com = "session out";
		} else {
			try {
				List<Systems> systemlst = null;
				HtmlGenerator generator = new HtmlGenerator();

				if (currentUser.getRolleid() == 19) {
					systemlst = TtSystemDao.getSystem();
				} else {
					systemlst = TtSystemDao.getSystem(currentUser.getCompany().getId());
				}

				JSONArray jsonarry = new JSONArray();
				try {

					for (Systems systems : systemlst) {

						JSONObject json = new JSONObject();

						json.put("sysid", systems.getId());
						json.put("sysurl", systems.getUrl());
						json.put("sysname", systems.getName());
						json.put("syslon", systems.getLon());
						json.put("syslat", systems.getLat());
						jsonarry.put(json);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				com = jsonarry.toString();
				System.out.println("load dashboard");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return com;
	}

	@RequestMapping(value = "/logoutfunct", method = RequestMethod.GET)
	public ModelAndView logoutfunct(HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		auth.setAuthenticated(false);

		Company com = new Company();
		com.setId(0);

		currentUser.setCompany(com);
		currentUser.setCompanyID(0);
		currentUser.setId(0);
		currentUser.setUsername(null);
		HttpSession session = request.getSession(false);
		session.invalidate();
		System.out.println("############################################### log clean");
		ModelAndView view = new ModelAndView();
		view.setViewName("login");
		return view;
	}

	// dashboard refresh using time countdown
	@RequestMapping(value = "/dashboardrefresh", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String dashboardrefresh(HttpServletRequest request) {

		String passString = "";
		int systemcount = 0;
		int systemid = 0;

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		if (currentUser.getUsername() == null) {
			System.out.println("fail..return login");
			passString = "logout";
		} else {
			System.out.println("%%%%%%%%%%sysuser :" + currentUser.getName());
			try {
				List<Systems> systemlst = null;

				if (currentUser.getRolleid() == 19) {
					systemlst = TtSystemDao.getSystem();
					// systemlst = TtSystemDao.getSystemall();
				} else {
					systemlst = TtSystemDao.getSystem(currentUser.getCompany().getId());
				}

				for (Systems systems : systemlst) {
					systemcount = systemcount + 1;
					systemid = systems.getId();
					int countclearalrm = TtSystemDao.getAlarmClearcount(systems.getId());
					int countCritical = TtSystemDao.getAlarmcount(systems.getId(), 1);
					int countMajor = TtSystemDao.getAlarmcount(systems.getId(), 2);
					int countMinor = TtSystemDao.getAlarmcount(systems.getId(), 3);
					passString = passString + systemid + "," + countclearalrm + "," + countCritical + "," + countMajor
							+ "," + countMinor + ",";
				}
				passString = systemcount + "," + passString;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return passString;
	}

	// show alarm window from main dashboard
	@RequestMapping(value = "/alarmwindow", method = RequestMethod.GET)
	public ModelAndView showAlarmwindow(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser == null) {
			System.out.println("fail..return login");
			view.setViewName("login");
		} else {
			view.setViewName("ttSystem/alarmListTable");
		}
		return view;
	}

	// get data for loadalarmforsysid
	@RequestMapping(value = "/loadalarmforsysid", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String loadalarmforsysid(HttpServletRequest request, @ModelAttribute("id") int id) {
		String passString = "";
		int systemcount = 0;
		int systemid = 0;

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		try {
			List<Alarm> alarmlst = TtSystemDao.getAlarmsListForBysysid(id);

			HtmlGenerator generator = new HtmlGenerator();
			passString = generator.generateAlarmtblforsysit(alarmlst);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return passString;
	}

	@RequestMapping(value = "/heartbeatblink", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String heartbeatblink(HttpServletRequest request, @ModelAttribute("sec") String sec) {
		String passString = "";
		int systemcount = 0;
		int systemid = 0;
		int status = 0; // 0 = deactive ,1 = active

		long gap = Long.parseLong(sec); // = seconds ex 30000 milisec = 30 sec

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser.getUsername() == null) {
			passString = "logout";
		} else {

			try {

				List<Systems> systemlst = null;

				if (currentUser.getRolleid() == 19) {
					systemlst = TtSystemDao.getSystem();
				} else {
					systemlst = TtSystemDao.getSystem(currentUser.getCompany().getId());
				}

				for (Systems systems : systemlst) {
					systemcount = systemcount + 1;
					systemid = systems.getId();
					Heartbeat hb = TtSystemDao.getlastHbeat(systems.getId());

					Calendar calendar = Calendar.getInstance();
					Date date1 = calendar.getTime();

					Date d1, d3;

					d1 = date1;

					d3 = hb.getRegtime();

					long difference = d1.getTime() - d3.getTime();

					if (difference <= gap) {
						status = 1;
					} else {
						status = 0;
					}
					passString = passString + systemid + "," + status + ",";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return systemcount + "," + passString;
	}

	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	public @ResponseBody String getusers(HttpServletRequest request, @ModelAttribute("companyid") int id,
			Map<String, Object> model) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		JSONArray jsonarry = new JSONArray();
		try {
			List<User> list = null;
			list = TtSystemDao.getUsers(id);

			for (User user : list) {

				JSONObject json = new JSONObject();

				json.put("uid", user.getId());
				json.put("fname", user.getFirstname());
				json.put("lname", user.getLastname());

				jsonarry.put(json);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonarry.toString();

	}

	@RequestMapping(value = "/saveSystemwithuser", method = RequestMethod.GET)
	public @ResponseBody String saveSystemwithuser(HttpServletRequest request, @ModelAttribute("sysid") String sysid,
			@ModelAttribute("sysname") String sysname, @ModelAttribute("sysno") String sysno,
			@ModelAttribute("sysbrand") String sysbrand, @ModelAttribute("sysmodel") String sysmodel,
			@ModelAttribute("configtype") String configtype, @ModelAttribute("company") String company,
			@ModelAttribute("branch") String branch, @ModelAttribute("lon") String lon,
			@ModelAttribute("lat") String lat, @ModelAttribute("url") String url, @ModelAttribute("ip") String ip,
			@ModelAttribute("details") String details, @ModelAttribute("users") String users) {
			
			String returnstring = "error";
			SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser.getUsername() == null) {
			returnstring = "logout";
		} else {

			int id = Integer.parseInt(sysid);
			Systems systems = new Systems();

			// new system reg
			try {
				// get brand by id
				Brand brand = null;
				brand = TtSystemDao.getBrandbyid(Integer.parseInt(sysbrand));

				// proccess config type
				if (configtype.equals("1")) {
					configtype = "DynDNS";
				} else if (configtype.equals("2")) {
					configtype = "Watchdog";
				}
				// get company by id
				Company companyobj = null;
				companyobj = TtSystemDao.getCompanyById(Integer.parseInt(company));

				// get branch by id
				Branch branchobj = null;
				branchobj = TtSystemDao.getBranchbyid(Integer.parseInt(branch));

				MyDateUtil date = new MyDateUtil();

				// for system update
				if (id != 0) {
					System.out.println("%%%%%%%%%%%%%update");
					systems.setId(id);
					returnstring = "update:" + systems.getId();
				} else {
					System.out.println("%%%%%%%%%%%%%save");
					returnstring = "save";
				}

				systems.setStatus(1);
				systems.setSystemNo(sysno);
				systems.setBrand(brand);
				systems.setModel(sysmodel);
				systems.setConfigType(configtype);
				systems.setCompany(companyobj);
				systems.setBranch(branchobj);
				// systems.setRegtime(date.getDateNow());
				systems.setDescription(details);
				systems.setName(sysname);
				systems.setLon(lon);
				systems.setLat(lat);
				systems.setUrl(url);
				systems.setIp(ip);
				systems = TtSystemDao.saveOrUpdate(systems);

				if (returnstring == "save") {
					returnstring = "save:" + systems.getId();
				}

				if (id != 0) {

					if (users == "null" || users.equals("null")) {
						// remove all user
						List<SystemsHasUser> systemuser = null;
						systemuser = TtSystemDao.getAllSystemsHasUserbySystem(systems);
						for (SystemsHasUser syshasusers : systemuser) {
							syshasusers.setStatus(2);
							TtSystemDao.saveOrUpdate(syshasusers);
						}

					} else {
						System.out.println("$$$$$ line 1876");

						String[] userids = users.split(",");

						for (int i = 0; i < userids.length; i++) {
							System.out.println("$$$$$ line 1881");
							User userobj = TtSystemDao.getUser(Integer.parseInt(userids[i]));
							int getcount = TtSystemDao.getSystemsHasUserbySystemanUser(systems, userobj);

							if (getcount == 0) {
								System.out.println("$$$$$ line 1886");
								SystemsHasUser sysusers = new SystemsHasUser();
								sysusers.setSystems(systems);
								sysusers.setUser(userobj);
								sysusers.setStatus(2);

								TtSystemDao.saveOrUpdate(sysusers);

							}

						}
						List<SystemsHasUser> systemuser = null;
						systemuser = TtSystemDao.getAllSystemsHasUserbySystem(systems);
						System.out.println("$$$$$ line 1899");
						for (SystemsHasUser syshasusers : systemuser) {
							System.out.println("$$$$$ line 1901");
							syshasusers.setStatus(2);
							TtSystemDao.saveOrUpdate(syshasusers);
						}

						List<SystemsHasUser> systemuserforactive = null;
						systemuserforactive = TtSystemDao.getAllSystemsHasUserbySystem(systems);
						System.out.println("$$$$$ line 1908");
						for (SystemsHasUser syshasusers : systemuserforactive) {
							System.out.println("$$$$$ line 1910");
							for (int i = 0; i < userids.length; i++) {
								System.out.println("$$$$$ line 1912");

								int getuid = syshasusers.getUser().getId();
								int stringuid = Integer.parseInt(userids[i]);
								if (getuid == stringuid) {
									System.out.println("$$$$$ line 1917");
									syshasusers.setStatus(1);
									TtSystemDao.saveOrUpdate(syshasusers);
								}

							}

						}

					}

				} else {
					if (systems.getId() != 0) {

						if (users != "null" || users.equals("null")) {
							System.out.println("########### 2 users " + users);
							String[] userids = users.split(",");

							for (int i = 0; i < userids.length; i++) {

								System.out.println("$$$$$$$$$$$$$$$$ id :" + userids[i]);

								int intid = Integer.parseInt(userids[i]);

								User userobj = TtSystemDao.getUser(intid);

								SystemsHasUser sysusers = new SystemsHasUser();
								sysusers.setSystems(systems);
								sysusers.setUser(userobj);
								sysusers.setStatus(1);

								TtSystemDao.saveOrUpdate(sysusers);
							}
						}
					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return returnstring;
	}

	@RequestMapping(value = "/getbranch", method = RequestMethod.GET)
	public @ResponseBody String getBranch(HttpServletRequest request, @ModelAttribute("companyid") String companyid,
			Map<String, Object> model) {

		JSONArray jsonarry = new JSONArray();
		try {
			List<Branch> list = null;
			list = TtSystemDao.getBranch(Integer.parseInt(companyid));

			for (Branch branch : list) {

				JSONObject json = new JSONObject();

				json.put("id", branch.getId());
				json.put("name", branch.getName());

				jsonarry.put(json);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonarry.toString();

	}

	@RequestMapping(value = "/loadsystemtbl", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String loadSystemTable(HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {
				List<Systems> systemList = TtSystemDao.getSystem();

				JSONArray json = new JSONArray();

				for (Systems systems : systemList) {

					JSONObject jsonobj = new JSONObject();
					jsonobj.put("id", systems.getId());
					jsonobj.put("com", systems.getCompany().getName());
					jsonobj.put("sys", systems.getName());
					jsonobj.put("sysbrand", systems.getBrand().getName());
					jsonobj.put("sysmodel", systems.getModel());
					jsonobj.put("config", systems.getConfigType());
					jsonobj.put("branch", systems.getBranch().getName());
					jsonobj.put("status", systems.getStatus());

					json.put(jsonobj);

				}

				view = json.toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	// @ModelAttribute("size") int size,
	// sysenabledisable

	@RequestMapping(value = "/sysenabledisable", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String sysenabledisable(@ModelAttribute("id") int id, @ModelAttribute("sts") int status,
			HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {

				Systems systems = null;

				systems = TtSystemDao.getSystembyId(id);

				systems.setStatus(status);

				systems = TtSystemDao.saveOrUpdate(systems);

				view = systems.getIp();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/sysdelete", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String sysdelete(@ModelAttribute("id") int id, HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {

				Systems systems = null;

				systems = TtSystemDao.getSystembyId(id);

				systems.setStatus(2); // status id == 2

				systems = TtSystemDao.saveOrUpdate(systems);

				view = systems.getIp();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/selectsysteminfoforup", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String selectsysteminfoforup(@ModelAttribute("id") int id, HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {
				Systems systems = TtSystemDao.getSystembyId(id);

				JSONArray json = new JSONArray();

				JSONObject jsonobj = new JSONObject();
				jsonobj.put("id", systems.getId());
				jsonobj.put("sys", systems.getName());
				jsonobj.put("sysno", systems.getSystemNo());
				jsonobj.put("sysbrand", systems.getBrand().getId());
				jsonobj.put("sysmodel", systems.getModel());
				jsonobj.put("configType", systems.getConfigType());
				jsonobj.put("companyId", systems.getCompany().getId());
				jsonobj.put("branch", systems.getBranch().getId());
				jsonobj.put("lon", systems.getLon());
				jsonobj.put("lat", systems.getLat());
				jsonobj.put("url", systems.getUrl());
				jsonobj.put("ip", systems.getIp());
				jsonobj.put("details", systems.getDescription());

				List<SystemsHasUser> systemshasuserlist = null;
				systemshasuserlist = TtSystemDao.getSystemsHasUserbySystem(systems);
				String idlist = "";
				for (SystemsHasUser sysuser : systemshasuserlist) {
					idlist += sysuser.getUser().getId() + ",";
				}

				jsonobj.put("users", idlist);

				json.put(jsonobj);

				view = json.toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/saveBrand", method = RequestMethod.GET)
	public @ResponseBody String saveBrand(HttpServletRequest request, @ModelAttribute("id") String bid,
			@ModelAttribute("name") String name, @ModelAttribute("number") String number,
			@ModelAttribute("comment") String comment) {
		String returnstring = "error";
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser.getUsername() == null) {
			returnstring = "logout";
		} else {
			int id = Integer.parseInt(bid);
			try {
				Brand brand = new Brand();

				MyDateUtil date = new MyDateUtil();

				if (id != 0) {
					brand.setId(id);
					returnstring = "update";
				} else {
					returnstring = "save";
				}

				brand.setName(name);
				brand.setBrandNumber(number);
				brand.setDescription(comment);
				brand.setRegtime(date.getDateNow());
				brand.setStatus(1);

				brand = TtSystemDao.saveOrUpdate(brand);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return returnstring;
	}

	@RequestMapping(value = "/brandWindow", method = RequestMethod.GET)
	public ModelAndView showBrandRegwindow(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser == null) {
			System.out.println("fail..return login");
			view.setViewName("login");
		} else {
			view.setViewName("ttSystem/brandRegistration");
		}
		return view;
	}

	@RequestMapping(value = "/loginreg", method = RequestMethod.GET)
	public ModelAndView loginregWindow(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser == null) {
			System.out.println("fail..return login");
			view.setViewName("login");
		} else {
			view.setViewName("ttSystem/loginRegistration");
		}
		return view;
	}

	@RequestMapping(value = "/getcompnylist", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String getcompnylist(HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {
				List<Company> company = TtSystemDao.getCompany();

				JSONArray jsonarry = new JSONArray();

				for (Company companylst : company) {

					JSONObject json = new JSONObject();

					json.put("id", companylst.getId());
					json.put("name", companylst.getName());

					jsonarry.put(json);

				}

				view = jsonarry.toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/getUserlistByid", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String getUserlistByid(@ModelAttribute("company") String companyid,
			HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {
				List<User> user = TtSystemDao.getUsers(Integer.parseInt(companyid));

				JSONArray jsonarry = new JSONArray();

				for (User userlst : user) {

					JSONObject json = new JSONObject();

					json.put("id", userlst.getId());
					json.put("name", userlst.getFirstname() + " " + userlst.getLastname());
					jsonarry.put(json);
				}

				view = jsonarry.toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/saveLogin", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String saveLogin(@ModelAttribute("uid") String uid,
			@ModelAttribute("username") String username, @ModelAttribute("password") String password,
			HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {
				Rolls roll = new Rolls();
				roll.setId(27);

				User user = new User();
				user = TtSystemDao.getUser(Integer.parseInt(uid));

				Login login = new Login();

				login.setStatus(1);
				login.setRolls(roll);
				login.setUser(user);
				login.setUsername(username);
				login.setPassword(password);

				login = userDaoimpl.saveOrUpdate(login);

				if (login.getId() != 0) {
					view = "save";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/branchreg", method = RequestMethod.GET)
	public ModelAndView showBranchRegwindow(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		if (currentUser == null) {
			System.out.println("fail..return login");
			view.setViewName("login");
		} else {
			view.setViewName("ttSystem/branchRegistration");
		}
		return view;
	}

	@RequestMapping(value = "/saveBranch", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String saveBranch(@ModelAttribute("comid") String companyid,
			@ModelAttribute("branch") String branchname, @ModelAttribute("details") String details,
			HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {
				MyDateUtil date = new MyDateUtil();
				Company company = new Company();

				company = TtSystemDao.getCompanyById(Integer.parseInt(companyid));

				Branch branch = new Branch();

				branch.setCompany(company);
				branch.setName(branchname);
				branch.setDescription(details);
				branch.setRegtime(date.getDateNow());
				branch.setStatus(1);

				branch = TtSystemDao.saveOrUpdate(branch);

				// login = userDaoimpl.saveOrUpdate(login);

				if (branch.getId() != 0) {
					view = "save";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}

	@RequestMapping(value = "/getenabledesable", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String getenabledesable(@ModelAttribute("company") String companyid,
			HttpServletRequest request) {

		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		String view = "0";

		if (currentUser == null) {

			view = "logout";

			return view;

		} else {

			try {

				List<Systems> systemlst = new ArrayList<>();

				if (currentUser.getRolleid() == 19) {
					systemlst = TtSystemDao.getSystem();
					// systemlst = TtSystemDao.getSystemall();
				} else {
					systemlst = TtSystemDao.getSystem(currentUser.getCompany().getId());
				}

				JSONArray jsonarry = new JSONArray();

				for (Systems syslst : systemlst) {

					JSONObject json = new JSONObject();

					json.put("id", syslst.getId());
					json.put("status", syslst.getStatus());
					jsonarry.put(json);
				}

				view = jsonarry.toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		}
	}
}
