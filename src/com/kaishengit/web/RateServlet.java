package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Goal;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Task;
import com.kaishengit.service.GoalService;
import com.kaishengit.service.MessageService;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class RateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String m = request.getParameter("m");
		if("login".equals(m) || m == null){
			login(request, response);
		}else if("update".equals(m)){
			if("POST".equals(request.getMethod())){
				update(request, response);
			}else{
				response.sendError(405);
			}
		}else if("side".equals(m)){
			sideLogin(request, response);
		}else{
			response.sendError(404, "参数错误！");
		}
	}
	
	private void sideLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String gid = request.getParameter("gid");
			String tid = request.getParameter("tid");
			
			GoalService gservice = new GoalService();
			Goal goal = gservice.findGoalById(gid);
			session.setAttribute("goal", goal);
			
			response.sendRedirect("rate.jspx?m=login&tid=" + tid);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		Task task = (Task) session.getAttribute("task");
		
		if(task == null){
			response.sendRedirect("task.jspx?m=login");
		}else{
			String r = request.getParameter("rate");
			int rate = Integer.parseInt(r);
			
			Task t = new Task();
			t.setId(task.getId());
			t.setTname(task.getTname());
			t.setTdesc(task.getTdesc());
			t.setBegintime(task.getBegintime());
			t.setEndtime(task.getEndtime());
			t.setTcreatetime(task.getTcreatetime());
			if(rate == 0){
				t.setState("新任务");
			}else if(rate > 0 && rate < 100){
				t.setState("进行中");
			}else if(rate == 100){
				t.setState("已完成");
			}
			t.setLevel(task.getLevel());
			t.setEmployeeid(task.getEmployeeid());
			t.setEmployee(task.getEmployee());
			t.setGoalid(task.getGoalid());
			t.setRate(rate);
			
			TaskService tservice = new TaskService();
			tservice.update(t);
			
			Project project = (Project) session.getAttribute("project");
			Employee employee = (Employee) session.getAttribute("employee");
			Message m=new Message();
			m.setId(PKUtil.getId());
			m.setContent("修改了任务："+task.getTname() + "的进度");
			m.setLink("rate.jspx?m=side&tid=" + task.getId() + "&gid=" + task.getGoalid());
			m.setMcreatetime(DateUtil.getNow());
			m.setProjectid(project.getId());
			m.setEmployeeid(employee.getId());
			
			MessageService mservice=new MessageService();
			mservice.save(m);
			
			response.sendRedirect("rate.jspx?m=login&tid=" + task.getId());
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = request.getParameter("tid");
		TaskService service = new TaskService();
		Task task = service.findTaskById(id);
		if(task == null){
			response.sendError(404, "查找的数据不存在!");
		}else{
			session.setAttribute("task", task);
			request.getRequestDispatcher("/WEB-INF/views/rate.jsp").forward(request, response);
		}
	}
}
