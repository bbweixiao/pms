package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.ContactDao;
import com.kaishengit.entity.Contact;

public class ContactService {

	private ContactDao dao = new ContactDao();
	
	public List<Contact> findContactsByProjectId(String id) {
		return dao.findContactsByProjectId(id);
	}

	public void save(Contact contact) {
		dao.save(contact);
	}

	public Contact findContactById(String id) {
		return dao.findContactById(id);
	}

	public void update(Contact contact) {
		dao.update(contact);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}
}
