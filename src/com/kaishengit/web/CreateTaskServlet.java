package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.EmployeeProject;
import com.kaishengit.entity.Goal;
import com.kaishengit.entity.Project;
import com.kaishengit.service.EmployeeProjectService;

public class CreateTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			EmployeeProjectService emservice = new EmployeeProjectService();
			Project p = (Project) session.getAttribute("project");
			List<EmployeeProject> staffList = emservice.findEPsByRoleAndProjectId("staff", p.getId());
			session.setAttribute("staffList", staffList);
			request.getRequestDispatcher("/WEB-INF/views/createtask.jsp").forward(request, response);
		}
	}
}
