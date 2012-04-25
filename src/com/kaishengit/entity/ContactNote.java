package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;

public class ContactNote {

	private String id;
	private String content;
	private String createtime;
	private String contactid;
	private String employeeid;
	private Employee employee;
	
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTime(){
		return DateUtil.getTime(getCreatetime());
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
}
