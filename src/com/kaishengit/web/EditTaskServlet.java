package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Goal;
import com.kaishengit.entity.Task;
import com.kaishengit.service.TaskService;

public class EditTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			String tid = request.getParameter("tid");
			TaskService tservice = new TaskService();
			Task task = tservice.findTaskById(tid);
			if(task == null){
				response.sendRedirect("task.jspx?m=login&gid=" + goal.getId());
			}else{
				session.setAttribute("task", task);
				request.getRequestDispatcher("/WEB-INF/views/edittask.jsp").forward(request, response);
			}
		}
	}
}
