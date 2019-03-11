package com.ngs.dao.implementation;

import java.util.List;

//import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.dao.interfaces.TtSystemDao;
import com.ngs.model.Activities;
import com.ngs.model.Alarm;
import com.ngs.model.Alarmname;
import com.ngs.model.Company;
import com.ngs.model.Department;
import com.ngs.model.Heartbeat;
import com.ngs.model.RollHasActivities;
import com.ngs.model.Systems;
import com.ngs.model.Tt;
import com.ngs.model.TtUpdate;
import com.ngs.model.Types;
import com.ngs.model.User;
import com.ngs.model.UserHasDepartment;

public class TtSystemDaoImpl implements TtSystemDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Tt saveOrUpdate(Tt tt) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		if (tt.getId() == null) {
			session.save(tt);
		} else {
			session.update(tt);
		}
		return tt;

	}

	@Override
	@Transactional
	public Alarm saveOrUpdate(Alarm alarm) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		if (alarm.getId() == null) {
			session.save(alarm);
		} else {
			session.update(alarm);
		}
		return alarm;

	}

	@Override
	@Transactional
	public List<Alarm> getAlarms() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);
		// session.update(criteria);
		// criteria.add(Restrictions.eq("status",1));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Types> getTypes() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Types.class);
		// criteria.add(Restrictions.eq("company.id",integer));
		return criteria.list();
	}

	@Override
	@Transactional
	public int getLastInsertedValue() throws Exception {
		int id = 0;

		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			id = (Integer) session.createQuery("select max(id) from tt").uniqueResult();
			// mv.addObject("kor", result);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// mv.addObject("message", out);
		return id;

	}

	@Override
	@Transactional
	public Alarm getAlarmById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Alarm) session.load(Alarm.class, id);

	}

	@Override
	@Transactional
	public List<Systems> getSystem(Integer id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Systems.class);
		criteria.add(Restrictions.eq("company.id", id));
		return criteria.list();

	}

	@Override
	@Transactional
	public List<Tt> getTroubleTicket() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tt.class);

		// List<Tt> result = null;

		/*
		 * criteria.setFirstResult(param); criteria.setMaxResults(size);
		 * criteria.addOrder(Order.desc("id")); result = criteria.list();
		 * 
		 * return result;
		 */
		return criteria.list();

	}

	@Override
	@Transactional
	public Tt getTtById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Tt) session.load(Tt.class, id);
	}

	@Override
	@Transactional
	public TtUpdate saveOrUpdate(TtUpdate ttupdate) {
		Session session = sessionFactory.getCurrentSession();

		if (ttupdate.getId() == null) {
			session.save(ttupdate);
		} else {
			session.update(ttupdate);
		}
		return ttupdate;
	}

	@Override
	@Transactional
	public List<User> getUsers(Integer id) throws Exception {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("company.id", id));
		return criteria.list();

	}

	@Override
	@Transactional
	public Tt getTroubleTicketById(int id) {
		Session session = sessionFactory.getCurrentSession();
		// Criteria criteria = session.createCriteria(Tt.class);
		// criteria.add(Restrictions.eq("id",id));
		Tt tt = (Tt) session.load(Tt.class, id);
		return tt;
		// return criteria.;
	}

	@Override
	@Transactional
	public int getAlarmCount() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);
		return criteria.list().size();
	}

	@Override
	@Transactional
	public List<Company> getCompany() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Department> getDepartment(Integer id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Department.class);
		criteria.add(Restrictions.eq("company.id", id));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Systems> getSystemByCompany(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Systems.class); // System is
																	// a key
																	// word
		criteria.add(Restrictions.eq("company.id", id));

		return criteria.list();
	}

	@Override
	@Transactional
	public List<Alarm> getAlarmsListForCompany(List<Systems> systemList) throws Exception {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);
		criteria.add(Restrictions.in("systems", systemList));
		// criteria.setFirstResult(offset!=null?offset:0)
		// criteria.setMaxResults(maxResults!=null?maxResults:10)
		return criteria.list();
	}

	/*
	 * @Override
	 * 
	 * @Transactional public List<Department> getDepartmentByUser(int id) {
	 * Session session = sessionFactory.getCurrentSession(); Criteria criteria =
	 * session.createCriteria(UserHasDepartment.this.getDepartment());
	 * criteria.add(Restrictions.eq("user.id",id)); return criteria.list();
	 * 
	 * }
	 */
	@Override
	@Transactional
	public List<UserHasDepartment> getDepartmentByUser(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserHasDepartment.class);
		criteria.add(Restrictions.eq("user.id", id));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Tt> getTtByDepartment(List<Integer> departmentId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tt.class);
		criteria.add(Restrictions.in("department.id", departmentId));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Activities> getActivities(List<Integer> activityId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Activities.class);
		criteria.add(Restrictions.in("id", activityId));
		return criteria.list();

	}

	@Override
	@Transactional
	public List<RollHasActivities> getActivitiesByRoll(int roll) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RollHasActivities.class);
		criteria.add(Restrictions.eq("rolls.id", roll));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Tt> getTtByCompany(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tt.class);
		criteria.add(Restrictions.eq("company.id", id));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Alarm> searchAlarmForPagination(int size, int param, List<Systems> systemList) throws Exception {
		System.out.println("########################################## size is : " + size + " and param is : " + param);
		System.out.println("searching a db with parameters ");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);
		criteria.add(Restrictions.in("systems", systemList));
		List<Alarm> result = null;

		criteria.setFirstResult(param);
		criteria.setMaxResults(size);
		criteria.addOrder(Order.desc("id"));
		result = criteria.list();

		return result;
	}

	@Override
	@Transactional
	public List<Tt> searchTtForPagination(int size, int param) throws Exception {
		System.out.println("########################################## size is : " + size + " and param is : " + param);
		System.out.println("searching a db with parameters ");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tt.class);
		List<Tt> result = null;

		criteria.setFirstResult(param);
		criteria.setMaxResults(size);
		criteria.addOrder(Order.desc("id"));
		result = criteria.list();

		return result;
	}

	@Override
	@Transactional
	public List<Tt> searchTtForPagination(int id, int size, int param) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tt.class);
		criteria.add(Restrictions.eq("company.id", id));

		List<Tt> result = null;

		criteria.setFirstResult(param);
		criteria.setMaxResults(size);
		criteria.addOrder(Order.desc("id"));
		result = criteria.list();

		return result;

	}

	@Override
	@Transactional
	public List<Tt> searchTtForPagination(List<Integer> departmentList, int size, int param) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tt.class);
		criteria.add(Restrictions.in("department.id", departmentList));

		List<Tt> result = null;

		criteria.setFirstResult(param);
		criteria.setMaxResults(size);
		criteria.addOrder(Order.desc("id"));
		result = criteria.list();

		return result;
	}

	@Override
	@Transactional
	public Company saveOrUpdate(Company company) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		if (company.getId() == null) {
			session.save(company);
		} else {
			session.update(company);
		}
		return company;

	}

	@Override
	@Transactional
	public Systems saveOrUpdate(Systems systems) throws Exception {
		Session session = sessionFactory.getCurrentSession();

		if (systems.getId() == null) {
			session.save(systems);
		} else {
			session.update(systems);
		}
		return systems;
	}

	@Override
	@Transactional
	public List<Systems> getSystem() throws Exception {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Systems.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Company> getCompanyForUser(int id) throws Exception {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class);
		criteria.add(Restrictions.eq("id", id));
		return criteria.list();
	}

	@Override
	@Transactional
	public User saveOrUpdate(User person) throws Exception {
		Session session = sessionFactory.getCurrentSession();

		if (person.getId() == null) {
			session.save(person);
		} else {
			session.update(person);
		}
		return person;
	}

	@Override
	@Transactional
	public List<Integer> getMaxVal() throws Exception {
		/*
		 * Session session = sessionFactory.getCurrentSession(); Criteria
		 * criteria = session.createCriteria(Company.class); DetachedCriteria
		 * maxId = DetachedCriteria.forClass(User.class) .setProjection(
		 * Projections.max("id") ); session.createCriteria(User.class)
		 * 
		 * .add( Property.forName("id").eq(maxId) ) .list();
		 */
		////////////////////////////////
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(User.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("id"));
		crit.setProjection(projList);
		// List<Integer> results = crit.list();
		return crit.list();// results;
	}
	/*
	 * @Override
	 * 
	 * @Transactional public List<Department>
	 * getDepartmentList(List<UserHasDepartment> userhasdepartmentList) {
	 * Session session = sessionFactory.getCurrentSession(); Criteria criteria =
	 * session.createCriteria(Department.class);
	 * criteria.add(Restrictions.in("systems", userhasdepartmentList)); return
	 * criteria.list();
	 * 
	 * }
	 */

	@Override
	@Transactional
	public User getUserById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.load(User.class, id);
	}

	@Override
	@Transactional
	public Department getDepartmentByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Department department = (Department) session.load(Department.class, name);
		return department;
	}

	@Override
	@Transactional
	public UserHasDepartment saveOrUpdate(UserHasDepartment uhasd) throws Exception {
		Session session = sessionFactory.getCurrentSession();

		if (uhasd.getId() == null) {
			session.save(uhasd);
		} else {
			session.update(uhasd);
		}
		return uhasd;
	}

	@Override
	@Transactional
	public Department getDepartmentById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Department department = (Department) session.load(Department.class, id);
		return department;
	}

	@Override
	@Transactional
	public Systems getSystembyId(Integer id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Systems) session.load(Systems.class, id);
	}

	@Override
	@Transactional
	public Types getTypesbyID(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Types) session.load(Types.class, id);
	}

	// List<Alarm> getAlarmByalsyst(int alarmname,int systemid) throws
	// Exception;

	@Override
	@Transactional
	public boolean getAlarmByalsyst(int alarmname, int systemid) throws Exception {
		boolean tem = false;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);

		Criterion criteria1 = Restrictions.eq("alarmname.id", alarmname);
		Criterion criteria2 = Restrictions.eq("systems.id", systemid);
		Criterion criteria3 = Restrictions.eq("status", 1);
		Conjunction andExp = Restrictions.and(criteria1, criteria2, criteria3);

		criteria.add(andExp);

		List<Alarm> list = criteria.list();

		for (Alarm object : list) {
			object.setStatus(0);
			saveOrUpdate(object);
			tem = true;
		}
		return tem;
	}

	@Override
	@Transactional
	public Heartbeat saveOrUpdate(Heartbeat heartbeat) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		if (heartbeat.getId() == null) {
			session.save(heartbeat);
		} else {
			session.update(heartbeat);
		}
		return heartbeat;

	}

	@Override
	@Transactional
	public Alarmname getAlarmnamebyID(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Alarmname) session.load(Alarmname.class, id);
	}

	@Override
	@Transactional
	public List<Alarm> getAlarmListBystatus(int size, int param, int systemid, int status) throws Exception {
		System.out.println("########################################## size is : " + size + " and param is : " + param);
		System.out.println("searching a db with parameters ");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);
		// criteria.add(Restrictions.eq("status",
		// 1)).add(Restrictions.in("systems", systemList));

		Criterion criteria2 = Restrictions.eq("systems.id", systemid);
		Criterion criteria3 = Restrictions.eq("status", status);

		criteria.add(Restrictions.and(criteria2, criteria3));
		
		

		List<Alarm> result = null;

		criteria.setFirstResult(param);
		criteria.setMaxResults(size);
		criteria.addOrder(Order.desc("id"));
		result = criteria.list();

		return result;
	}

	@Override
	@Transactional
	public List<Alarm> getAlarmsListForCompanyandstatus(int systemid,int status) throws Exception {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);
		Criterion criteria2 = Restrictions.eq("systems.id", systemid);
		Criterion criteria3 = Restrictions.eq("status", status);
		criteria.add(Restrictions.and(criteria2, criteria3));

		return criteria.list();
	}
	
	
	
	@Override
	@Transactional
	public boolean getAlarmByalsystnew(int alarmname, int systemid) throws Exception {
		boolean tem = false;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);

		Criterion criteria1 = Restrictions.eq("alarmname.id", alarmname);
		Criterion criteria2 = Restrictions.eq("systems.id", systemid);
		Criterion criteria3 = Restrictions.eq("status", 1);
		Conjunction andExp = Restrictions.and(criteria1, criteria2, criteria3);

		criteria.add(andExp);

		List<Alarm> list = criteria.list();

		for (Alarm object : list) {
			return true;
		}
		return tem;
	}

	@Override
	@Transactional
	public int getAlarmcount(int systemid ,int severity) throws Exception {
		int count = 0;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);

		Criterion criteria1 = Restrictions.eq("severity", severity);
		Criterion criteria2 = Restrictions.eq("systems.id", systemid);
		Criterion criteria3 = Restrictions.eq("status", 1);
		Conjunction andExp = Restrictions.and(criteria1, criteria2, criteria3);

		criteria.add(andExp);

		List<Alarm> list = criteria.list();
		
		for (Alarm object : list) {
			count = count + 1;
		}
		return count;
	}
	
	//getAlarmClearcount
	@Override
	@Transactional
	public int getAlarmClearcount(int systemid) throws Exception {
		int count = 0;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Alarm.class);

		Criterion criteria2 = Restrictions.eq("systems.id", systemid);
		Criterion criteria3 = Restrictions.eq("status", 0);
	
		criteria.add(Restrictions.and(criteria2, criteria3));


		List<Alarm> list = criteria.list();
		
		for (Alarm object : list) {
			count = count + 1;
		}
		return count;
	}
	
	
}
