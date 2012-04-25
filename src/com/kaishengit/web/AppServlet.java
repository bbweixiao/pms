package com.kaishengit.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.util.StringUtil;

public class AppServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("m");
		if(StringUtil.isEmpty(method)){
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		}else if("login".equals(method)){
			if("POST".equals(request.getMethod())){
				login(request,response);
			}else{
				response.sendError(405);
			}
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		//String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
		String password = request.getParameter("password");
		
		EmployeeService service = new EmployeeService();
		Employee employee = service.login(username,password);
		
		if(employee == null){
			response.sendRedirect("index.jspx?state=10001");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			response.sendRedirect("main.jspx?m=login");
		}
	}
}
