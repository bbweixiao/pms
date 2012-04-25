package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.File;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class FileDao {
	private DBHelp<File> db = new DBHelp<File>();
	public void save(File f) {
			String sql = "INSERT INTO t_file(id,fname,filename,filesize,fcreatetime,fileTypeid,employeeid) VALUE (?,?,?,?,?,?,?)";
			db.executeSQL(sql, f.getId(),f.getFname(),f.getFilename(),f.getFilesize(),f.getFcreatetime(),f.getFileTypeid(),f.getEmployeeid());
		
	}
	public List<File> findFilesByFileTypeid(String fileTypeid) {
		StringBuilder sql=new StringBuilder();
		sql.append("select tf.id,fname,filename,filesize,fcreatetime,fileTypeid,employeeid,te.username,te.password from t_file AS tf ");
		sql.append("LEFT JOIN t_employee AS te ON tf.employeeid=te.id ");
		sql.append("WHERE  tf.fileTypeid=? ");
		
		return db.executeQueryForList(sql.toString(), new fileRowMapper(), fileTypeid);
	
	}
	
	private class fileRowMapper implements RowMapper<File>{

		public File mapperRow(ResultSet rs) throws SQLException {
			File f = new File();
			f.setId(rs.getString("id"));
			f.setFname(rs.getString("fname"));
			f.setFilename(rs.getString("filename"));
			f.setFilesize(rs.getLong("filesize"));
			f.setFcreatetime(rs.getString("fcreatetime"));
			f.setFileTypeid(rs.getString("fileTypeid"));
			
			Employee e=new Employee();
			e.setId(rs.getString("employeeid"));
			e.setUsername(rs.getString("username"));
			e.setPassword(rs.getString("password"));
			
			f.setEmployee(e);
			f.setEmployeeid(rs.getString("employeeid"));
			return f;
		}
	}

	public void delete(String fileid) {
		String sql="delete From t_file where id=?";
		db.executeSQL(sql, fileid);
		
	}
	public File findById(String fileid) {
		StringBuilder sql=new StringBuilder();
		sql.append("select tf.id,tf.fname,filename,filesize,fcreatetime,fileTypeid,employeeid,te.username,te.password from t_file as tf ");
		sql.append("LEFT JOIN t_employee AS te ON tf.employeeid=te.id ");
		sql.append("WHERE  tf.id=? ");
		return db.executeQueryForObject(sql.toString(), new fileRowMapper(), fileid);
	}
	public void deleteByFileTypeId(String filetypeid) {
		
		String sql="delete From t_file where fileTypeid=?";
		db.executeSQL(sql, filetypeid);
	}
	public void deleteByProjectId(String pid) {
		String sql = "delete from t_file where fileTypeid in (select id from t_filetype where projectid=?)";
		db.executeSQL(sql, pid);
	}

}
