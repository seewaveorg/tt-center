package com.ngs.dao.interfaces;

import java.util.List;


import com.ngs.model.Activities;
import com.ngs.model.Category;

public interface ActivitiesDao {
	
	List<Activities> findActivitiesByRollID(int rollid);
	List<Activities> findReportsByRollID(int rollid);
	List<Activities> getAllActivities();
	List<Category> getAllCategories();
	String[] findActivitiesStringByRollID(int rollid);
	Activities getActivityByID(int activityID);

}
