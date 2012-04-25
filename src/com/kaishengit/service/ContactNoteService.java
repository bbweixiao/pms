package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.ContactNoteDao;
import com.kaishengit.entity.ContactNote;

public class ContactNoteService {

	private ContactNoteDao dao = new ContactNoteDao();
	
	public List<ContactNote> findCNsByContactId(String contactid) {
		return dao.findCNsByContactId(contactid);
	}

	public void save(ContactNote contactNote) {
		dao.save(contactNote);
	}

	public void deleteByContactId(String cid) {
		dao.deleteByContactId(cid);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}
}
