package com.kaishengit.entity;

public class Task {

	private String id;
	private String tname;
	private String tdesc;
	private String state;
	private String level;
	private String begintime;
	private String endtime;
	private String tcreatetime;
	private int rate = 0;
	private String employeeid;
	private Employee employee;
	private String goalid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTdesc() {
		return tdesc;
	}
	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTcreatetime() {
		return tcreatetime;
	}
	public void setTcreatetime(String tcreatetime) {
		this.tcreatetime = tcreatetime;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getGoalid() {
		return goalid;
	}
	public void setGoalid(String goalid) {
		this.goalid = goalid;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getRate() {
		return rate;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
}
