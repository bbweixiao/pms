package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Idea;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class IdeaDao {

	private DBHelp<Idea> db = new DBHelp<Idea>();
	
	public void save(Idea idea) {
		String sql = "insert into t_idea(id,title,content,icreatetime,employeeid,projectid) value(?,?,?,?,?,?)";
		db.executeSQL(sql, idea.getId(), idea.getTitle(), idea.getContent(), idea.getIcreatetime(), idea.getEmployeeid(), idea.getProjectid());
	}

	public List<Idea> findIdeasByPid(String pid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ti.id,ti.title,ti.content,ti.icreatetime,employeeid,ti.projectid,te.username,te.password from t_idea as ti ");
		sql.append("LEFT JOIN t_employee AS te ON ti.employeeid = te.id ");
		sql.append("where ti.projectid=?");
		return db.executeQueryForList(sql.toString(), new IdeaRowMapper(), pid);
	}
	
	private class IdeaRowMapper implements RowMapper<Idea>{

		public Idea mapperRow(ResultSet rs) throws SQLException {
			Idea i = new Idea();
			i.setId(rs.getString("id"));
			i.setTitle(rs.getString("title"));
			i.setContent(rs.getString("content"));
			i.setIcreatetime(rs.getString("icreatetime"));
			i.setEmployeeid(rs.getString("employeeid"));
			i.setProjectid(rs.getString("projectid"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setUsername(rs.getString("username"));
			e.setPassword(rs.getString("password"));
			i.setEmployee(e);
			return i;
		}
	}

	public Idea findIdeaById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ti.id,ti.title,ti.content,ti.icreatetime,employeeid,ti.projectid,te.username,te.password from t_idea as ti ");
		sql.append("LEFT JOIN t_employee AS te ON ti.employeeid = te.id ");
		sql.append("where ti.id=?");
		return db.executeQueryForObject(sql.toString(), new IdeaRowMapper(), id);
	}

	public void deleteById(String id) {
		String sql = "delete from t_idea where id=?";
		db.executeSQL(sql, id);
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_idea where projectid=?";
		db.executeSQL(sql, pid);
	}
}
