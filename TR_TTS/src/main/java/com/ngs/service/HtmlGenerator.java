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
	
	SimpleDateFormat simpledteformt = new SimpleDateFormat ("yyyy.MM.dd hh:mm a");
	// ---------------------------------generate Alarm
	// table---------------------------------------------------
	public String generateAlarmTable(List<Alarm> list) {

		String table ="<table class='table'> "
						+"<thead>"
							+"<tr>"
								+"<th>No</th>"
								+"<th>Event</th>"
								+"<th>Type</th>"
								+"<th>Date</th>"
								+"<th>Severity</th>"
								+"<th>System</th>"
								+"<th>Status</th>"
								+"<th>O</th>"
							+"</tr>"
						+"</thead>"
						+"<tbody>";
		
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

			if(alrm.getStatus()==0){
				
				if (alrm.getSeverity() == 1) {
					color = "#ffebee"; // Critical - Red
					severity = "Critical";
				} else if (alrm.getSeverity() == 2) {
					color = "#fff3e0"; // Major - orange
					severity = "Major";
				} else if (alrm.getSeverity() == 3) {
					color = "#eceff1"; // Minor - gray
					severity = "Minor";
				} else if (alrm.getSeverity() == 4) {
					color = "#eceff1";// Trivial - lite color
					severity = "Trivial";
				} else {
					color = "#FAFAFA"; // other - white
					severity = "No Record";
				}
				
				
				
			}else{
				if (alrm.getSeverity() == 1) {
					color = "#E57373"; // Critical - Red
					severity = "Critical";
				} else if (alrm.getSeverity() == 2) {
					color = "#FFB74D"; // Major - orange
					severity = "Major";
				} else if (alrm.getSeverity() == 3) {
					color = "#78909C"; // Minor - gray
					severity = "Minor";
				} else if (alrm.getSeverity() == 4) {
					color = "#CFD8DC";// Trivial - lite color
					severity = "Trivial";
				} else {
					color = "#FAFAFA"; // other - white
					severity = "No Record";
				}
				
			}
			
			
			
			
			
			// bgcolor="#FF0000"
			if (alrm.getStatus() == 1) {
				table = table
						+"<tr bgcolor='"+color+"'>"
							+"<td>"+i+"</td>"
							+"<td>"+alrm.getAlarmname().getName()+"</td>"
							+"<td>" + alrm.getTypes().getName()+"</td>"
							+"<td>"+simpledteformt.format(alrm.getRegtime())+"</td>"
							+"<td>"+severity+"</td>"
							+"<td>"+sys+"</td>"
							+"<td><span class='label label-danger'>not clear</span></td>"
							+"<td><input Type='checkbox' class='group1' name='alarmcheck' id='alarm' value='"+alrm.getId() +"'></td>"
						+"</tr>";
			} else {
				table = table
						+"<tr bgcolor='" + color + "'>"
							+"<td>"+i+"</td>"
							+"<td>"+alrm.getAlarmname().getName()+"</td>"
							+"<td>"+alrm.getTypes().getName()+"</td>"
							+"<td>"+simpledteformt.format(alrm.getRegtime())+"</td>"
							+"<td>"+severity+"</td>"
							+"<td>"+sys+"</td>"
							+"<td><span class='label label-success'>clear</span></td>"
							+"<td><input Type='checkbox' class='group1' name='alarmcheck' id='alarm' disabled='disabled' value='"+alrm.getId()+"'></td>"
						+"</tr>";

			}

		}

		table = table + "</tbody></table>";

		return table;
	}

	// --------------------------generate trouble ticket
	// table------------------------------------------------
	public String generateTroubleTicketTable(List<Tt> list) {

		String table = "<table class='table'> <thead> <tr> <th>No</th> <th>Comment</th><th>Current Owner</th> <th> Date time </th>"
				+ "</tr> </thead> <tbody>";
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
					+simpledteformt.format(tt.getRegtime()) + "</td></tr>";

		}

		table = table + "</tbody></table>";

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
		System.out.println(" menu html " + a);
		return a;

	}

	// ---------------------------------- populate system drop
	// down------------------------------------------
	public String generateOptionList(List<Systems> list) {

		String option = "<option>Select System</option>";

		for (Systems system : list) {
			option = option + "<option value='" + system.getId() + "'>" + system.getName() + "</option>";
		}
		System.out.println(" menu html " + option);
		return option;

	}

	// ------------------------------------populate user
	// dropdown--------------------------------------------------
	public String generateUserList(List<User> list) {

		String option = "<option>Select user</option>";

		for (User user : list) {
			option = option + "<option value='" + user.getId() + "'>" + user.getFirstname() + "</option>";
		}
		System.out.println(" menu html " + option);
		return option;

	}

	// ----------------------------------populate department
	// dropdown--------------------------------------------------
	public String generateDepartmentList(List<Department> list) {

		String option = "<option>Select department</option>";

		for (Department department : list) {
			option = option + "<option value='" + department.getId() + "'>" + department.getName() + "</option>";
		}
		System.out.println(" menu html " + option);
		return option;

	}

	// ------------------------------------generate User
	// table---------------------------------------------------------
	public String generatePersonTable(List<User> list) {
		// System.out.println("user list*********"+list);
		String table = "<table class='table' > <thead> <tr><th>Initials</th><th>First Name</th>"
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
	
	//dashboard components load
	public String generateDboardComponents(List<Systems> list) {

		String option = "";

		for (Systems systems : list) {
			
			
			option = option + "<div class='col-lg-3 col-md-6'>"+
            		"<div style='' id="+systems.getId()+" class='card-box'>"+
                    "<div class='dropdown pull-right'>"+
                        "<a href='#' class='dropdown-toggle card-drop' data-toggle='dropdown' aria-expanded='false'>"+
                            "<i class='zmdi zmdi-more-vert'></i>"+
                        "</a>"+
                        "<ul class='dropdown-menu' role='menu'>"+
                            "<li><a target='blank' href='"+systems.getUrl()+"'>Configuration</a></li>"+
                            "<li><a target='blank' href='#'>Another action</a></li>"+
                            "<li><a target='blank' href='#'>Something else here</a></li>"+
                            "<li class='divider'></li>"+
                            "<li><a target='blank' href='#'>Separated link</a></li>"+
                        "</ul>"+
                    "</div>"+

        			"<h4 class='header-title m-t-0 m-b-30'>"+systems.getName()+"</h4>"+
                    "<div onclick='javascript:openalarmwindow(" + systems.getId() + ");' class='widget-box-2'>"+
                        "<div class='widget-detail-2'>"+
                        "<h4 id='"+systems.getId()+"clear' class='m-b-0 pull-left'>00 <br>Alarms Cleared</h4>"+
                            "<span id='"+systems.getId()+"critical' class='badge badge-danger'>00 Critical</span><br>"+
                            "<span id='"+systems.getId()+"major' class='badge badge-warning'>00 Major</span><br>"+
                            "<span id='"+systems.getId()+"minor' class='badge badge-default'>00 Minor</span>"+
                        "</div>"+
                        "<br>"+    
                        "<div class='progress progress-bar-success-alt progress-sm m-b-0'>"+
                            "<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='77' aria-valuemin='0' aria-valuemax='100' style='width: 77%;'>"+
                                "<span class='sr-only'>77% Complete</span>"+
                            "</div>"+
                        "</div>"+
                    "</div>"+
        		"</div>"+
            "</div>";
	
			//option = option + "<option value='" + systems.getId() + "'>" + systems.getName() + "</option>";
		}
		return option;

	}

}
