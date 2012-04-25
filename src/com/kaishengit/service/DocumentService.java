package com.kaishengit.service;

import com.kaishengit.dao.DocumentDao;
import com.kaishengit.entity.Document;
import java.util.List;


public class DocumentService {
	DocumentDao dao=new DocumentDao();
	public void save(Document doc) {
		dao.save(doc);
	}

	public List<Document> findDocumentsByProjectId(String projectid) {
		return dao.findDocumentsByProjectId(projectid);
	}

	public void delete(String id) {
		dao.delete(id);
		
	}

	public Document findDocumentById(String id) {
		return dao.findDocumentById(id);
	}

	public void edit(Document d) {
		dao.edit(d);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteBuProjectId(pid);
	}
}
