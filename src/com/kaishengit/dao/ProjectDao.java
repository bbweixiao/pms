package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Project;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class ProjectDao {

	private DBHelp<Project> db = new DBHelp<Project>();
	
	public List<Project> findAllProjects(){
		String sql = "select id,pname,pdesc,pcreatetime from t_project";
		return db.executeQueryForList(sql, new ProjectRowMapper());
	}
	
	private class ProjectRowMapper implements RowMapper<Project>{

		public Project mapperRow(ResultSet rs) throws SQLException {
			Project p = new Project();
			p.setId(rs.getString("id"));
			p.setPname(rs.getString("pname"));
			p.setPdesc(rs.getString("pdesc"));
			p.setPcreatetime(rs.getString("pcreatetime"));
			return p;
		}
		
	}

	public void save(Project p) {
		String sql = "INSERT INTO t_project(id,pname,pdesc,pcreatetime) VALUE (?,?,?,?)";
		db.executeSQL(sql, p.getId(),p.getPname(),p.getPdesc(),p.getPcreatetime());
	}

	public Project findProjectById(String id) {
		String sql = "select id,pname,pdesc,pcreatetime from t_project where id=?";
		return db.executeQueryForObject(sql, new ProjectRowMapper(), id);
	}

	public void deleteById(String id) {
		String sql = "delete from t_project where id=?";
		db.executeSQL(sql, id);
	}

	public void update(Project p) {
		String sql = "update t_project set pname=?,pdesc=?,pcreatetime=? where id=?";
		db.executeSQL(sql, p.getPname(), p.getPdesc(), p.getPdesc(), p.getId());
	}
}
