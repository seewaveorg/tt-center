package com.ngs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.ngs.dao.implementation.TtSystemDaoImpl;
import com.ngs.dao.implementation.UserDaoImpl;
import com.ngs.model.Activities;
import com.ngs.model.Alarm;
import com.ngs.model.Company;
import com.ngs.model.Department;
import com.ngs.model.PersonType;
import com.ngs.model.RollHasActivities;
import com.ngs.model.Systems;
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
				System.out.println("activitylist###################333" + list);
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
			System.out.println("@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%");

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
				System.out.println("###################company of current user " + currentUser.getCompany().getId());
				List<Systems> systemList = TtSystemDao.getSystemByCompany(currentUser.getCompany().getId());
				System.out.println("##############system list " + systemList);
				System.out.println("##############system list " + systemList.size());

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
				System.out.println("###################company of current user " + currentUser.getCompany().getId());
				List<Systems> systemList = TtSystemDao.getSystemByCompany(currentUser.getCompany().getId());
				System.out.println("##############system list " + systemList);
				System.out.println("##############system list " + systemList.size());
				// get the result set

				////////////////////////////////// List<Alarm> list =
				////////////////////////////////// TtSystemDao.getAlarms();
				int size = 15;
				int param = 0;
				List<Alarm> list = TtSystemDao.searchAlarmForPagination(size, param, systemList);// getAlarmsListForCompany(systemList);

				table = html.generateAlarmTable(list);

				// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX pagination
				// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx

				String pagination = "<div class='pagination'><ul>"
						+ "<li class='disabled'><a href='#' onClick='loadOnClick(0)' >First</a></li>";
				int y = 0;
				int x = 0;
				for (int i = 0; i < TtSystemDao.getAlarmsListForCompany(systemList).size(); i++) {

					if (i % 15 == 0) { // 20 records per page
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

		System.out.println("1st Test----------------------");
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
				System.out.println("Tt status############" + tt.getStatus());
				// ---------------------------send email to current owner in
				// transfer-----------------------------------

				if (tt.getStatus() == 2) {
					System.out.println(tt.getUser().getId());
					User user = userDaoimpl.getUserByID(tt.getUser().getId());
					String recipient = user.getEmail();
					System.out.println("Email##############" + recipient);
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
		int roll = currentUser.getRolleid();

		// User user=null;
		if (currentUser == null) {
			ModelAndView view = new ModelAndView();

			view.setViewName("login?logout");// if session fail

			return view;
		} else {

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
				System.out.println(array[i]);

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
				System.out.println("###################currentuser= " + currentUser.getUser());
				List<UserHasDepartment> userhasdepartmentList = TtSystemDao.getDepartmentByUser(currentUser.getUser());
				List<Tt> list = null;
				System.out.println("#################33userhasdepartmentlist= " + userhasdepartmentList);

				int size = 15;
				int param = 0;
				List<Integer> departmentId = new ArrayList<>();
				for (UserHasDepartment departmentList : userhasdepartmentList) {
					departmentId.add(departmentList.getDepartment().getId());
					System.out.println("#################departmentId= " + departmentId);
				}

				if (rollId == 19) {// super NOC
					// show all trouble tickets
					list = TtSystemDao.searchTtForPagination(size, param);
					// xxxxxxxxxxxxxxxxxxxxxxxxxx
					// paginationxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
					String pagination = "<div class='pagination'><ul>"
							+ "<li class='disabled'><a href='#' onClick='loadOnClick(0)' >First</a></li>";
					int y = 0;
					int x = 0;
					for (int i = 0; i < TtSystemDao.getTroubleTicket().size(); i++) {

						if (i % 15 == 0) { // 20 records per page
							y = y + 1;
							x = i;
							pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
									+ ")' >" + y + "</a></li>";
						}

					}
					pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
							+ ")' >Last</a></li>" + "</ul></div>";

					view.addObject("pagination", pagination);
					// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

				} else if (rollId == 22) {// NOC
					// show all trouble tickets of the company
					int companyId = currentUser.getCompany().getId();
					list = TtSystemDao.searchTtForPagination(companyId, size, param);

					String pagination = "<div class='pagination'><ul>"
							+ "<li class='disabled'><a href='#' onClick='loadOnClick(0)' >First</a></li>";
					int y = 0;
					int x = 0;
					for (int i = 0; i < TtSystemDao.getTtByCompany(companyId).size(); i++) {

						if (i % 15 == 0) { // 20 records per page
							y = y + 1;
							x = i;
							pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
									+ ")' >" + y + "</a></li>";
						}

					}
					pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
							+ ")' >Last</a></li>" + "</ul></div>";

					view.addObject("pagination", pagination);

				} else {
					list = TtSystemDao.searchTtForPagination(departmentId, size, param);// getTtByDepartment(departmentId);

					String pagination = "<div class='pagination'><ul>"
							+ "<li class='disabled'><a href='#' onClick='loadOnClick(0)' >First</a></li>";
					int y = 0;
					int x = 0;
					for (int i = 0; i < TtSystemDao.getTtByDepartment(departmentId).size(); i++) {

						if (i % 15 == 0) { // 20 records per page
							y = y + 1;
							x = i;
							pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
									+ ")' >" + y + "</a></li>";
						}

					}
					pagination = pagination + "<li class='active'><a href='#'  onClick='loadOnClick(" + x
							+ ")' >Last</a></li>" + "</ul></div>";

					view.addObject("pagination", pagination);
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
			System.out.println("1st Test----------------------");
			MyDateUtil date = new MyDateUtil();

			System.out.println("__________________________________");

			tt.setRegtime(date.getDateNow());

			try {
				tt = TtSystemDao.saveOrUpdate(tt);

				if (tt.getStatus() == 2) {

					System.out.println(tt.getUser().getId());
					User user = userDaoimpl.getUserByID(tt.getUser().getId());
					String recipient = user.getEmail();
					System.out.println("Email##############" + recipient);
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

		System.out.println("###########comment=" + comment);
		System.out.println("id=" + ttid);
		System.out.println("status=" + status);
		System.out.println("info=" + info);

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
		try {
			List<Activities> list = TtSystemDao.getActivities(activityId);
			System.out.println("activitylist###################333" + list);
			a = html.generateActivityList(list);
			System.out.println("aaaaaaaaaaa" + a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;

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

			System.out.println("###################company of current user " + currentUser.getCompany().getId());
			List<Systems> systemList;
			try {
				systemList = TtSystemDao.getSystemByCompany(currentUser.getCompany().getId());
				System.out.println("##############system list " + systemList);
				System.out.println("##############system list " + systemList.size());
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
				System.out.println("###################currentuser= " + currentUser.getUser());
				List<UserHasDepartment> userhasdepartmentList = TtSystemDao.getDepartmentByUser(currentUser.getUser());
				List<Tt> list = null;
				System.out.println("#################33userhasdepartmentlist= " + userhasdepartmentList);

				// int size1=20;
				// int param1=0;
				List<Integer> departmentId = new ArrayList<>();
				for (UserHasDepartment departmentList : userhasdepartmentList) {
					departmentId.add(departmentList.getDepartment().getId());
					System.out.println("#################departmentId= " + departmentId);
				}

				// System.out.println("pagination test");

				if (rollId == 19) {// super NOC
					// show all trouble tickets
					System.out.println("sizexxxxxxxxxxxxxxxxxxx" + size);
					System.out.println("paramxxxxxxxxxxxxxxxxxxx" + param);

					list = TtSystemDao.searchTtForPagination(size, param);
					System.out.println("list xxxxxxxxxxxxxxxxxxx" + list);
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
					System.out.println("list xxxxxxxxxxxxxxxxxxx" + list);
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

			for (Company company : companyList) {

				companyOptionList.put(company.getId(), company.getName());
			}

			model.put("companyList", companyOptionList);

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

		try {
			systems.setStatus(1);
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

		System.out.println("value****************" + value);
		String str = value.replaceAll("\\D+", "");
		int id = Integer.parseInt(str);

		List<Systems> systemList = null;

		String option = "";

		HtmlGenerator html = new HtmlGenerator();

		try {
			// id=Integer.parseInt(str);
			System.out.println("value****************" + value);
			System.out.println("id*******************" + id);
			Integer number = Integer.valueOf(id);
			System.out.println(number.getClass().getName());

			// show system according to company
			systemList = TtSystemDao.getSystem(id);
			option = html.generateOptionList(systemList);
			System.out.println("system list***********" + systemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("OPtion****************" + option);
		return option;

	}

	@RequestMapping(value = "/getValuesForUserDropdowns", method = RequestMethod.GET)
	public @ResponseBody String getValuesForUserDropdown(HttpServletRequest request,
			@ModelAttribute("value") String value, BindingResult result, Map<String, Object> model) {
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");

		System.out.println("value****************" + value);
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

		System.out.println("OPtion****************" + option);
		return option;

	}

	@RequestMapping(value = "/getValuesForDepartmentDropdowns", method = RequestMethod.GET)
	public @ResponseBody String getValuesForDepartmentDropdown(HttpServletRequest request,
			@ModelAttribute("value") String value, BindingResult result, Map<String, Object> model) {
		// SysUser currentUser = (SysUser)
		// request.getSession().getAttribute("user");

		System.out.println("value****************" + value);
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

		System.out.println("OPtion****************" + option);
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

			User person = new User();
			model.put("person", person);
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
			view.setViewName("ttSystem/userRegistration");

			return view;
		}

	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(HttpServletRequest request, @Valid @ModelAttribute("person") User person,
			BindingResult result, Map<String, Object> model) {

		ModelAndView view = new ModelAndView();

		try {
			PersonType persontype = new PersonType();
			// persontype.setId(6);
			person.setStatus(1);
			// user.setPersonType();
			person = TtSystemDao.saveOrUpdate(person);

			view.setViewName("ttSystem/index");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
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
			System.out.println("department#########" + dpart);
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
				System.out.println("############ company id :" + currentUser.getCompany().getId());
				System.out.println("############ uname :" + currentUser.getUsername());
				List<Systems> systemlst = TtSystemDao.getSystem(currentUser.getCompany().getId());
				HtmlGenerator generator = new HtmlGenerator();

				com = generator.generateDboardComponents(systemlst);

				System.out.println("load dashboard");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return com;

	}
	
	
	
	// dashboard refresh using time countdown
	@RequestMapping(value = "/dashboardrefresh", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody String dashboardrefresh(HttpServletRequest request) {
		String passString= "";
		int systemcount = 0;
		int systemid = 0;
		
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		
		try {
			List<Systems> systemlst = TtSystemDao.getSystem(currentUser.getCompany().getId());
			
			for (Systems systems : systemlst) {
				systemcount = systemcount +1;
				systemid = systems.getId();
				int countclearalrm = TtSystemDao.getAlarmClearcount(systems.getId());
				int countCritical = TtSystemDao.getAlarmcount(systems.getId(),1);
				int countMajor = TtSystemDao.getAlarmcount(systems.getId(),2);
				int countMinor = TtSystemDao.getAlarmcount(systems.getId(),3);
				passString = passString +systemid+","+countclearalrm+","+countCritical+","+countMajor+","+countMinor+",";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return systemcount+","+passString;
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

}
