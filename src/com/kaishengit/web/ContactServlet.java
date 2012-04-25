package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kaishengit.entity.Contact;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.ContactNoteService;
import com.kaishengit.service.ContactService;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class ContactServlet extends HttpServlet {

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
			}else if("addContact".equals(m)){
				if("POST".equals(request.getMethod())){
					addContact(request, response);
				}else{
					response.sendError(405);
				}
			}else if("getDesc".equals(m)){
				if("GET".equals(request.getMethod())){
					getDesc(request, response);
				}else{
					response.sendError(405);
				}
			}else if("editContact".equals(m)){
				if("POST".equals(request.getMethod())){
					editContact(request, response);
				}else{
					response.sendError(405);
				}
			}else if("delete".equals(m)){
				delete(request, response);
			}else{
				response.sendError(404, "参数不正确");
			}
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String cid = request.getParameter("cid");
		ContactService cservice = new ContactService();
		ContactNoteService cnservice = new ContactNoteService();
		cnservice.deleteByContactId(cid);
		cservice.deleteById(cid);
		response.sendRedirect("contact.jspx?m=login");
	}

	private void editContact(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Contact contact = (Contact) session.getAttribute("contact");
		String companyname = request.getParameter("companyname");
		String mobile = request.getParameter("mobile");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String website = request.getParameter("website");
		String weibo1 = request.getParameter("weibo1");
		String weibo2 = request.getParameter("weibo2");
		String content = request.getParameter("content");
		
		Contact c = new Contact();
		c.setId(contact.getId());
		c.setName(contact.getName());
		c.setCompanyname(companyname);
		c.setMobile(mobile);
		c.setTel(tel);
		c.setEmail(email);
		c.setAddress(address);
		c.setWebsite(website);
		c.setProjectid(contact.getProjectid());
		c.setWeibo(weibo1 + "@" + weibo2);
		c.setContent(content);
		c.setCreatetime(contact.getCreatetime());
		
		ContactService service = new ContactService();
		service.update(c);
		
		Employee employee=(Employee) session.getAttribute("employee");
		Project project = (Project)session.getAttribute("project");
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("修改了联系人："+contact.getName());
		m.setLink("contact.jspx?m=login");
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(employee.getId());
		MessageService mservice=new MessageService();
		mservice.save(m);
		
		response.sendRedirect("contact.jspx?m=login");
	}

	private void getDesc(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		String id = new String(request.getParameter("id").getBytes("ISO8859-1"),"UTF-8");
		ContactService cservice = new ContactService();
		Contact c = cservice.findContactById(id);
		if(c != null){
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			response.addHeader("pragma", "no-cache");
			response.addHeader("cache-control", "no-cache");
			response.addHeader("expires", "0");
			Gson gson = new Gson();
			String json = gson.toJson(c);
			out.print(json);
			out.flush();
			out.close();
		}
	}

	private void addContact(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Project project = (Project)session.getAttribute("project");
		
		String name = request.getParameter("name");
		String companyname = request.getParameter("companyname");
		String mobile = request.getParameter("mobile");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String website = request.getParameter("website");
		String weibo1 = request.getParameter("weibo1");
		String weibo2 = request.getParameter("weibo2");
		String content = request.getParameter("content");
		
		Contact c = new Contact();
		c.setId(PKUtil.getId());
		c.setName(name);
		c.setCompanyname(companyname);
		c.setMobile(mobile);
		c.setTel(tel);
		c.setEmail(email);
		c.setAddress(address);
		c.setWebsite(website);
		c.setProjectid(project.getId());
		c.setWeibo(weibo1 + "@" + weibo2);
		c.setContent(content);
		c.setCreatetime(DateUtil.getNow());
		
		ContactService service = new ContactService();
		service.save(c);
		
		Employee employee=(Employee) session.getAttribute("employee");
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("添加了联系人："+name);
		m.setLink("contact.jspx?m=login");
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(employee.getId());
		MessageService mservice=new MessageService();
		mservice.save(m);
		
		response.sendRedirect("contact.jspx?m=login");
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Project project = (Project)session.getAttribute("project");
		
		ContactService service = new ContactService();
		List<Contact> contactList = service.findContactsByProjectId(project.getId());
		session.setAttribute("contactList", contactList);
		
		request.getRequestDispatcher("/WEB-INF/views/contact.jsp").forward(request, response);
	}
}
