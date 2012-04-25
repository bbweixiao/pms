package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Goal;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.GoalService;
import com.kaishengit.service.MessageService;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class GoalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Project project = (Project)session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String m = request.getParameter("m");
			if("login".equals(m) || m == null){
				login(request, response);
			}else if("addGoal".equals(m)){
				if("POST".equals(request.getMethod())){
					addGoal(request,response);
				}else{
					response.sendError(405);
				}
			}else if("editGoal".equals(m)){
				if("POST".equals(request.getMethod())){
					editGoal(request, response);
				}else{
					response.sendError(405);
				}
			}else if("deleteGoal".equals(m)){
				deleteGoal(request, response);
			}else{
				response.sendError(404,"参数错误！");
			}
		}
	}
	private void editGoal(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			String gdesc = request.getParameter("gdesc");
			
			Goal g = new Goal();
			g.setId(goal.getId());
			g.setGname(goal.getGname());
			g.setGdesc(gdesc);
			g.setProjectid(goal.getProjectid());
			g.setGcreatetime(goal.getGcreatetime());
			GoalService gservice = new GoalService(); 
			gservice.update(g);
			
			Project project = (Project)session.getAttribute("project");
			Employee e=(Employee) session.getAttribute("employee");
			Message m=new Message();
			m.setId(PKUtil.getId());
			m.setContent("修改了目标："+goal.getGname());
			m.setLink("");
			m.setMcreatetime(DateUtil.getNow());
			m.setProjectid(project.getId());
			m.setEmployeeid(e.getId());
			
			MessageService mservice=new MessageService();
			mservice.save(m);
			
			response.sendRedirect("goal.jspx?m=login");
		}
	}
	private void deleteGoal(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String gid = request.getParameter("gid");
		TaskService tservice = new TaskService();
		GoalService gservice = new GoalService();
		tservice.deleteByGoalId(gid);
		gservice.deleteById(gid);
		response.sendRedirect("goal.jspx?m=login");
	}
	private void addGoal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String gname = request.getParameter("gname");
		String gdesc = request.getParameter("gdesc");
		
		HttpSession session = request.getSession();
		Project project = (Project)session.getAttribute("project");
		
		Goal g = new Goal();
		g.setId(PKUtil.getId());
		g.setGname(gname);
		g.setGdesc(gdesc);
		g.setProjectid(project.getId());
		g.setGcreatetime(DateUtil.getNow());
		GoalService gservice = new GoalService();
		gservice.save(g);
		
		Employee e=(Employee) session.getAttribute("employee");
		
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("创建了新目标："+gname);
		m.setLink("");
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(e.getId());
		
		MessageService mservice=new MessageService();
		mservice.save(m);
		
		response.sendRedirect("goal.jspx?m=login");
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Project p = (Project) session.getAttribute("project");
		
		GoalService gservice = new GoalService();
		List<Goal> goalList = gservice.findGoalsByProjectId(p.getId());
		session.setAttribute("goalList", goalList);
		
		request.getRequestDispatcher("/WEB-INF/views/goal.jsp").forward(request, response);
	}
}
