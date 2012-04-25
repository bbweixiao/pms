package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.ProjectDao;
import com.kaishengit.entity.Project;

public class ProjectService {

	ProjectDao dao = new ProjectDao();
	
	public List<Project> findAllProjects(){
		return dao.findAllProjects();
	}
	public void saveProject(Project p) {
		dao.save(p);
	}
	public Project findProjectById(String id) {
		return dao.findProjectById(id);
	}
	public void deleteById(String id) {
		dao.deleteById(id);
	}
	public void update(Project p) {
		dao.update(p);
	}
}
