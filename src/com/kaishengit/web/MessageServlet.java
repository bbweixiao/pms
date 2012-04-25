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
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.service.MessageService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

  
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Employee employee=(Employee) session.getAttribute("employee");
		if(employee == null){
			response.sendRedirect("index.jspx");
		}else{
			Project project=(Project) session.getAttribute("project");
			if(project == null){
				response.sendRedirect("main.jspx?m=login");
			}else{
				String m = request.getParameter("m");
				if("release".equals(m)){
					if("POST".equals(request.getMethod())){
						release(request, response);
					}else{
						response.sendError(405);
					}
				}else if("showMsg".equals(m)){
					showMsg(request, response);
				}else{
					response.sendError(404, "参数错误！");
				}
			}
		}
	}

	private void showMsg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		HttpSession session=request.getSession();
		Project project=(Project) session.getAttribute("project");
		
		MessageService mservice = new MessageService();
		List<Message> list = mservice.find(project.getId());
		session.setAttribute("list", list);
		for(Message m : list){
			m.setMcreatetime(DateUtil.getTime(m.getMcreatetime()));
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
		out.flush();
		out.close();
	}

	private void release(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		HttpSession session=request.getSession();
		Employee employee=(Employee) session.getAttribute("employee");
		Project project=(Project) session.getAttribute("project");
	
		String content=request.getParameter("message");
		String s = content.substring(0, 1);
		int index = content.indexOf(" ");
		
		if("@".equals(s)&&index>0){
			String str=content.substring(1,content.indexOf(" "));
			System.out.println(str);
			EmployeeService mservice=new EmployeeService();
			Employee e=mservice.findByUsername(str);
			if(e!=null){
				content="向 "+str+" 发送消息:"+content.substring(content.indexOf(" "));
			}else{
				content="发表了心情: "+content;
			}
		}else{
			content= "发表了心情: " + content;
		}
		String link = "";
		
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent(content);
		m.setMcreatetime(DateUtil.getNow());
		m.setEmployeeid(employee.getId());
		m.setProjectid(project.getId());
		m.setLink(link);
		
		MessageService service=new MessageService();
		service.save(m);
		response.sendRedirect("project.jspx?pid="+project.getId());
	}
}
