package com.ngs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ngs.dao.implementation.LoginDaoImpl;
import com.ngs.dao.implementation.TtSystemDaoImpl;
import com.ngs.dao.implementation.UserDaoImpl;
import com.ngs.model.Alarm;
import com.ngs.model.Alarmname;
import com.ngs.model.Heartbeat;
import com.ngs.model.Systems;
import com.ngs.model.SystemsHasUser;
import com.ngs.model.Types;
import com.ngs.service.DbService;
import com.ngs.service.MailService;
import com.ngs.service.SmsService;
import com.ngs.util.FileValidator;
import com.ngs.util.MyDateUtil;

@Controller
public class APIController {

	static Logger log4j = Logger.getLogger(APIController.class);

	@Autowired
	public TtSystemDaoImpl TtSystemDao;

	@Autowired
	public UserDaoImpl userDaoimpl;
	@Autowired
	private DbService dBConnector;

	@Autowired
	private LoginDaoImpl loginDaoImpl;

	// @Autowired
	// private AuthenticationManager authenticationManager;

	@Autowired
	FileValidator fileValidator;

	// @Autowired
	// private ActivitiesDaoImpl activitDao;

	// @Autowired
	// private ActivitiesDao activitiesInterDao;

	@RequestMapping(value = "/alarm/{system}/{alarm}/{alarmid}/{time}/{type}/{seviarity}/{details}/{status}/", produces = "application/json")
	public ResponseEntity<String> setAlarm(@PathVariable("system") String system, @PathVariable("alarm") String alarm,
			@PathVariable("alarmid") String alarmid, @PathVariable("time") String time,
			@PathVariable("type") String type, @PathVariable("seviarity") String seviarity,
			@PathVariable("details") String details, @PathVariable("status") String status) {
		// public ResponseEntity<String> saveAlarms() {
		// @RequestMapping(value = "/call", produces="application/json")
		// public String saveAlarms(HttpServletRequest request) {

		String responsstatus = "";

		System.out.println("@@@@@@ I am in the rest ctontreller mthoed ");
		System.out.println("@@@@@@ System : " + system);
		System.out.println("@@@@@@ Alarm : " + alarm);
		System.out.println("@@@@@@ Alarm ID : " + alarmid);
		System.out.println("@@@@@@ Time : " + time);
		System.out.println("@@@@@@ Type :" + type);
		System.out.println("@@@@@@ Seviarity : " + seviarity);
		System.out.println("@@@@@@ Details : " + details);
		System.out.println("@@@@@@ Status : " + status);

		if (Integer.parseInt(status) == 0) {
			boolean uprowcount = false;

			try {
				int alarmnameid = Integer.parseInt(alarmid);
				int systemid = Integer.parseInt(system);

				Systems systemobj = new Systems();

				systemobj = TtSystemDao.getSystembyId(systemid);
				uprowcount = TtSystemDao.getAlarmByalsyst(alarmnameid, systemid);

				Alarmname alarmobj = new Alarmname();
				alarmobj = TtSystemDao.getAlarmnamebyID(alarmnameid);

				if (uprowcount) {
					List<SystemsHasUser> sysusers = TtSystemDao.getSystemsHasUserbySystem(systemobj);
					boolean msgss = false;
					
					
					boolean sms = sendSms(alarmnameid, systemobj, "CLEAR");
					boolean email = sendEmail(alarmnameid, systemobj, "CLEAR");
					
					responsstatus = "Ok";
					

				} else {
						responsstatus = "Ok";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("000000 save error in api ");
				e.printStackTrace();
				return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			if (system == "") {
				return new ResponseEntity<String>("Data Packet Has no related System", HttpStatus.NO_CONTENT);
			} else {
				try {
					int alarmnameid = Integer.parseInt(alarmid);
					int systemid = Integer.parseInt(system);
					if (TtSystemDao.getAlarmByalsystnew(alarmnameid, systemid)) {

						return new ResponseEntity<String>("Ok", HttpStatus.OK);

					} else {

						Alarm alarmobj = new Alarm();
						Systems systemobj = new Systems();

						try {
							systemobj = TtSystemDao.getSystembyId(Integer.parseInt(system));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// get the ype
						Types typeobj = new Types();

						try {
							typeobj = TtSystemDao.getTypesbyID(Integer.parseInt(type));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Alarmname alarmname = new Alarmname();


						alarmname = TtSystemDao.getAlarmnamebyID(Integer.parseInt(alarmid));
						
						Date date = new Date();

						MyDateUtil d = new MyDateUtil();
						String date_str = time.replaceAll("_", " ");
						String uniqueID = UUID.randomUUID().toString();
						String alarmdata = null;
						try {
							alarmdata = URLDecoder.decode(alarm, "UTF-8");
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// severe 	=1
						// warning	=2
						// info		=3
						// normal	=4 
						// other(not needed)=5 
						
						alarmobj.setRegtime(date);
						alarmobj.setAlarm(alarmdata);
						alarmobj.setSeverity(alarmname.getStatus());
						alarmobj.setAlarmname(alarmname);
						alarmobj.setDetails(details); // oid
						alarmobj.setOriginatetime(date);
						alarmobj.setSystems(systemobj);
						alarmobj.setTypes(typeobj);
						alarmobj.setStatus(Integer.parseInt(status));
						
						// save the type object
						try {

							alarmobj = TtSystemDao.saveOrUpdate(alarmobj);

							System.out.println("save success in api ");

							Alarmname alarmname1 = new Alarmname();

							alarmname1 = TtSystemDao.getAlarmnamebyID(Integer.parseInt(alarmid));

							List<SystemsHasUser> sysusers = TtSystemDao.getSystemsHasUserbySystem(systemobj);
							boolean msgss = false;
							
							boolean sms = sendSms(alarmnameid, systemobj, "ACTIVE");
							boolean email = sendEmail(alarmnameid, systemobj, "ACTIVE");
							
							responsstatus = "Ok";

							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("save error in api ");
							e.printStackTrace();
							return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
						}

					}

				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}

		}

		return new ResponseEntity<String>(responsstatus, HttpStatus.OK);

	}

	@RequestMapping(value = "/heartbeat/{systemid}/{code}/", produces = "application/json")
	public ResponseEntity<String> setHeartBeat(@PathVariable("systemid") String systemid,
			@PathVariable("code") String code, HttpServletRequest request) {

		String respnse = "";
		MyDateUtil date = new MyDateUtil();
		System.out.println(date.getDateNow());

		try {

			String ip = request.getRemoteAddr();

			System.out.println("~~~~~ip : " + ip);

			Systems systems = new Systems();
			systems = TtSystemDao.getSystembyId(Integer.parseInt(systemid));

			Heartbeat heartbeat = new Heartbeat();
			heartbeat.setSystems(systems);
			heartbeat.setIp(ip);
			heartbeat.setRegtime(date.getDateNow());
			heartbeat.setDescription(code);

			heartbeat = TtSystemDao.saveOrUpdate(heartbeat);

			respnse = "ok";

		} catch (Exception ex) {
			respnse = "error";
			System.out.println("************Error");
			ex.printStackTrace();
		}

		return new ResponseEntity<String>(respnse, HttpStatus.OK);
	}

	// get system id and ip list
	@RequestMapping(value = "/sysdetails/{companyid}/", produces = "application/json")
	public ResponseEntity<String> getSysdetails(@PathVariable("companyid") String companyid,
			HttpServletRequest request) {
		String respnse = "";
		MyDateUtil date = new MyDateUtil();
		System.out.println(date.getDateNow());

		JSONArray jsonArray = new JSONArray();
		
		
		int comid = Integer.parseInt(companyid);
		
		if(comid == 0) { // select * from system table 
			System.out.println("test 0");
			try {
				List<Systems> systemlist = null;

				systemlist = TtSystemDao.getSystem();
				for (Systems systems : systemlist) {
					JSONObject json = new JSONObject();
					json.put("id", systems.getId());
					json.put("ip", systems.getIp());
					json.put("url", systems.getUrl());

					jsonArray.put(json);
				}

				respnse = jsonArray.toString();

			} catch (Exception ex) {
				respnse = "error";
				System.out.println("************Error");
				ex.printStackTrace();
			}
			
			
			
		}else {
		
		try {



			List<Systems> systemlist = null;

			systemlist = TtSystemDao.getSystem(Integer.parseInt(companyid));
			for (Systems systems : systemlist) {
				JSONObject json = new JSONObject();
				json.put("id", systems.getId());
				json.put("ip", systems.getIp());
				json.put("url", systems.getUrl());

				jsonArray.put(json);
			}

			respnse = jsonArray.toString();

		} catch (Exception ex) {
			respnse = "error";
			System.out.println("************Error");
			ex.printStackTrace();
		}
		
		}

		return new ResponseEntity<String>(respnse, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/REQ-HOS-SEC", produces = "application/json")
	public @ResponseBody JSONArray hospitalSectionalQ(HttpServletRequest request) {

		System.out.println("Hello");

		return null;

	}
	
	
	
	
	@RequestMapping(value = "/alarmtext/{system}/{alarm}/{time}/{type}/{seviarity}/{details}/{status}/", produces = "application/json")
	public ResponseEntity<String> setAlarmText(@PathVariable("system") String system, @PathVariable("alarm") String alarm,
			@PathVariable("time") String time,
			@PathVariable("type") String type, @PathVariable("seviarity") String seviarity,
			@PathVariable("details") String details, @PathVariable("status") String status) {
		// public ResponseEntity<String> saveAlarms() {
		// @RequestMapping(value = "/call", produces="application/json")
		// public String saveAlarms(HttpServletRequest request) {

		String responsstatus = "OK";


		alarm = alarm.replace('+', ' ');
		
		String[] parts = alarm.split(":");
		String serviritytype = parts[0]; 
		
		String alarmstring = parts[1];
		
		alarmstring = alarmstring.trim();
		

		
		try {
		
		int systemid = Integer.parseInt(system);

		Systems systemobj = new Systems();

		systemobj = TtSystemDao.getSystembyId(systemid);
		
		Types types = TtSystemDao.getTypesbyID(Integer.parseInt(type));
		
		
		
		if(serviritytype.equals("WARNING") || serviritytype.equals("SEVERE")) {
			System.out.println("WARNING");
		
			Alarmname alarmane = TtSystemDao.getAlarmnamebyName("name", alarmstring);	
			
			if(alarmane != null) {

				if (TtSystemDao.getAlarmByalsystnew(alarmane.getId(), systemid)) {

					return new ResponseEntity<String>("Ok", HttpStatus.OK);

				}else {
					
					boolean returnval = saveAlarm(alarmane, details, systemobj, types, 1);
					
					if(returnval) {
						// header ACTIVE or CLEAR
						 boolean sms = sendSms(alarmane.getId(),systemobj,"ACTIVE");
						 boolean email= sendEmail(alarmane.getId(),systemobj,"ACTIVE");
						
						 return new ResponseEntity<String>("Ok", HttpStatus.OK);
						
					}else {
						return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);						
					}
					
					
				}
	
			}else {
				return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
			}
			
			
		}else if(serviritytype.equals("INFORMATION")) {
			System.out.println("INFORMATION");
			
			Alarmname alarmane = TtSystemDao.getAlarmnamebyName("clear", alarmstring);

			if(alarmane != null) {
				
				System.out.println("@@@@@@@@@@@@@@@@ : "+alarmane.getClear());
				System.out.println("@@@@@@@@@@@@@@@@ : "+alarmane.getId());
				
				
				List<Alarm> allist =TtSystemDao.getAlarmListforClear(alarmane.getId(),systemid, 1);
				MyDateUtil date = new MyDateUtil();
				int count = 0;
				
				if(!allist.isEmpty()) {
					for(Alarm alrmobj : allist) {
						count ++;
						alrmobj.setCleartime(date.getDateNow());
						alrmobj.setStatus(0);
						
						try {
							alrmobj = TtSystemDao.saveOrUpdate(alrmobj);
							
						}catch(Exception e) {
							count = 0;
							e.printStackTrace();
						}
						
					}
					
					if(count !=0) {
						boolean sms = sendSms(alarmane.getId(), systemobj, "CLEAR");
						boolean email = sendEmail(alarmane.getId(), systemobj, "CLEAR");
					}else {
						return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
					}
					
				}else {
					return new ResponseEntity<String>("OK", HttpStatus.OK);
				}
				
				
				
				
				
			}else {
				
				return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
			}
			
			
		}
		
		}catch (Exception e) {
			System.out.println("error : "+e);
			e.printStackTrace();
			// TODO: handle exception
		}
		
		

		return new ResponseEntity<String>(responsstatus, HttpStatus.OK);


	
	}
	
	//------------------------------------ internal function for api--------------------------------------------
	public boolean saveAlarm(Alarmname alarmane,String details,Systems systemobj,Types types,int statusid) {
		boolean returnobj = false;
		
		try{
			
			Alarm alarmobj = new Alarm();
			MyDateUtil date = new MyDateUtil();
			
			
			alarmobj.setRegtime(date.getDateNow());
			alarmobj.setAlarm(alarmane.getName());
			alarmobj.setSeverity(alarmane.getStatus());
			alarmobj.setAlarmname(alarmane);
			alarmobj.setDetails(details); // oid
			alarmobj.setOriginatetime(date.getDateNow());
			alarmobj.setSystems(systemobj);
			alarmobj.setTypes(types);
			alarmobj.setStatus(statusid);
			
			alarmobj = TtSystemDao.saveOrUpdate(alarmobj);
			
			if(alarmobj.getId()==null) {
				returnobj = false;
				
			}else {
				returnobj = true;
			}
			
			
		}catch (Exception e) {
			returnobj = false;
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		
		return returnobj;
	}
	
	
	// header ACTIVE or CLEAR
	public boolean sendSms(int alarmid,Systems systemobj,String header) {
		
		boolean returnstat = false;
		
		Alarmname alarmname1 = new Alarmname();

		try {
		alarmname1 = TtSystemDao.getAlarmnamebyID(alarmid);
		
		List<SystemsHasUser> sysusers = TtSystemDao.getSystemsHasUserbySystem(systemobj);
		for (SystemsHasUser systemshasuser : sysusers) {
		
			if(systemshasuser.getUser().getMobile()==null ||systemshasuser.getUser().getMobile().equals(null) || systemshasuser.getUser().getMobile().equals("") || systemshasuser.getUser().getMobile().isEmpty()) {
			
			}else {
				String txtTelNo = systemshasuser.getUser().getMobile();
				String txtMessage = "["+header+"] "+systemobj.getName()+" Alarm :" +alarmname1.getName();
				
				SmsService sms = new SmsService();
				boolean msgstatus = sms.smsSender(txtTelNo, txtMessage);
				
				System.out.println("sms status : " + msgstatus);
			}
		}
		returnstat = true;
		}catch (Exception e) {
			returnstat = false;
			e.printStackTrace();
			// TODO: handle exception
		}
		return returnstat;
	}
	
	public boolean sendEmail(int alarmid,Systems systemobj,String header) {
		boolean returnstat = false;
		
		Alarmname alarmname1 = new Alarmname();

		try {
		
		alarmname1 = TtSystemDao.getAlarmnamebyID(alarmid);

		List<SystemsHasUser> sysusers = TtSystemDao.getSystemsHasUserbySystem(systemobj);
		for (SystemsHasUser systemshasuser : sysusers) {
			
			if(systemshasuser.getUser().getEmail()== null ||systemshasuser.getUser().getEmail().equals(null) || systemshasuser.getUser().getEmail().equals("") || systemshasuser.getUser().getEmail().isEmpty()) {
			
			}else {
				MailService ms = new MailService();
				returnstat = ms.doSendEmail(systemshasuser.getUser().getEmail(), systemobj.getName(),"["+header+"] : " + alarmname1.getName());
			}
			returnstat = true;
		}
		
		}catch (Exception e) {
			returnstat = false;
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return returnstat;
	}
	


	

}
