package com.kaishengit.entity;

import com.kaishengit.util.DateUtil;
import com.kaishengit.util.SuffixUtil;

public class File {
	private String id;
	private String fname;
	private String filename;
	private Long filesize;
	private String fcreatetime;
	private String fileTypeid;
	private String employeeid;
	private Employee employee;
	
	
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getSuffix(){
		return SuffixUtil.getSuffix(getFilename());
	}
	public String getFcreatetime() {
		return fcreatetime;
	}
	public void setFcreatetime(String fcreatetime) {
		this.fcreatetime = fcreatetime;
	}
	public String getTime(){
		return DateUtil.getTime(getFcreatetime());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileTypeid() {
		return fileTypeid;
	}
	public void setFileTypeid(String fileTypeid) {
		this.fileTypeid = fileTypeid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	
	
	

}
