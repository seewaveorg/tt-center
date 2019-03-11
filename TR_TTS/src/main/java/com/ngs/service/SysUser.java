package com.ngs.service;

import com.ngs.model.Company;


public class SysUser {
	
	private int id;
	private int user;
	private String name;
	private String username;
	private String imagpath;
	private String rolle;
	private int rolleid;
	private int companyID;
	private int departmentID;
	private int loginID;
	private Company company;
	
	
	
	
	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public int getCompanyID() {
		return companyID;
	}


	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}


	public int getDepartmentID() {
		return departmentID;
	}


	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}



	public int getLoginID() {
		return loginID;
	}


	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}


	SysUser(){
		
	}
	
	
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}
	public String getImagpath() {
		return imagpath;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setImagpath(String imagpath) {
		this.imagpath = imagpath;
	}


	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}




	public String getRolle() {
		return rolle;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}


	public int getRolleid() {
		return rolleid;
	}


	public void setRolleid(int rolleid) {
		this.rolleid = rolleid;
	}



	
	public int getUser() {
		return user;
	}


	public void setUser(int user) {
		this.user = user;
	}
	

}
