package com.ngs.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.dao.interfaces.PersonDao;
//import com.ngs.model.Alergies;
import com.ngs.model.Login;
import com.ngs.model.User;
//import com.ngs.model.Person;
//import com.ngs.model.PersonHasDeceases;
//import com.ngs.model.PersonHasSocialhabits;
//import com.ngs.model.Socialhabits;

public class PersonDaoImpl implements PersonDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<User> searchPersonByParam(String param, String value) {
		// get a list of person according to given param and value
		System.out.println("################param " + param + "  value is " + value);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		if (param.equals("id")) {
			criteria.add(Restrictions.eq(param, value));
		} else {
			criteria.add(Restrictions.like(param, value, MatchMode.ANYWHERE));
			// criteria.add( Restrictions.like(param, value) );
		}
		criteria.addOrder(Order.asc("firstname"));
		criteria.addOrder(Order.asc("familyname"));
		// criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(10);
		// List result = criteria.list();

		return criteria.list();
	}

	@Override
	@Transactional
	public User getPersonByID(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		// Person person = (Person) session.load(Person.class, id);
		User person = (User) session.get(User.class, id);
		return person;
	}

	@Override
	@Transactional
	public User saveOrUpdate(User person, String command) throws Exception {

		Session session = sessionFactory.getCurrentSession();

		// if (person.getId() != 0 || person.getId() == null) {
		if (command.equals("save")) {
			session.save(person);

		} else {
			// update

			session.update(person);

		}

		return person;

	}

	@Override
	@Transactional
	public List<User> getAllPerson(int hospital, int roll) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("roll", roll));
		criteria.addOrder(Order.asc("id"));
		criteria.setMaxResults(20);
		return criteria.list();
	}

	/****
	 * @Override
	 * @Transactional public Alergies saveOrUpdate(Alergies alergies)throws
	 *                Exception { Session session =
	 *                sessionFactory.getCurrentSession();
	 *                if(alergies.getId()==null){
	 *                if(alergies.getMedicine().getId()==0){
	 *                alergies.setMedicine(null); } session.save(alergies);
	 *                }else{ session.update(alergies); } return alergies; }
	 * 
	 * 
	 * @Override
	 * @Transactional public List<Alergies> getAllAlergies() throws Exception {
	 *                Session session = sessionFactory.getCurrentSession();
	 *                Criteria criteria =
	 *                session.createCriteria(Alergies.class);
	 *                criteria.addOrder(Order.desc("id")); //adds order
	 * 
	 *                return criteria.list(); }
	 * 
	 * 
	 * @Override
	 * @Transactional public List<Alergies> getAllergiesForAPerson(String
	 *                personID) throws Exception { Session session =
	 *                sessionFactory.getCurrentSession(); Criteria criteria =
	 *                session.createCriteria(Alergies.class);
	 *                criteria.add(Restrictions.eq("person.id", personID));
	 *                criteria.addOrder(Order.desc("id")); //adds order
	 * 
	 *                return criteria.list(); }
	 * 
	 * 
	 * @Override
	 * @Transactional public List
	 *                <PersonHasDeceases> getPersonHasDeceaseForPerson(String
	 *                personID) throws Exception { Session session =
	 *                sessionFactory.getCurrentSession(); Criteria criteria =
	 *                session.createCriteria(PersonHasDeceases.class);
	 *                criteria.add(Restrictions.eq("person.id", personID));
	 *                criteria.addOrder(Order.desc("id")); //adds order return
	 *                criteria.list(); }
	 * 
	 * 
	 * @Override
	 * @Transactional public List<Socialhabits> getAllSocialHabits() throws
	 *                Exception { Session session =
	 *                sessionFactory.getCurrentSession(); Criteria criteria =
	 *                session.createCriteria(Socialhabits.class);
	 *                criteria.addOrder(Order.desc("name")); //adds order return
	 *                criteria.list(); }
	 * 
	 * 
	 * 
	 * 
	 * @Override
	 * @Transactional public PersonHasSocialhabits
	 *                saveOrUpdate(PersonHasSocialhabits phsa) throws Exception{
	 *                Session session = sessionFactory.getCurrentSession();
	 *                if(phsa.getId()==null){ session.save(phsa); }else{
	 *                session.update(phsa); } return phsa; }
	 * 
	 * 
	 * @Override
	 * @Transactional public List
	 *                <PersonHasSocialhabits> getPersonHasSocialForPerson(String
	 *                personID) throws Exception { Session session =
	 *                sessionFactory.getCurrentSession(); Criteria criteria =
	 *                session.createCriteria(PersonHasSocialhabits.class);
	 *                criteria.add(Restrictions.eq("person.id", personID));
	 *                criteria.addOrder(Order.desc("id")); //adds order return
	 *                criteria.list(); }
	 ****/

	@Override
	@Transactional
	public Integer getLastInsertedPerson() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		/**
		 * Query query = session.createQuery("from Person order by reff DESC");
		 * query.setMaxResults(1); Person last = (Person) query.uniqueResult();
		 * return last;
		 **/
		Criteria criteria = session.createCriteria(User.class).setProjection(Projections.max("reff"));
		Integer lastreff = (Integer) criteria.uniqueResult();

		return lastreff;

	}

}
