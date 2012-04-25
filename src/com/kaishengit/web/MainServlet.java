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
import com.kaishengit.entity.Project;
import com.kaishengit.service.ContactNoteService;
import com.kaishengit.service.ContactService;
import com.kaishengit.service.DocumentService;
import com.kaishengit.service.EmployeeProjectService;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.service.FileTypeService;
import com.kaishengit.service.GoalService;
import com.kaishengit.service.IdeaCommentService;
import com.kaishengit.service.IdeaService;
import com.kaishengit.service.MessageService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.service.SinggleFileService;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee e = (Employee) session.getAttribute("employee");
		if(e == null){
			response.sendRedirect("index.jspx");
		}else{
			String m = request.getParameter("m");
			if("login".equals(m) || m == null){
				login(request, response);
			}else if("addProject".equals(m)){
				if("POST".equals(request.getMethod())){
					addProject(request,response);
				}else{
					response.sendError(405);
				}
			}else if("deleteProject".equals(m)){
				deleteProject(request, response);
			}else if("editProject".equals(m)){
				if("POST".equals(request.getMethod())){
					editProject(request, response);
				}else{
					response.sendError(405);
				}
			}else{
				response.sendError(404, "²ÎÊý´íÎó£¡");
			}
		}
	}
	
	private void editProject(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String pname = request.getParameter("pname");
			String pdesc = request.getParameter("pdesc");
			Project p = new Project();
			p.setId(project.getId());
			p.setPcreatetime(project.getPcreatetime());
			p.setPname(pname);
			p.setPdesc(pdesc);
			ProjectService pservice = new ProjectService();
			pservice.update(p);
			
			response.sendRedirect("main.jspx?m=login");
		}
	}

	private void deleteProject(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String pid = request.getParameter("pid");
		
		TaskService tservice = new TaskService();
		tservice.deleteByProjectId(pid);
		
		GoalService gservice = new GoalService();
		gservice.deleteByProjectId(pid);
		
		EmployeeProjectService epservice = new EmployeeProjectService();
		epservice.deleteByProjectId(pid);
		
		DocumentService dservice = new DocumentService();
		dservice.deleteByProjectId(pid);
		
		MessageService mservice = new MessageService();
		mservice.deleteByProjectId(pid);
		
		ContactNoteService cnservice = new ContactNoteService();
		cnservice.deleteByProjectId(pid);
		
		ContactService cservice = new ContactService();
		cservice.deleteByProjectId(pid);
		
		IdeaCommentService icservice = new IdeaCommentService();
		icservice.deleteByProjectId(pid);
		
		IdeaService iservice = new IdeaService();
		iservice.deleteByProjectId(pid);
		
		SinggleFileService fservice = new SinggleFileService();
		fservice.deleteByProjectId(pid);
		
		FileTypeService ftservice = new FileTypeService();
		ftservice.deleteByProjectId(pid);
		
		ProjectService pservice = new ProjectService();
		pservice.deleteById(pid);
		
		response.sendRedirect("main.jspx?m=login");
	}

	private void addProject(HttpServletRequest request, HttpServletResponse response) {
		
		String pname = request.getParameter("pname");
		String pdesc = request.getParameter("pdesc");
		
		String id = request.getParameter("manager");
		String paramValue[] = request.getParameterValues("staff");

		Project p = new Project();
		p.setId(PKUtil.getId());
		p.setPname(pname);
		p.setPdesc(pdesc);
		p.setPcreatetime(DateUtil.getNow());
		ProjectService pservice = new ProjectService();
		pservice.saveProject(p);
		
		EmployeeService eservice = new EmployeeService();
		EmployeeProjectService emservice = new EmployeeProjectService();
		EmployeeProject ep = new EmployeeProject();
		
		String eid = "";
		for (int i = 0; i < paramValue.length; i++) {
			eid = paramValue[i].toString();
			if(eid.equals(id)){
				continue;
			}
			ep.setEmployeeid(eid);
			ep.setEmployee(eservice.findById(eid));
			ep.setProjectid(p.getId());
			ep.setProject(p);
			ep.setRole("staff");
			emservice.saveEmployeeProject(ep);
		}
		
		ep.setEmployeeid(id);
		ep.setProjectid(p.getId());
		ep.setRole("manager");
		emservice.saveEmployeeProject(ep);
		
		try {
			response.sendRedirect("main.jspx");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee e = (Employee)session.getAttribute("employee");
		if(e == null){
			response.sendRedirect("index.jspx");
		}else{
			EmployeeProjectService epservice = new EmployeeProjectService();
			List<EmployeeProject> epList = epservice.findEmployeeProjectsByEmployeeId(e.getId());
			
			session.setAttribute("epList", epList);
			request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
		}
	}
}
