package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;

public class FileType {
	private String id;
	private String ftname;
	private Employee employee;
	private String ftcreatetime;
	private String employeeid;
	private String projectid;
	private Long filetypesize;
	
	

	public Long getFiletypesize() {
		return filetypesize;
	}
	public void setFiletypesize(Long filetypesize) {
		this.filetypesize = filetypesize;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getFtcreatetime() {
		return ftcreatetime;
	}
	public void setFtcreatetime(String ftcreatetime) {
		this.ftcreatetime = ftcreatetime;
	}
	public String getTime(){
		return DateUtil.getTime(getFtcreatetime());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFtname() {
		return ftname;
	}
	public void setFtname(String ftname) {
		this.ftname = ftname;
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
