package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.EmployeeProject;
import com.kaishengit.entity.Project;
import com.kaishengit.service.EmployeeProjectService;
import com.kaishengit.service.ProjectService;

public class EditProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		if(employee == null){
			response.sendRedirect("index.jspx");
		}else{
			String pid = request.getParameter("pid");
			ProjectService pservice = new ProjectService();
			Project project = pservice.findProjectById(pid);
			if(project == null){
				response.sendRedirect("main.jspx?m=login");
			}else{
				session.setAttribute("project", project);
				EmployeeProjectService epservice = new EmployeeProjectService();
				EmployeeProject ep = epservice.findEPByRoleAndProjectId("manager", pid);
				request.setAttribute("ep", ep);
				request.getRequestDispatcher("/WEB-INF/views/editproject.jsp").forward(request, response);
			}
		}
	}
}
