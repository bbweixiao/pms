package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;

public class IdeaComment {

	private String id;
	private String content;
	private String createtime;
	private String employeeid;
	private Employee employee;
	private String ideaid;
	
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
	public String getTime(){
		return DateUtil.getTime(getCreatetime());
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getIdeaid() {
		return ideaid;
	}
	public void setIdeaid(String ideaid) {
		this.ideaid = ideaid;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
}
