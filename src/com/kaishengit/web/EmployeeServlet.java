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
import com.kaishengit.util.PKUtil;

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Employee e=(Employee) session.getAttribute("employee");
		if(e==null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String m = request.getParameter("m");
			if("new".equals(m)){
				if("POST".equals(request.getMethod())){
					newEmployee(request, response);
				}else{
					response.sendError(405);
				}
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
		String username = request.getParameter("username");
		EmployeeService service = new EmployeeService();
		Employee employee = service.findByUsername(username);
		PrintWriter out = response.getWriter();
		if(employee == null){
			out.print("[\"ok\"]");
		}else{
			out.print("[\"error\"]");
		}
		out.flush();
		out.close();
	}
	private void newEmployee(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String name=request.getParameter("username");
		String pw1=request.getParameter("pw1");
		
		Employee em=new Employee();
		em.setId(PKUtil.getId());
		em.setUsername(name);
		em.setPassword(pw1);
		EmployeeService service=new EmployeeService();
		service.save(em);
		
		response.sendRedirect("main.jspx?m=login");		
	}


}
