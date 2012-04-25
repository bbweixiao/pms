package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;

public class Idea {

	private String id;
	private String title;
	private String content;
	private String icreatetime;
	private String employeeid;
	private Employee employee;
	private String projectid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIcreatetime() {
		return icreatetime;
	}
	public String getTime(){
		return DateUtil.getTime(getIcreatetime());
	}
	public void setIcreatetime(String icreatetime) {
		this.icreatetime = icreatetime;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
}
