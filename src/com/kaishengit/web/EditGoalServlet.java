package com.kaishengit.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Goal;
import com.kaishengit.entity.Project;
import com.kaishengit.service.GoalService;

public class EditGoalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String gid = request.getParameter("gid");
			GoalService gservice = new GoalService();
			Goal goal = gservice.findGoalById(gid);
			if(goal == null){
				response.sendRedirect("goal.jspx?m=login");
			}else{
				session.setAttribute("goal", goal);
				request.getRequestDispatcher("/WEB-INF/views/editgoal.jsp").forward(request, response);
			}
		}
	}
}
