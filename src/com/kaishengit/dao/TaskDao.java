package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Task;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class TaskDao {

	private DBHelp<Task> db = new DBHelp<Task>();
	
	public List<Task> findTasksByGoalId(String goalid){
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id,tt.tname,tt.tdesc,tt.state,tt.level,tt.begintime,tt.endtime,tt.tcreatetime,employeeid,tt.goalid,tt.rate,te.username,te.password from t_task  AS tt ");
		sql.append("LEFT JOIN t_employee AS te ON tt.employeeid = te.id ");
		sql.append("where tt.goalid=?");
		return db.executeQueryForList(sql.toString(), new TaskRowMapper(), goalid);
	}
	
	public Task findTaskById(String id){
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id,tt.tname,tt.tdesc,tt.state,tt.level,tt.begintime,tt.endtime,tt.tcreatetime,employeeid,tt.goalid,tt.rate,te.username,te.password from t_task  AS tt ");
		sql.append("LEFT JOIN t_employee AS te ON tt.employeeid = te.id ");
		sql.append("where tt.id=?");
		return db.executeQueryForObject(sql.toString(), new TaskRowMapper(), id);
	}
	
	private class TaskRowMapper implements RowMapper<Task>{

		public Task mapperRow(ResultSet rs) throws SQLException {
			Task t = new Task();
			t.setId(rs.getString("id"));
			t.setTname(rs.getString("tname"));
			t.setTdesc(rs.getString("tdesc"));
			t.setState(rs.getString("state"));
			t.setLevel(rs.getString("level"));
			t.setBegintime(rs.getString("begintime"));
			t.setEndtime(rs.getString("endtime"));
			t.setTcreatetime(rs.getString("tcreatetime"));
			t.setEmployeeid(rs.getString("employeeid"));
			t.setGoalid(rs.getString("goalid"));
			t.setRate(rs.getInt("rate"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setUsername(rs.getString("username"));
			e.setPassword(rs.getString("password"));
			
			t.setEmployee(e);
			
			return t;
		}
	}

	public void save(Task task) {
		String sql = "insert into t_task(id,tname,tdesc,state,level,begintime,endtime,tcreatetime,employeeid,goalid,rate) value (?,?,?,?,?,?,?,?,?,?,?)";
		db.executeSQL(sql, task.getId(), task.getTname(), task.getTdesc(), task.getState(), task.getLevel(), task.getBegintime(), task.getEndtime(),task.getTcreatetime(), task.getEmployeeid(),task.getGoalid(), task.getRate());
	}

	public void updateById(Task task) {
		String sql = "UPDATE t_task SET tname=?,tdesc=?,state=?,level=?,begintime=?,endtime=?,tcreatetime=?,employeeid=?,goalid=?,rate=? WHERE id=?";
		db.executeSQL(sql, task.getTname(), task.getTdesc(), task.getState(), task.getLevel(), task.getBegintime(), task.getEndtime(),task.getTcreatetime(), task.getEmployeeid(),task.getGoalid(), task.getRate(), task.getId());
	}

	public void deleteByGoalId(String goalId) {
		String sql = "delete from t_task where goalid=?";
		db.executeSQL(sql, goalId);
	}

	public void deleteById(String id) {
		String sql = "delete from t_task where id=?";
		db.executeSQL(sql, id);
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_task where goalid in(select id from t_goal where projectid =?)";
		db.executeSQL(sql.toString(), pid);
	}

	public List<Task> findTasksByPidAndEid(String projectid, String employeeid){
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id,tt.tname,tt.tdesc,tt.state,tt.level,tt.begintime,tt.endtime,tt.tcreatetime,employeeid,tt.goalid,tt.rate,te.username,te.password from t_task  AS tt ");
		sql.append("LEFT JOIN t_employee AS te ON tt.employeeid = te.id ");
		sql.append("where tt.employeeid=? and tt.goalid in(");
		sql.append("select id from t_goal where projectid=?)");
		return db.executeQueryForList(sql.toString(), new TaskRowMapper(), employeeid, projectid);
	}
}
