package com.ngs.service;

import java.text.SimpleDateFormat;
import java.util.List;

import com.ngs.model.Activities;
import com.ngs.model.Alarm;
import com.ngs.model.Department;
import com.ngs.model.Systems;
import com.ngs.model.Tt;
import com.ngs.model.User;

public class HtmlGenerator {

	SimpleDateFormat simpledteformt = new SimpleDateFormat("yyyy.MM.dd hh:mm a");

	// ---------------------------------generate Alarm
	// table---------------------------------------------------
	public String generateAlarmTable(List<Alarm> list) {

		String table = "";

		int i = 0;
		int x = 0;
		String color = "";
		String severity = "";

		for (Alarm alrm : list) {
			i++;
			String sys = "";

			if (alrm.getSystems().equals(null)) {
				sys = "Not defined";
			} else {
				sys = alrm.getSystems().getName();
			}

			if (alrm.getStatus() == 0) {

				if (alrm.getSeverity() == 1) {
					color = "#ffebee"; // Critical - Red
					severity = "<span class='label label-danger'>Severe</span>";
				} else if (alrm.getSeverity() == 2) {
					color = "#fff3e0"; // Major - orange
					severity = "<span class='label label-warning'>Warning</span>";
				} else if (alrm.getSeverity() == 3) {
					color = "#eceff1"; // Minor - gray
					severity ="<span class='label label-default'>Info</span>";
				} else if (alrm.getSeverity() == 4) {
					color = "#eceff1";// Trivial - lite color
					severity = "Normal";
				} else {
					color = "#FAFAFA"; // other - white
					severity = "No Record";
				}

			} else {
				if (alrm.getSeverity() == 1) {
					color = "#E57373"; // Critical - Red
					severity = "<span class='label label-danger'>Severe</span>";
				} else if (alrm.getSeverity() == 2) {
					color = "#FFB74D"; // Major - orange
					severity = "<span class='label label-warning'>Warning</span>";
				} else if (alrm.getSeverity() == 3) {
					color = "#78909C"; // Minor - gray
					severity ="<span class='label label-default'>Info</span>";
				} else if (alrm.getSeverity() == 4) {
					color = "#CFD8DC";// Trivial - lite color
					severity = "Normal";
				} else {
					color = "#FAFAFA"; // other - white
					severity = "No Record";
				}

			}

			
			// bgcolor="#FF0000"
			String cleardate ="";
			if(alrm.getCleartime()==null) {
				cleardate ="";
			}else {
				cleardate =alrm.getCleartime().toString();
			}
			
			if (alrm.getStatus() == 1) {
				table = table + "<tr>" + "<td>" + i + "</td>" + "<td>"
						+ alrm.getAlarmname().getName() + "</td>" + "<td>" + alrm.getTypes().getName() + "</td>"
						+ "<td>" + simpledteformt.format(alrm.getOriginatetime()) + "</td>"
						+ "<td>"+cleardate+"</td>"
						+ "<td>" + severity + "</td>"
						+ "<td>" + sys + "</td>" + "<td><span class='label label-danger'>not clear</span></td>"
						+ "<td><input Type='checkbox' class='group1' name='alarmcheck' id='alarm' value='"
						+ alrm.getId() + "'></td>" + "</tr>";
			} else {
				table = table + "<tr>" + "<td>" + i + "</td>" + "<td>"
						+ alrm.getAlarmname().getName() + "</td>" + "<td>" + alrm.getTypes().getName() + "</td>"
						+ "<td>" + simpledteformt.format(alrm.getOriginatetime()) + "</td>" 
						+ "<td>"+cleardate+"</td>"
						+ "<td>" + severity + "</td>"
						+ "<td>" + sys + "</td>" + "<td><span class='label label-success'>clear</span></td>"
						+ "<td><input Type='checkbox' class='group1' name='alarmcheck' id='alarm' disabled='disabled' value='"
						+ alrm.getId() + "'></td>" + "</tr>";

			}

		}


		return table;
	}

