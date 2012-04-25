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
import com.kaishengit.entity.IdeaComment;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.IdeaCommentService;
import com.kaishengit.service.IdeaService;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class IdeaShowServlet extends HttpServlet {

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
			}else if("addIdeaComment".equals(m)){
				if("POST".equals(request.getMethod())){
					addIdeaComment(request, response);
				}else{
					response.sendError(405);
				}
			}else if("deleteIC".equals(m)){
				delete(request, response);
			}else{
				response.sendError(404, "参数错误！");
			}
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Idea idea = (Idea) session.getAttribute("idea");
		if(idea == null){
			response.sendRedirect("idea.jspx?m=login");
		}else{
			String icid = request.getParameter("icid");
			IdeaCommentService icservice = new IdeaCommentService();
			icservice.deleteById(icid);
			response.sendRedirect("ideashow.jspx?m=login&id=" + idea.getId());
		}
	}
	private void addIdeaComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Idea idea = (Idea) session.getAttribute("idea");
		Employee employee = (Employee) session.getAttribute("employee");
		if(idea == null){
			response.sendRedirect("idea.jspx?m=login");
		}else{
			String content = request.getParameter("content");
			IdeaComment ic = new IdeaComment();
			ic.setId(PKUtil.getId());
			ic.setContent(content);
			ic.setCreatetime(DateUtil.getNow());
			ic.setIdeaid(idea.getId());
			ic.setEmployeeid(employee.getId());
			ic.setEmployee(employee);
			IdeaCommentService icservice = new IdeaCommentService();
			icservice.save(ic);
			
			Project project = (Project) session.getAttribute("project");
			Message m=new Message();
			m.setId(PKUtil.getId());
			m.setContent("对想法："+ idea.getTitle() + "发表了新回复");
			m.setLink("ideashow.jspx?m=login&id=="+idea.getIcreatetime());
			m.setMcreatetime(DateUtil.getNow());
			m.setProjectid(project.getId());
			m.setEmployeeid(employee.getId());
			MessageService mservice=new MessageService();
			mservice.save(m);
			
			response.sendRedirect("ideashow.jspx?m=login&id=" + ic.getIdeaid());
		}
	}
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		IdeaService iservice = new IdeaService();
		Idea idea = iservice.findIdeaById(id);
		if(idea == null){
			response.sendError(404,"查找的内容不存在！");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("idea", idea);
			IdeaCommentService icservice = new IdeaCommentService();
			List<IdeaComment> ideaCommentList = icservice.findICsListByIdeaId(idea.getId());
			session.setAttribute("ideaCommentList", ideaCommentList);
			request.getRequestDispatcher("/WEB-INF/views/ideashow.jsp").forward(request, response);
		}
	}
}
