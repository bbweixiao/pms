package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.GoalDao;
import com.kaishengit.entity.Goal;

public class GoalService {

	private GoalDao dao = new GoalDao();
	
	public List<Goal> findGoalsByProjectId(String projectid){
		return dao.findGoalsByProjectId(projectid);
	}

	public void save(Goal g) {
		dao.save(g);
	}

	public Goal findGoalById(String id) {
		return dao.findById(id);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void update(Goal goal) {
		dao.update(goal);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}
}
