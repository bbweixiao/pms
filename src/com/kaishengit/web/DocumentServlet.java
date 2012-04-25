package com.kaishengit.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Document;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.DocumentService;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class DocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DocumentService service=new DocumentService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Project p=(Project) session.getAttribute("project");
		
		if(p==null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String m = request.getParameter("m");
			
			if("login".equals(m) ){
				login(request, response);
			}else if("addDocument".equals(m)){
				if("POST".equals(request.getMethod())){
					addDocument(request,response);
					response.sendRedirect("document.jspx?m=login");
				}else{
					response.sendError(405);
				}
			}else if("delete".equals(m)){
				delete(request,response);
				response.sendRedirect("document.jspx?m=login");
			}else if("edit".equals(m)){
				if("POST".equals(request.getMethod())){
					edit(request,response);
					String id=request.getParameter("id");
					response.sendRedirect("documentshow.jspx?m=login&did="+id);
				}else{
					response.sendError(405);
				}
			}else{
				response.sendError(404, "参数错误！");
			}
		}
	}


	private void edit(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Document d=new Document();
		d.setContent(content);
		d.setTitle(title);
		d.setId(id);
		
		service.edit(d);
		Document document = service.findDocumentById(id);
		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		Employee employee=(Employee) session.getAttribute("employee");
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("修改了资料："+title);
		m.setLink("documentshow.jspx?m=login&did="+document.getId());
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(employee.getId());
		
		MessageService mservice=new MessageService();
		mservice.save(m);
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		
		service.delete(id);
	}

	private void addDocument(HttpServletRequest request,
			HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		Project project = (Project)session.getAttribute("project");
		Employee employee=(Employee) session.getAttribute("employee");
		String did=PKUtil.getId();
		Document doc=new Document();
		doc.setId(did);
		doc.setTitle(title);
		doc.setContent(content);
		doc.setDcreatetime(DateUtil.getNow());
		doc.setEmployeeid(employee.getId());
		doc.setProjectid(project.getId());
		
		service.save(doc);
		
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("分享了新资料："+title);
		m.setLink("documentshow.jspx?m=login&did="+did);
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(employee.getId());
		
		MessageService mservice=new MessageService();
		mservice.save(m);
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Project project = (Project)session.getAttribute("project");
		
		
		List<Document> documentList=service.findDocumentsByProjectId(project.getId());
		
		session.setAttribute("documentList", documentList);
		System.out.print(documentList);
	
		request.getRequestDispatcher("/WEB-INF/views/document.jsp").forward(request, response);
	}
}
