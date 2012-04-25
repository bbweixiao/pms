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
import com.kaishengit.entity.Task;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.service.GoalService;
import com.kaishengit.service.MessageService;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class TaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String m = (String) request.getParameter("m");
			if("login".equals(m) || m == null){
				login(request, response);
			}else if("addTask".equals(m)){
				if("POST".equals(request.getMethod())){
					addTask(request,response);
				}else{
					response.sendError(405);
				}
			}else if("editTask".equals(m)){
				if("POST".equals(request.getMethod())){
					editTask(request,response);
				}else{
					response.sendError(405);
				}
			}else if("deleteTask".equals(m)){
				deleteTask(request, response);
			}else{
				response.sendError(404,	"参数错误");
			}
		}
	}
	private void deleteTask(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			String tid = request.getParameter("tid");
			TaskService tservice = new TaskService();
			tservice.deleteById(tid);
			response.sendRedirect("task.jspx?m=login&gid=" + goal.getId());
		}
	}
	private void editTask(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			Task task = (Task) session.getAttribute("task");
			if(task == null){
				response.sendRedirect("task.jspx?m=login&gid=" + goal.getId());
			}else{
				String tdesc = request.getParameter("tdesc");
				String begintime = request.getParameter("begintime");
				String endtime = request.getParameter("endtime");
				String level = request.getParameter("level");
				Task t = new Task();
				t.setId(task.getId());
				t.setTname(task.getTname());
				t.setTdesc(tdesc);
				t.setTcreatetime(task.getTcreatetime());
				t.setBegintime(begintime);
				t.setEndtime(endtime);
				t.setEmployeeid(task.getEmployeeid());
				t.setEmployee(task.getEmployee());
				t.setGoalid(task.getGoalid());
				t.setLevel(level);
				t.setState(task.getState());
				t.setRate(task.getRate());
				TaskService tservice = new TaskService();
				tservice.update(t);
				
				Employee e=(Employee) session.getAttribute("employee");
				Project p=(Project) session.getAttribute("project");
				Message m=new Message();
				m.setId(PKUtil.getId());
				m.setContent("修改任务："+task.getTname());
				m.setLink("");
				m.setMcreatetime(DateUtil.getNow());
				m.setProjectid(p.getId());
				m.setEmployeeid(e.getId());
				MessageService mservice=new MessageService();
				mservice.save(m);
				
				response.sendRedirect("task.jspx?m=login&gid=" + task.getGoalid());
			}
		}
	}
	private void addTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			String tname = request.getParameter("tname");
			String tdesc = request.getParameter("tdesc");
			String begintime = request.getParameter("begintime");
			String endtime = request.getParameter("endtime");
			String level = request.getParameter("level");
			String taskManager = request.getParameter("taskManager");
			
			Task task = new Task();
			task.setId(PKUtil.getId());
			task.setTname(tname);
			task.setTdesc(tdesc);
			task.setBegintime(begintime);
			task.setEndtime(endtime);
			task.setTcreatetime(DateUtil.getNow());
			task.setState("新任务");
			task.setLevel(level);
			task.setRate(0);
			task.setGoalid(goal.getId());
			task.setEmployeeid(taskManager);
			
			EmployeeService eservice = new EmployeeService();
			Employee employee = eservice.findById(taskManager);
			task.setEmployee(employee);
			
			TaskService tservice = new TaskService();
			tservice.save(task);
			
			Employee e=(Employee) session.getAttribute("employee");
			Project p=(Project) session.getAttribute("project");
			Message m=new Message();
			m.setId(PKUtil.getId());
			m.setContent("创建了新任务："+tname);
			m.setLink("");
			m.setMcreatetime(DateUtil.getNow());
			m.setProjectid(p.getId());
			m.setEmployeeid(e.getId());
			MessageService mservice=new MessageService();
			mservice.save(m);
			
			response.sendRedirect("task.jspx?m=login&gid=" + goal.getId());
		}
	}
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String gid = request.getParameter("gid");
		GoalService gservice = new GoalService();
		Goal goal = gservice.findGoalById(gid);
		if(goal == null){
			response.sendRedirect("goal.jspx?m=login");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("goal", goal);
			
			TaskService tservice = new TaskService();
			List<Task> taskList = tservice.findTasksByGoalId(gid);
			if(taskList.size() != 0){
				int count = 0;
				for (Task task : taskList) {
					count = count + task.getRate();
				}
				int rate = count/taskList.size();
				request.setAttribute("rate", rate);
			}
			session.setAttribute("taskList", taskList);
			
			request.getRequestDispatcher("/WEB-INF/views/task.jsp").forward(request, response);
		}
	}
}
