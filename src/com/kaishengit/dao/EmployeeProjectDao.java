package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.EmployeeProject;
import com.kaishengit.entity.Project;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class EmployeeProjectDao {

	private DBHelp<EmployeeProject> db = new DBHelp<EmployeeProject>();
	
	public void saveEmployeeProject(EmployeeProject em){
		String sql = "insert into t_employee_project(employeeid,projectid,role) value (?,?,?)";
		db.executeSQL(sql, em.getEmployeeid(),em.getProjectid(),em.getRole());
	}

	public List<EmployeeProject> findEmployeeProjectsByEmployeeId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT employeeid,te.username,te.password,projectid,tp.pname,tp.pdesc,tp.pcreatetime,role FROM t_employee_project AS tep ");
		sql.append("LEFT JOIN t_employee AS te ON tep.employeeid = te.id ");
		sql.append("LEFT JOIN t_project AS tp ON tep.projectid = tp.id ");
		sql.append("where tep.employeeid = ?");
		return db.executeQueryForList(sql.toString(), new EmployeeProjectRowMapper(), id);
	}

	private class EmployeeProjectRowMapper implements RowMapper<EmployeeProject> {

		public EmployeeProject mapperRow(ResultSet rs) throws SQLException {
			EmployeeProject ep = new EmployeeProject();
			ep.setRole(rs.getString("role"));
			
			Employee e = new Employee();
			e.setId(rs.getString("employeeid"));
			e.setPassword(rs.getString("password"));
			e.setUsername(rs.getString("username"));
			
			ep.setEmployee(e);
			ep.setEmployeeid(rs.getString("employeeid"));
			
			Project p = new Project();
			p.setPcreatetime(rs.getString("pcreateTime"));
			p.setPdesc(rs.getString("pdesc"));
			p.setId(rs.getString("projectId"));
			p.setPname(rs.getString("pname"));
			
			ep.setProject(p);
			ep.setProjectid(rs.getString("projectid"));
			
			return ep;
		}
	}

	public List<EmployeeProject> findEmployeeProjectsByRoleAndPid(String role, String projectid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT employeeid,te.username,te.password,projectid,tp.pname,tp.pdesc,tp.pcreatetime,role FROM t_employee_project AS tep ");
		sql.append("LEFT JOIN t_employee AS te ON tep.employeeid = te.id ");
		sql.append("LEFT JOIN t_project AS tp ON tep.projectid = tp.id ");
		sql.append("where tep.role = ? and tep.projectid=?");
		return db.executeQueryForList(sql.toString(), new EmployeeProjectRowMapper(), role, projectid);
	}

	public EmployeeProject findEmployeeProjectByEidAndPid(String eid, String pid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT employeeid,te.username,te.password,projectid,tp.pname,tp.pdesc,tp.pcreatetime,role FROM t_employee_project AS tep ");
		sql.append("LEFT JOIN t_employee AS te ON tep.employeeid = te.id ");
		sql.append("LEFT JOIN t_project AS tp ON tep.projectid = tp.id ");
		sql.append("where tep.employeeid=? and tep.projectid=?");
		return db.executeQueryForObject(sql.toString(), new EmployeeProjectRowMapper(), eid, pid);
	}

	public void deleteByProjectId(String pid) {
		String sql = "delete from t_employee_project where projectid=?";
		db.executeSQL(sql, pid);
	}

	public EmployeeProject findEmployeeProjectByRoleAndPid(String role, String pid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT employeeid,te.username,te.password,projectid,tp.pname,tp.pdesc,tp.pcreatetime,role FROM t_employee_project AS tep ");
		sql.append("LEFT JOIN t_employee AS te ON tep.employeeid = te.id ");
		sql.append("LEFT JOIN t_project AS tp ON tep.projectid = tp.id ");
		sql.append("where tep.role = ? and tep.projectid=?");
		return db.executeQueryForObject(sql.toString(), new EmployeeProjectRowMapper(), role, pid);
	}
}
