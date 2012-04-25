package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;

public class Message {
	private String id;
	private String content;
	private String mcreatetime;
	private String projectid;
	private String employeeid;
	private Employee employee;
	private String link;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getMcreatetime() {
		return mcreatetime;
	}
	public String getTime(){
		return DateUtil.getTime(getMcreatetime());
	}
	public void setMcreatetime(String mcreatetime) {
		this.mcreatetime = mcreatetime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	
	
}
