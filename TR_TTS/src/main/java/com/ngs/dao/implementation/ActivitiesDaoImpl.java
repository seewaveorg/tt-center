package com.ngs.dao.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ngs.dao.interfaces.ActivitiesDao;
import com.ngs.model.Activities;
import com.ngs.model.Category;
import com.ngs.model.RollHasActivities;


public class ActivitiesDaoImpl implements ActivitiesDao {

	private SessionFactory sessionFactory;




	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	@Transactional
	public List<Activities> findActivitiesByRollID(int rollid) {
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println("#################  check pint A");
		
		
		Query query = session.createQuery("from RollHasActivities as a where a.rolls = :roll and a.status = 1");// here should be something else than list()
		query.setInteger("roll", rollid);
		
		@SuppressWarnings("unchecked")
		List<RollHasActivities> RollActivitylist = query.list();
		//System.out.println("####################### RollActivitylist is"+RollActivitylist.toString());
		
		// declares an array of integers
        //Integer[] anArray;

        // allocates memory for 10 integers
       // anArray = new Integer[RollActivitylist.size()];

       // int[] act = new int[RollActivitylist.size()];
       
        
		//create a list include list of activities
		List<Activities> activitylist = new ArrayList<Activities>();

        for(RollHasActivities rollActivity : RollActivitylist) {
        		
        		System.out.println(" Activity Status is "+rollActivity.getActivities().getStatus());
        		System.out.println(" Activity Name is "+rollActivity.getActivities().getActivitiy() );
        		//System.out.println(" Activity desc is "+rollActivity.getActivities().get);
        		System.out.println(" Activity id is "+ rollActivity.getActivities().getId());
        		
        		// Now get the Activities object from by giving its id
        		//Activities acti = getActivityByID(rollActivity.getActivities().getId());
        		System.out.println("acti object is "+rollActivity.getActivities().getActivitiy());
        		
        		
        		// get only activie activities for menu creations 1 : menu acitve 3 :report active  0 :deactivated
        		if(rollActivity.getActivities().getStatus()==1){
        		activitylist.add(rollActivity.getActivities());
        		}
        		/**
        		//activityidlist = activityidlist+rollActivity.getActivities().getId();
        		//StringActivities.add(rollActivity.getActivities().getActivitiy() );
        		if(rollActivity.getActivities()==null){
        			System.out.println("object is null");
        		}
        		**/
        }
        
       // List Arrays.stream(act).boxed().collect(Collectors.toList());
	
        /**
		//List<Integer> ids = Arrays.asList(anArray);
		List<Integer> ids = new ArrayList<Integer>(Arrays.asList(anArray));
		Query innerQuery = session.createQuery("FROM Activities as a WHERE a.id IN (:ids)");
		innerQuery.setParameter("ids", ids);
		@SuppressWarnings("unchecked")
		List<Activities> activities =  innerQuery.list();
		**/
        
		return activitylist;
	}
	
	

	@Override
	@Transactional
	public Activities getActivityByID(int activityID) {
		//Now get the activity list which has the activity ids
		Session session = sessionFactory.getCurrentSession();
		Activities activity = (Activities) session.load(Activities.class, activityID);
		return activity;
	}

	@Override
	@Transactional
	public String[] findActivitiesStringByRollID(int rollid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RollHasActivities as a where a.rolls = :roll");// here should be something else than list()
		query.setInteger("roll", rollid);
	
		@SuppressWarnings("unchecked")
		List<RollHasActivities> RollActivitylist = query.list();
		
		String[] StringActivities = new String[RollActivitylist.size()];
		
		Activities acti =null;
		int tem = 0;
        for(RollHasActivities rollActivity : RollActivitylist) {
        	StringActivities[tem] = rollActivity.getActivities().getActivitiy();
    	    tem = tem +1;
        		
        		// Now get the Activities object from by giving its id
        		acti = getActivityByID(rollActivity.getActivities().getId());
        		System.out.println("acti object is "+acti.getActivitiy());
       
        		if(rollActivity.getActivities()==null){
        			System.out.println("object is null for string");
        		}
        }
		
		return StringActivities;
	}





	@Override
	@Transactional
	public List<Activities> getAllActivities() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Activities.class);
		criteria.add(Restrictions.eq("status", 1));
		return criteria.list();
		
	}



	@Override
	@Transactional
	public List<Category> getAllCategories() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Category.class);
		
		return criteria.list();
	}



	@Override
	@Transactional
	public List<Activities> findReportsByRollID(int rollid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RollHasActivities.class);
		criteria.add(Restrictions.eq("status", 3));
		criteria.add(Restrictions.eq("rolls.id", rollid));
		List<RollHasActivities> RollActivitylist = criteria.list();
	
		List<Activities> activityList = new ArrayList();
	
        for(RollHasActivities rollhasactivity : RollActivitylist) {
        	// category 9 is Reports
        	if( rollhasactivity.getActivities().getCategory().getId() == 9 && rollhasactivity.getActivities().getStatus() ==1 ){
        		activityList.add(rollhasactivity.getActivities());
        	}
        	
        }
		return activityList;
	}
	



	
}