	// --------------------------generate trouble ticket
	// table------------------------------------------------
	public String generateTroubleTicketTable(List<Tt> list) {

		String table = "";
		int i = 0;
		for (Tt tt : list) {

			i++;
			String sys = "";
			if (tt.getSystems() == null) {
				sys = "Not defined";
			} else {
				sys = tt.getSystems().getName();
			}
			table = table +

					"<tr value='" + tt.getId() + "' onclick='loadMe();'><td>" + i + "</td><td >" + tt.getComment()
					+ "</td><td>" + tt.getUser().getFirstname() + " " + tt.getUser().getLastname() + "</td><td>"
					+ simpledteformt.format(tt.getRegtime()) + "</td></tr>";

		}


		return table;
	}

	// -------------------------------create side
	// bar--------------------------------------------------------------
	public String generateActivityList(List<Activities> list) {

		String a = "<li><a class='menu' onclick='javascript:dboard(1);'><span class='fa fa-square-o'></span>Main Dashboard</a></li>";
		for (Activities activities : list) {
			a = a + "<li ><a class='menu' onclick='javascript:test(" + activities.getId() + ");'  value='"
					+ activities.getUrl() + "' id='" + activities.getId() + "' ><span class='fa fa-square-o'></span>"
					+ activities.getActivitiy() + "</a></li>";

		}
		return a;

	}

	// ---------------------------------- populate system drop
	// down------------------------------------------
	public String generateOptionList(List<Systems> list) {

		String option = "<option>Select System</option>";

		for (Systems system : list) {
			option = option + "<option value='" + system.getId() + "'>" + system.getName() + "</option>";
		}
		return option;

	}

	// ------------------------------------populate user
	// dropdown--------------------------------------------------
	public String generateUserList(List<User> list) {

		String option = "<option>Select user</option>";

		for (User user : list) {
			option = option + "<option value='" + user.getId() + "'>" + user.getFirstname() + "</option>";
		}
		return option;

	}

	// ----------------------------------populate department
	// dropdown--------------------------------------------------
	public String generateDepartmentList(List<Department> list) {

		String option = "<option>Select department</option>";

		for (Department department : list) {
			option = option + "<option value='" + department.getId() + "'>" + department.getName() + "</option>";
		}
		return option;

	}

	// ------------------------------------generate User
	// table---------------------------------------------------------
	public String generatePersonTable(List<User> list) {
		String table = "<table id='datatable-buttons' class='table table-striped table-bordered'> <thead> <tr><th>Initials</th><th>First Name</th>"
				+ "<th>Last Name</th><th>NIC</th></tr> </thead> <tbody>";
		int i = 0;

		for (User person : list) {
			i++;

			table = table + "<tr value='" + person.getId() + "' onclick='loadMe();' ><td>" + person.getInitials()
					+ "</td> <td>" + person.getFirstname() + "</td> <td>" + person.getLastname() + "</td> <td>"
					+ person.getNic() + "</td></tr>";

		}
		return table + "</table>";

	}

