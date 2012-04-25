package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.kaishengit.entity.Employee;
import com.kaishengit.util.DBHelp;
import com.kaishengit.util.RowMapper;

public class EmployeeDao {

	private DBHelp<Employee> db = new DBHelp<Employee>();

	public Employee findByUserNameAndPassword(String username, String password) {
		String sql = "SELECT id,username,`password` FROM t_employee WHERE username = ? AND `password` = ?";
		return db.executeQueryForObject(sql, new employeeRowMapper(), username,password);
	}
	
	
	private class employeeRowMapper implements RowMapper<Employee> {

		public Employee mapperRow(ResultSet rs) throws SQLException {
			Employee a = new Employee();
			
			a.setId(rs.getString("id"));
			a.setPassword(rs.getString("password"));
			a.setUsername(rs.getString("username"));
			return a;
		}
		
	}


	public void update(Employee employee) {
		String sql = "update t_employee set username = ?,password=? where id = ?";
		db.executeSQL(sql, employee.getUsername(),employee.getPassword(),employee.getId());
	}


	public Employee findById(String id) {
		String sql = "SELECT id,username,`password` FROM t_employee WHERE id = ?";
		return db.executeQueryForObject(sql, new employeeRowMapper(), id);
	}


	public List<Employee> findAllEmployees() {
		String sql = "SELECT id,username,`password` FROM t_employee";
		return db.executeQueryForList(sql, new employeeRowMapper());
	}


	public Employee findManagerByProjectId(String pid) {
		String sql = "SELECT id,username,`password` FROM t_employee WHERE id=(SELECT employeeid FROM t_employee_project WHERE role='manager' AND projectid=?)";
		return db.executeQueryForObject(sql, new employeeRowMapper(), pid);
	}

	public void repassword(String username,String password) {
		String sql = "update t_employee set password=? where username = ?";
		db.executeSQL(sql,password,username);
	}
	
	public Employee findByUsername(String username) {
		String sql = "SELECT id,username,`password` FROM t_employee WHERE username = ?";
		return db.executeQueryForObject(sql, new employeeRowMapper(), username);
	}


	public void save(Employee e) {
		String sql="insert into t_employee (id,username,password) values (?,?,?)";
		db.executeSQL(sql, e.getId(),e.getUsername(),e.getPassword());
	}

}
