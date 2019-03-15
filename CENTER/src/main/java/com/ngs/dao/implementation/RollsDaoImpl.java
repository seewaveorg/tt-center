package com.ngs.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.dao.interfaces.RollsDao;
import com.ngs.model.Activities;
import com.ngs.model.Login;
import com.ngs.model.RollHasActivities;
import com.ngs.model.Rolls;

public class RollsDaoImpl implements RollsDao {
	
	private SessionFactory sessionFactory;
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//@SuppressWarnings("unchecked")

	@Override
	@Transactional
	public Rolls findByRollID(int roll) {

		List<Rolls> rolls = new ArrayList<Rolls>();

		rolls  = getSessionFactory().getCurrentSession()
			.createQuery("from Rolls where id=?")
			.setParameter(0, roll).list();

		if (rolls.size() > 0) {

			return rolls.get(0);
			
		} else {
			return null;
		}
	}


	@Override
	@Transactional
	public List<Rolls> getAllRolls() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Rolls.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public RollHasActivities saveOrUpdate(RollHasActivities rha) {
		Session session = sessionFactory.getCurrentSession();
		
		if(rha.getId() == null){
			session.save(rha);
		}else{
			session.update(rha);
		}
		return rha;
	}

	@Override
	@Transactional
	public boolean deleteAllRollHasActivities(Rolls roll) {
		boolean tem = false;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RollHasActivities.class);
		criteria.add(Restrictions.eq("rolls", roll));
		List<RollHasActivities> rolls = criteria.list();
		for(RollHasActivities rollobject:rolls){
			session.delete(rollobject);
			tem =true;
		}
		return tem;
	}



	@Override
	@Transactional
	public Activities getActivityByID(int activityID) {
		Session session = sessionFactory.getCurrentSession();
		
		return (Activities) session.load(Activities.class, activityID);
	}


	


}
