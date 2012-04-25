package com.kaishengit.service;

import java.util.List;
import com.kaishengit.dao.FileTypeDao;
import com.kaishengit.entity.FileType;

public class FileTypeService {
	FileTypeDao dao=new FileTypeDao();

	public void save(FileType f) {
		dao.save(f);
		
	}

	public List<FileType> findAll() {
		return dao.findAll();
	}
	public FileType findByid(String id){
		return dao.findByid(id);
	}

	public void delete(String filetypeid) {
		dao.delete(filetypeid);
		
	}

	public void updateByid(Long filetypesize, String fileTypeid) {		
		dao.updateByid(filetypesize,fileTypeid);
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}

}
