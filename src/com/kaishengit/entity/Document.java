package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;

public class Document {
	private String id;
	private String title;
	private String content;
	private String dcreatetime;
	private String employeeid;
	private String projectid;
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
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
	public String getDcreatetime() {
		return dcreatetime;
	}
	public void setDcreatetime(String dcreattime) {
		this.dcreatetime = dcreattime;
	}
	
	public String getTime(){
		return DateUtil.getTime(getDcreatetime());
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
	
	

}
