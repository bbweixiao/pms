package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Contact;
import com.kaishengit.entity.Project;
import com.kaishengit.service.ContactService;

public class EditContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String id = request.getParameter("id");
			ContactService cservice = new ContactService();
			Contact contact = cservice.findContactById(id);
			if(contact == null){
				response.sendRedirect("contact.jspx?m=login");
			}else{
				session.setAttribute("contact", contact);
				request.getRequestDispatcher("/WEB-INF/views/editcontact.jsp").forward(request, response);
			}
		}
	}
}
