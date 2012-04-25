package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.EmployeeDao;
import com.kaishengit.entity.Employee;

public class EmployeeService {
	EmployeeDao dao = new EmployeeDao();

	public Employee login(String username, String password) {
		return dao.findByUserNameAndPassword(username,password);
	}

	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	public Employee findManagerByProjectId(String pid) {
		return dao.findManagerByProjectId(pid);
	}

	public Employee findById(String id) {
		return dao.findById(id);
	}

	public void repassword(String username, String password) {
		dao.repassword(username,password);
	}
	
	public Employee findByUsername(String str) {
		return dao.findByUsername( str);
	}
	
	public void save(Employee e){
		dao.save(e);
	}
}
