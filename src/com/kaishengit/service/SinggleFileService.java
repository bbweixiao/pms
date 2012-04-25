package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.FileDao;
import com.kaishengit.entity.File;

public class SinggleFileService {
	FileDao dao=new FileDao();

	public void save(File f) {
		dao.save(f);
		
	}

	public List<File> findFilesByFileTypeid(String fileTypeid) {
		return dao.findFilesByFileTypeid(fileTypeid);
	}

	public void delete(String fileid) {
		dao.delete(fileid);
		
	}



	public File findFileById(String fileid) {
		return dao.findById(fileid);
	}

	public void deleteByFileTypeId(String filetypeid) {
		dao.deleteByFileTypeId(filetypeid);
		
	}

	public void deleteByProjectId(String pid) {
		dao.deleteByProjectId(pid);
	}

}
