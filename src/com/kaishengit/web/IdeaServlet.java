package com.kaishengit.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Idea;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.IdeaCommentService;
import com.kaishengit.service.IdeaService;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class IdeaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String m = request.getParameter("m");
			if("login".equals(m) || m == null){
				login(request, response);
			}else if("addIdea".equals(m)){
				if("POST".equals(request.getMethod())){
					addIdea(request, response);
				}else{
					response.sendError(405);
				}
			}else if("deleteIdea".equals(m)){
				delete(request, response);
			}else{
				response.sendError(404,"参数错误！");
			}
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String id = request.getParameter("id");
		IdeaCommentService icservice = new IdeaCommentService();
		IdeaService service = new IdeaService();
		icservice.deleteByIdeaId(id);
		service.deleteById(id);
		response.sendRedirect("idea.jspx?m=login");
	}

	private void addIdea(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		Project project = (Project) session.getAttribute("project");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id=PKUtil.getId();
		Idea idea = new Idea();
		idea.setId(id);
		idea.setTitle(title);
		idea.setContent(content);
		idea.setEmployeeid(employee.getId());
		idea.setEmployee(employee);
		idea.setProjectid(project.getId());
		idea.setIcreatetime(DateUtil.getNow());
		IdeaService iservice = new IdeaService();
		iservice.save(idea);

		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("发表了新想法："+title);
		m.setLink("ideashow.jspx?m=login&id=="+id);
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(employee.getId());
		MessageService mservice=new MessageService();
		mservice.save(m);
		
		response.sendRedirect("idea.jspx?m=login");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		IdeaService iservice = new IdeaService();
		List<Idea> ideaList = iservice.findIdeasByPid(project.getId());
		session.setAttribute("ideaList", ideaList);
		request.getRequestDispatcher("/WEB-INF/views/idea.jsp").forward(request, response);
	}
}
