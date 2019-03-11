package com.ngs.dao.interfaces;

import java.util.List;

import com.ngs.model.Activities;
import com.ngs.model.RollHasActivities;
import com.ngs.model.Rolls;

public interface RollsDao {
	
	Rolls findByRollID(int roll);
	Activities getActivityByID(int activityID);
	RollHasActivities saveOrUpdate(RollHasActivities rha);
	List<Rolls> getAllRolls();
	
	boolean deleteAllRollHasActivities(Rolls roll);

	
	
	

}

