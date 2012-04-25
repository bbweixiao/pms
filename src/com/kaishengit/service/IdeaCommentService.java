package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.IdeaCommentDao;
import com.kaishengit.entity.IdeaComment;

public class IdeaCommentService {

	private IdeaCommentDao dao = new IdeaCommentDao();
	
	public List<IdeaComment> findICsListByIdeaId(String ideaId) {
		return dao.finICsByIdeaId(ideaId);
	}

	public void save(IdeaComment ic) {
		dao.save(ic);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void deleteByIdeaId(String ideaId) {
		dao.deleteByIdeaId(ideaId);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}

}
