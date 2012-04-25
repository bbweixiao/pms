package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;

public class CreateProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		if(employee == null){
			response.sendRedirect("index.jspx");
		}else{
			EmployeeService service = new EmployeeService();
			List<Employee> employeeList = service.findAllEmployees();
			session.setAttribute("employeeList", employeeList);
			request.getRequestDispatcher("/WEB-INF/views/createproject.jsp").forward(request, response);
		}
	}
}
