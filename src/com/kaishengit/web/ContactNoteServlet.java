package com.kaishengit.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Contact;
import com.kaishengit.entity.ContactNote;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.ContactNoteService;
import com.kaishengit.service.ContactService;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class ContactNoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String m = request.getParameter("m");
		if("login".equals(m) || m == null){
			login(request, response);
		}else if("addCN".equals(m)){
			if("POST".equals(request.getMethod())){
				addCN(request, response);
			}else{
				response.sendError(405);
			}
		}else{
			response.sendError(404, "参数错误！");
		}
	}

	private void addCN(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		Contact contact = (Contact) session.getAttribute("contact");
		if(contact == null){
			response.sendRedirect("contact.jspx?m=login");
		}else{
			String content = request.getParameter("content");
			ContactNote cn = new ContactNote();
			cn.setId(PKUtil.getId());
			cn.setContent(content);
			cn.setCreatetime(DateUtil.getNow());
			cn.setContactid(contact.getId());
			cn.setEmployeeid(employee.getId());
			cn.setEmployee(employee);
			ContactNoteService cnservice = new ContactNoteService();
			cnservice.save(cn);
			
			Project project = (Project)session.getAttribute("project");
			Message m=new Message();
			m.setId(PKUtil.getId());
			m.setContent("发表了联系人："+contact.getName() + "的新纪录");
			m.setLink("contactnote.jspx?m=login&cid=" + contact.getId());
			m.setMcreatetime(DateUtil.getNow());
			m.setProjectid(project.getId());
			m.setEmployeeid(employee.getId());
			MessageService mservice=new MessageService();
			mservice.save(m);
			
			response.sendRedirect("contactnote.jspx?m=login&cid=" + contact.getId());
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String cid = request.getParameter("cid");
		if(cid == null){
			response.sendRedirect("contact.jspx?m=login");
		}else{
			ContactNoteService cnservice = new ContactNoteService();
			ContactService cservice = new ContactService();
			Contact contact = cservice.findContactById(cid);
			session.setAttribute("contact", contact);
			List<ContactNote> contactNoteList = cnservice.findCNsByContactId(cid);
			session.setAttribute("contactNoteList", contactNoteList);
			
			request.getRequestDispatcher("/WEB-INF/views/contactnote.jsp").forward(request, response);
		}
	}
}
