package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.EmployeeProjectDao;
import com.kaishengit.entity.EmployeeProject;

public class EmployeeProjectService {

	private EmployeeProjectDao dao = new EmployeeProjectDao();
	public void saveEmployeeProject(EmployeeProject ep) {
		dao.saveEmployeeProject(ep);
	}
	public List<EmployeeProject> findEmployeeProjectsByEmployeeId(String id) {
		return dao.findEmployeeProjectsByEmployeeId(id);
	}
	public List<EmployeeProject> findEPsByRoleAndProjectId(String role, String pid) {
		return dao.findEmployeeProjectsByRoleAndPid(role, pid);
	}
	public EmployeeProject findEmployeeProjectByEidAndPid(String eid, String pid) {
		return dao.findEmployeeProjectByEidAndPid(eid, pid);
	}
	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}
	public EmployeeProject findEPByRoleAndProjectId(String role, String pid) {
		return dao.findEmployeeProjectByRoleAndPid(role, pid);
	}

	
}
