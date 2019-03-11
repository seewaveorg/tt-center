package com.ngs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
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
import com.ngs.model.Types;
import com.ngs.service.DbService;
import com.ngs.service.MailService;
import com.ngs.util.FileValidator;
import com.ngs.util.MyDateUtil;
import com.ngs.service.SmsService;

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
				uprowcount = TtSystemDao.getAlarmByalsyst(alarmnameid, systemid);

				Alarmname alarmobj = new Alarmname();
				alarmobj = TtSystemDao.getAlarmnamebyID(alarmnameid);

				if (uprowcount) {
					MailService ms = new MailService();
					boolean msgss = ms.doSendEmail("thimal@energynetlk.com", "clear..! UPS Alarm Clear",
							alarmobj.getName());

					String txtOriNo = "94711012725";
					String txtTelNo = "0766410980";
					String txtHeading = " UPS Alarm Clear..!";
					String txtMessage = " UPS Alarm Clear : " + alarmobj.getName();
					String txtPasswd = "thara";
					String txtUID = "energynet";

					System.out.println("####################array :" + alarmobj.getName());

					SmsService sms = new SmsService();
					boolean msgstatus = sms.smsSender(txtOriNo, txtTelNo, txtHeading, txtMessage, txtPasswd, txtUID);

					System.out.println("sms status : " + msgstatus);

					if (msgss) {
						responsstatus = "Ok";
					} else {
						responsstatus = "ok, but email not sent ";
					}

				} else {
					MailService ms = new MailService();
					boolean msgss = ms.doSendEmail("thimal@energynetlk.com", "error..! UPS Alarm Error",
							alarmobj.getName());
					if (msgss) {
						responsstatus = "Ok";

					} else {
						responsstatus = "ok, but email not sent ";
					}
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

						alarmname.setId((Integer.parseInt(alarmid)));

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

						alarmobj.setRegtime(date);
						alarmobj.setAlarm(alarmdata);
						alarmobj.setSeverity(Integer.parseInt(seviarity));
						alarmobj.setAlarmname(alarmname);
						alarmobj.setDetails(details); // oid
						alarmobj.setSystems(systemobj);
						alarmobj.setTypes(typeobj);
						alarmobj.setStatus(Integer.parseInt(status));

						// save the type object
						try {

							alarmobj = TtSystemDao.saveOrUpdate(alarmobj);

							System.out.println("save success in api ");

							Alarmname alarmname1 = new Alarmname();

							alarmname1 = TtSystemDao.getAlarmnamebyID(Integer.parseInt(alarmid));

							MailService ms = new MailService();
							boolean msgss = ms.doSendEmail("thimal@energynetlk.com", "UPS Alarm", alarmname1.getName());

							String txtOriNo = "94711012725";
							String txtTelNo = "0766410980";
							String txtHeading = "UPS Alarm..!";
							String txtMessage = "UPS Alarm : " + alarmname1.getName();
							String txtPasswd = "thara";
							String txtUID = "energynet";

							System.out.println("####################array :" + alarmname1.getName());

							SmsService sms = new SmsService();
							boolean msgstatus = sms.smsSender(txtOriNo, txtTelNo, txtHeading, txtMessage, txtPasswd,
									txtUID);

							System.out.println("sms status : " + msgstatus);

							if (msgss) {
								responsstatus = "Ok";

							} else {
								responsstatus = "ok, but email not sent ";
							}

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

		try {

			String ip = request.getRemoteAddr();

			System.out.println("~~~~~ip : " + ip);

			Systems systems = new Systems();
			systems = TtSystemDao.getSystembyId(Integer.parseInt(systemid));

			Heartbeat heartbeat = new Heartbeat();
			heartbeat.setSystems(systems);
			heartbeat.setIp(ip);
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

	@RequestMapping(value = "/REQ-HOS-SEC", produces = "application/json")
	public @ResponseBody JSONArray hospitalSectionalQ(HttpServletRequest request) {

		System.out.println("Hello");

		return null;

	}

}