	// dashboard components load
	public String generateDboardComponents(List<Systems> list) {

		String option = "";

		for (Systems systems : list) {

			String status = "Enable";
			
			option = option + "<div class='col-lg-2 col-md-4'>" + "<div id=" + systems.getId()
					+ " class='card-box'>" + "<div class='dropdown pull-right'>"
					+ "<a href='#' class='dropdown-toggle card-drop' data-toggle='dropdown' aria-expanded='false'>"
					+ "<i class='zmdi zmdi-more-vert'></i>" + "</a>" + "<ul class='dropdown-menu' role='menu'>"
					+ "<li><a target='blank' href='" + systems.getUrl() + "'>UPS Login</a></li>"
					+ "<li class='divider'></li>"
					+ "<li><a target='blank' onclick='javascript:sysenabledesable("+systems.getId()+");' >"+status+"</a></li>" + "</ul>" + "</div>"
					+ "<h4 class='header-title m-t-0 m-b-30'>" + systems.getName() + ""
						+"&nbsp;<i id='"+systems.getId()+"active' class='fa fa-circle text-success'></i>"
						+"&nbsp;<i id='"+systems.getId()+"deactive' class='fa fa-circle text-danger blink'></i>"
					+ "</h4>"
					+ "<div onclick='javascript:openalarmwindow("+systems.getId()+",\""+systems.getName()+"\");' class='widget-box-2'>"
					+ "<div class='widget-detail-2'>" + "<h4 id='" + systems.getId()
					+ "clear' class='m-b-0 pull-left'>00 <br>Alarms Cleared</h4>" + "<span id='" + systems.getId()
					+ "critical' class='badge badge-danger'>00 Critical</span><br>" + "<span id='" + systems.getId()
					+ "major' class='badge badge-warning'>00 Major</span><br>" + "<span id='" + systems.getId()
					+ "minor' class='badge badge-default'>00 Minor</span>" + "</div>" + "<br>"
					+"<div class='progress progress-bar-success-alt progress-sm m-b-5'>"
					+ "<div class='progress-bar progress-bar-success progress-animated wow animated animated'"
					+ "role='progressbar' aria-valuenow='80' aria-valuemin='0' aria-valuemax='100'"
					+ "style='width: 80%; visibility: visible; animation-name: animationProgress;'>"
					+ "</div>"
					+"</div>"
					+"</div>"
					+"</div>"
					+"</div>";

			// option = option + "<option value='" + systems.getId() + "'>" +
			// systems.getName() + "</option>";
		}
		return option;

	}

	// alarm table for system id
	public String generateAlarmtblforsysit(List<Alarm> list) {
		int i = 0;
		String tblbdy = "";
		String color="";
		String severity="";
		String status="";

		for (Alarm alarm : list) {
			i++;
			
			if (alarm.getStatus() == 0) {

				if (alarm.getSeverity() == 1) {
					color = "#ffebee"; // Critical - Red
					severity = "Severe";
				} else if (alarm.getSeverity() == 2) {
					color = "#fff3e0"; // Major - orange
					severity = "Warning";
				} else if (alarm.getSeverity() == 3) {
					color = "#eceff1"; // Minor - gray
					severity = "Information";
				} else if (alarm.getSeverity() == 4) {
					color = "#eceff1";// Trivial - lite color
					severity = "Trivial";
				} else {
					color = "#FAFAFA"; // other - white
					severity = "No Record";
				}

			} else {
				if (alarm.getSeverity() == 1) {
					color = "#E57373"; // Critical - Red
					severity = "Severe";
				} else if (alarm.getSeverity() == 2) {
					color = "#FFB74D"; // Major - orange
					severity = "Warning";
				} else if (alarm.getSeverity() == 3) {
					color = "#78909C"; // Minor - gray
					severity = "Information";
				} else if (alarm.getSeverity() == 4) {
					color = "#CFD8DC";// Trivial - lite color
					severity = "Trivial";
				} else {
					color = "#FAFAFA"; // other - white
					severity = "No Record";
				}

			}
			
			
			if (alarm.getStatus() == 1) {
				status ="<span class='label label-danger'>not clear</span>";
			}else {
				status ="<span class='label label-success'>cleared</span>";
			}
			
			if(alarm.getCleartime() == null) {
				tblbdy = tblbdy + "<tr>" + 
						"<td>"+i+"</td>"+ 
						"<td>"+alarm.getAlarmname().getName()+"</td>"+ 
						"<td>"+alarm.getTypes().getName()+"</td>"+ 
						"<td>"+simpledteformt.format(alarm.getOriginatetime())+"</td>"+
						"<td></td>"+
						"<td>"+severity+"</td>"+
						"<td>"+status+"</td>"+
					"</tr>";
			}else {
				tblbdy = tblbdy + "<tr>" + 
						"<td>"+i+"</td>"+ 
						"<td>"+alarm.getAlarmname().getName()+"</td>"+ 
						"<td>"+alarm.getTypes().getName()+"</td>"+ 
						"<td>"+simpledteformt.format(alarm.getOriginatetime())+"</td>"+
						"<td>"+simpledteformt.format(alarm.getCleartime())+"</td>"+
						"<td>"+severity+"</td>"+
						"<td>"+status+"</td>"+
					"</tr>";
			}
			
			
		}
		
		return tblbdy;
	}

}
