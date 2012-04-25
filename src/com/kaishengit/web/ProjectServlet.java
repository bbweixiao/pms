package com.kaishengit.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.EmployeeProject;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Task;
import com.kaishengit.service.EmployeeProjectService;
import com.kaishengit.service.MessageService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.service.TaskService;

public class ProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		if(employee == null){
			response.sendRedirect("index.jspx");
		}else{
			login(request, response);
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		String pid  = request.getParameter("pid");
		ProjectService pservice = new ProjectService();
		Project project = pservice.findProjectById(pid);
		if(project == null){
			response.sendError(404, "查找的内容不存在！");
		}else{
			EmployeeProjectService epservice = new EmployeeProjectService();
			EmployeeProject ep = epservice.findEmployeeProjectByEidAndPid(employee.getId(), pid);
			
			if(ep == null){
				response.sendRedirect("main.jspx?m=login");
			}else{
				MessageService mservice = new MessageService();
				List<Message> list = mservice.find(pid);
				session.setAttribute("list", list);
				
				TaskService tservice = new TaskService();
				List<Task> ptasklist = tservice.findByPidAndEid(pid, employee.getId()); 
				session.setAttribute("ptaskList", ptasklist);
				
				session.setAttribute("project", project);
				request.getRequestDispatcher("/WEB-INF/views/project.jsp").forward(request, response);
			}
		}
	}
}
