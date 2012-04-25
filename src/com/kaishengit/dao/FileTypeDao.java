package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.FileType;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class FileTypeDao {
	private DBHelp<FileType> db = new DBHelp<FileType>();
	public void save(FileType f) {
			String sql = "INSERT INTO t_filetype(id,ftname,ftcreatetime,employeeid,projectid,filetypesize) VALUE (?,?,?,?,?,?)";
			db.executeSQL(sql, f.getId(),f.getFtname(),f.getFtcreatetime(),f.getEmployeeid(),f.getProjectid(),f.getFiletypesize());
		
}
	public List<FileType> findAll() {
		StringBuilder sql = new StringBuilder(); 
		sql.append("select tft.id,ftname,ftcreatetime,employeeid,te.username,te.password,projectid,filetypesize from t_filetype AS tft ");
		sql.append("LEFT JOIN t_employee AS te ON tft.employeeid = te.id ");
		
		return db.executeQueryForList(sql.toString(), new fileTypeRowMapper());
	
	}
	
	private class fileTypeRowMapper implements RowMapper<FileType>{

		public FileType mapperRow(ResultSet rs) throws SQLException {
			FileType f = new FileType();
			f.setId(rs.getString("id"));
			f.setFtname(rs.getString("ftname"));
			f.setFtcreatetime(rs.getString("ftcreatetime"));
			f.setEmployeeid(rs.getString("employeeid"));
			f.setProjectid(rs.getString("projectid"));
			f.setFiletypesize(rs.getLong("filetypesize"));
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setPassword(rs.getString("password"));
			e.setUsername(rs.getString("username"));
			
			f.setEmployee(e);
			f.setEmployeeid(rs.getString("employeeid"));
			
			return f;
		}
	}

	public void delete(String filetypeid) {
		String sql="delete From t_filetype where id=?";
		db.executeSQL(sql, filetypeid);
		
	}
	public FileType findByid(String id) {
		StringBuilder sql = new StringBuilder(); 
		sql.append("select tft.id,ftname,ftcreatetime,employeeid,te.username,te.password,projectid,filetypesize from t_filetype AS tft ");
		sql.append("LEFT JOIN t_employee AS te ON tft.employeeid = te.id ");
		sql.append("where tft.id=?");
		return db.executeQueryForObject(sql.toString(), new fileTypeRowMapper(),id);
	
	}
	public void updateByid(Long filetypesize, String fileTypeid) {
		String sql="update t_filetype set filetypesize=? where id=?";
		db.executeSQL(sql,filetypesize,fileTypeid);
		
	}
	
	public List<FileType> findFileTypesByPid(String pid) {
		StringBuilder sql = new StringBuilder(); 
		sql.append("select tft.id,ftname,ftcreatetime,employeeid,te.username,te.password,projectid,filetypesize from t_filetype AS tft ");
		sql.append("LEFT JOIN t_employee AS te ON tft.employeeid = te.id ");
		sql.append("where tft.projectid=? ");
		return db.executeQueryForList(sql.toString(), new fileTypeRowMapper(), pid);
	}
	public void deleteByProjectId(String pid) {
		String sql = "delete from t_filetype where projectid=?";
		db.executeSQL(sql, pid);
	}
}
