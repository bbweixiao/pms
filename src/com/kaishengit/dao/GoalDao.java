package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Goal;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class GoalDao {

	private DBHelp<Goal> db = new DBHelp<Goal>();
	
	public List<Goal> findGoalsByProjectId(String projectid){
		String sql = ("select id,gname,gdesc,gcreatetime,projectid from t_goal where projectid=?");
		return db.executeQueryForList(sql, new goalRowMapper(), projectid);
	}
	
	private class goalRowMapper implements RowMapper<Goal>{

		public Goal mapperRow(ResultSet rs) throws SQLException {
			Goal g = new Goal();
			g.setId(rs.getString("id"));
			g.setGname(rs.getString("gname"));
			g.setGdesc(rs.getString("gdesc"));
			g.setGcreatetime(rs.getString("gcreatetime"));
			g.setProjectid(rs.getString("projectid"));
			return g;
		}
	}

	public void save(Goal g) {
		String sql = "INSERT INTO t_goal(id,gname,gdesc,gcreatetime,projectid) VALUE (?,?,?,?,?)";
		db.executeSQL(sql, g.getId(), g.getGname(), g.getGdesc(), g.getGcreatetime(), g.getProjectid());
	}

	public Goal findById(String id) {
		String sql = "select id,gname,gdesc,gcreatetime,projectid from t_goal where id=?";
		return db.executeQueryForObject(sql, new goalRowMapper(), id);
	}

	public void deleteById(String id) {
		String sql = "delete from t_goal where id=?";
		db.executeSQL(sql, id);
	}

	public void update(Goal goal) {
		String sql = "update t_goal set gname=?,gdesc=?,gcreatetime=?,projectid=? where id=?";
		db.executeSQL(sql, goal.getGname(), goal.getGdesc(), goal.getGcreatetime(), goal.getProjectid(), goal.getId());
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_goal where projectid=?";
		db.executeSQL(sql, pid);
	}
}
