package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.IdeaDao;
import com.kaishengit.entity.Idea;

public class IdeaService {

	private IdeaDao dao = new IdeaDao();
	
	public void save(Idea idea) {
		dao.save(idea);
	}

	public List<Idea> findIdeasByPid(String Pid) {
		return dao.findIdeasByPid(Pid);
	}

	public Idea findIdeaById(String id) {
		return dao.findIdeaById(id);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}

}
