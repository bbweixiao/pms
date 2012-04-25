package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.TaskDao;
import com.kaishengit.entity.Task;

public class TaskService {

	private TaskDao dao = new TaskDao();
	
	public List<Task> findTasksByGoalId(String gid){
		return dao.findTasksByGoalId(gid);
	}
	
	public Task findTaskById(String id){
		return dao.findTaskById(id);
	}

	public void save(Task task) {
		dao.save(task);
	}

	public void update(Task task) {
		dao.updateById(task);
	}

	public void deleteByGoalId(String goalId) {
		dao.deleteByGoalId(goalId);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}

	public List<Task> findByPidAndEid(String pid, String eid) {
		return dao.findTasksByPidAndEid(pid, eid);
	}
}
