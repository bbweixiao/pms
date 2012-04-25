package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;

public class MessageService {
	MessageDao dao=new MessageDao();
	
	public void save(Message message){
		dao.save(message);
	}
	public List<Message> find(String pid){
		return dao.findByProjectId(pid);
	}
	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}

}
