package com.ngs.dao.interfaces;

import java.util.List;

import com.ngs.model.Activities;
//import com.ngs.dao.implementation.Integet;
import com.ngs.model.Alarm;
import com.ngs.model.Alarmname;
import com.ngs.model.Branch;
import com.ngs.model.Brand;
import com.ngs.model.Company;
import com.ngs.model.Department;
import com.ngs.model.Heartbeat;
import com.ngs.model.RollHasActivities;
import com.ngs.model.Systems;
import com.ngs.model.SystemsHasUser;
import com.ngs.model.Tt;
import com.ngs.model.TtUpdate;
import com.ngs.model.Types;
import com.ngs.model.User;
import com.ngs.model.UserHasDepartment;

public interface TtSystemDao {
	
	Tt saveOrUpdate(Tt tt)throws Exception;//insert or update trouble ticket
	Alarm saveOrUpdate(Alarm alarm)throws Exception;//insert or Alarm
	List<Alarm> getAlarms()throws Exception;//get all registered alarm
	Alarm getAlarmById(int id)throws Exception;//get Alarm restricted by id
	List<Alarm> getAlarmsListForCompany(List<Systems> systemList)throws Exception;//get Alarms restricted in company
	List<Types> getTypes()throws Exception;//get Alarm types
	Types getTypesbyID(int id)throws Exception;//get Alarm types
	
	int getLastInsertedValue()throws Exception;
	List<Systems> getSystem()throws Exception;//get all registered systems
	List<Tt> getTroubleTicket()throws Exception;//get all registered trouble tickets
	Tt getTtById(int id)throws Exception;//get trouble ticket restricted by id
	TtUpdate saveOrUpdate(TtUpdate ttupdate);//update trouble ticket
	//List<User> getUsers()throws Exception;//get all registered users
	Tt getTroubleTicketById(int id);//get trouble ticket restricted by id
	int getAlarmCount();//get alarm count
	List<Company> getCompany()throws Exception;//get all registered companies
	//List<Department>getDepartment()throws Exception;//get all departments
	List<Systems> getSystemByCompany(int id)throws Exception;//get systems restricted by company id
	List<UserHasDepartment> getDepartmentByUser(int id)throws Exception;//get userhasdepartment
	//List<Tt> getTtByDepartment(int department)throws Exception;//get trouble ticket restricted by department
	//List<Department> getDepartmentList(List<UserHasDepartment> userhasdepartmentList);//get all user's departments
	//List<Department> getDepartmentByUser(int id);
	List<Tt> getTtByDepartment(List<Integer> department) throws Exception;//get trouble ticket restricted by department
	//List<Types> getTypes(Integer integer) throws Exception;
	List<Systems> getSystem(Integer id) throws Exception;//
	Systems getSystembyId(Integer id) throws Exception;//
	List<Department> getDepartment(Integer id) throws Exception;
	List<User> getUsers(Integer id) throws Exception;
	List<Activities> getActivities(List<Integer> activity)throws Exception;//get activities restricted by roll
	List<RollHasActivities>getActivitiesByRoll(int roll)throws Exception;
	//List<Systems> getSystemByCompany(Company company)throws Exception;
	List<Tt> getTtByCompany(int id)throws Exception;
	List<Alarm> searchAlarmForPagination(int size, int param, List<Systems> systemList) throws Exception;
	List<Tt> searchTtForPagination(int size,int param) throws Exception;
	List<Tt> searchTtForPagination(int id,int size,int param) throws Exception;
	List<Tt> searchTtForPagination(List<Integer> departmentList,int size,int param) throws Exception;
	Company saveOrUpdate(Company company)throws Exception;//save or update company
	Systems saveOrUpdate(Systems systems)throws Exception;//save or update system
	List<Company> getCompanyForUser(int id)throws Exception;//get user's company
	User saveOrUpdate(User person)throws Exception;//insert or update user
	List<Integer> getMaxVal()throws Exception;//get maximum id of user
	User getUserById(int id)throws Exception;//get user by user id
	Department getDepartmentByName(String id);//get department by name
	UserHasDepartment saveOrUpdate(UserHasDepartment uhasd)throws Exception;//save or update UserHasDepartment
	Department getDepartmentById(int id)throws Exception;
	
	
	//get alarm list by alarmname,system,status
	boolean getAlarmByalsyst(int alarmname,int systemid)throws Exception;
	
	Alarmname getAlarmnamebyID(int id)throws Exception;//get trouble ticket restricted by id
	
	//hearbeat save
	Heartbeat saveOrUpdate(Heartbeat heartbeat)throws Exception;
	
	//get alarm list by status
	public List<Alarm> getAlarmListBystatus(int size, int param, int systemid,int status) throws Exception;
	
	// get current alarmlist 
	public List<Alarm> getAlarmsListForCompanyandstatus(int systemid, int status) throws Exception;
	
	
	public boolean getAlarmByalsystnew(int alarmname, int systemid) throws Exception;
		
	public int getAlarmcount(int systemid ,int severity) throws Exception;
	
	public int getAlarmClearcount(int systemid) throws Exception;
	
	public List<Alarm> getAlarmsListForBysysid(int systemid) throws Exception;
	
	public Heartbeat getlastHbeat(int systemid) throws Exception;
	
	public User getUser(int id) throws Exception;
	
	
	SystemsHasUser saveOrUpdate(SystemsHasUser sysusers)throws Exception;
	
	public List<SystemsHasUser> getSystemsHasUserbySystem(Systems system) throws Exception;
	
	List<Brand> getBrand()throws Exception;
	
	List<Branch> getBranch(int id)throws Exception;
	
	public Brand getBrandbyid(int id) throws Exception;
	
	Company getCompanyById(int id)throws Exception;
	
	//getBranchbyid
	
	public Branch getBranchbyid(int id) throws Exception;
	
	public int getSystemsHasUserbySystemanUser(Systems system,User user) throws Exception;
	
	public Brand saveOrUpdate(Brand brand) throws Exception;
	public List<Systems> getSystemall() throws Exception;
	
	public Branch saveOrUpdate(Branch branch) throws Exception ;
	
	
	public Alarmname getAlarmnamebyName(String param, String name) throws Exception;
	public List<Alarm> getAlarmListforClear(int alarmid,int systemid, int status) throws Exception;
	
	public List<SystemsHasUser> getAllSystemsHasUserbySystem(Systems system) throws Exception;
	
}

