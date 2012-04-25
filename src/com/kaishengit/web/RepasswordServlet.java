package com.kaishengit.web;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;

public class RepasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		if(employee == null){
			response.sendRedirect("index.jspx");
		}else{
			String m = request.getParameter("m");
			if("repassword".equals(m)){
				repassword(request, response);
			}else if("test".equals(m)){
				test(request, response);
			}else{
				response.sendError(404, "²ÎÊý´íÎó£¡");
			}
		}
		
		
	}
	private void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session=request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		String pwold = request.getParameter("pwold");
		PrintWriter out = response.getWriter();
		if(employee.getPassword().equals(pwold)){
			out.print("[\"ok\"]");
		}else{
			out.print("[\"error\"]");
		}
		out.flush();
		out.close();
	}
	private void repassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session=request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		
		String pwold=request.getParameter("pwold");
		String pwnew1=request.getParameter("pwnew1");
		String pwnew2=request.getParameter("pwnew2");
		if(employee.getPassword().equals(pwold)){
			if(pwnew1.equals(pwnew2)){
				EmployeeService service=new EmployeeService();
				service.repassword(employee.getId(),pwnew1);
				employee.setPassword(pwnew1);
				session.setAttribute("employee", employee);
				response.sendRedirect("main.jspx");
				
			}else{
				response.sendRedirect("main.jspx");
			}
			
		}else{
			response.sendRedirect("index.jspx");
		}
	}

	
}
