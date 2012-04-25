package com.kaishengit.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.kaishengit.entity.Document;
import com.kaishengit.service.DocumentService;

public class DocumentShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String m = request.getParameter("m");
		if("login".equals(m) || m == null){
			login(request, response);
		
		}else if("edit".equals(m)){
			edit(request,response);
			
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		if(id==null){
			response.sendRedirect("document.jspx?m=login");
		}else{
			DocumentService service=new DocumentService();
			Document document=service.findDocumentById(id);
			HttpSession session = request.getSession();
			session.setAttribute("document", document);
			
			request.getRequestDispatcher("/WEB-INF/views/editdocument.jsp").forward(request, response);
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("did");
		if(id==null){
			response.sendRedirect("document.jspx?m=login");
		}else{
			DocumentService service=new DocumentService();
			Document document=service.findDocumentById(id);
			HttpSession session = request.getSession();
			session.setAttribute("document", document);
			request.getRequestDispatcher("/WEB-INF/views/documentshow.jsp").forward(request, response);
		}
	}
	
	
}
