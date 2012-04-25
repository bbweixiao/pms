package com.kaishengit.web;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.service.MessageService;
import com.kaishengit.service.SinggleFileService;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.File;
import com.kaishengit.entity.FileType;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.FileTypeService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class FileTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Project project = (Project) session.getAttribute("project");
		if(project == null){
			response.sendRedirect("main.jspx?m=login");
		}else{
			String m = request.getParameter("m");
			if("login".equals(m) || m == null){
				login(request, response);
			}else if("addFolder".equals(m)){
				addFolder(request, response);
				response.sendRedirect("filetype.jspx?m=login");
			}else if("delete".equals(m)){
				delete(request,response);
				response.sendRedirect("filetype.jspx?m=login");
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String filetypeid=request.getParameter("filetypeid");
		SinggleFileService ser=new SinggleFileService();
		List<File> filelist=ser.findFilesByFileTypeid(filetypeid);
		Iterator<File> iter=filelist.iterator();
		while(iter.hasNext()){
			File file=(File)iter.next();
			String filename=file.getFilename();
			System.out.println(filename);
			java.io.File f=new java.io.File("D:/upload/"+filename);
			f.delete();
		}
		ser.deleteByFileTypeId(filetypeid);
		FileTypeService service=new FileTypeService();
		service.delete(filetypeid);
		
		
		
	}

	private void addFolder(HttpServletRequest request,
			HttpServletResponse response) {
		String ftname = request.getParameter("ftname");
		HttpSession session = request.getSession();
		Project project=(Project) session.getAttribute("project");
		Employee employee=(Employee) session.getAttribute("employee");

				
		FileType f=new FileType();
		f.setId(PKUtil.getId());
		f.setFtname(ftname);
		f.setFtcreatetime(DateUtil.getNow());
		f.setEmployeeid(employee.getId());
		f.setProjectid(project.getId());
		
		FileTypeService service=new FileTypeService();
		service.save(f);
		
		Message m=new Message();
		m.setId(PKUtil.getId());
		m.setContent("创建了新文件夹："+ftname);
		m.setLink("filetype.jspx?m=login");
		m.setMcreatetime(DateUtil.getNow());
		m.setProjectid(project.getId());
		m.setEmployeeid(employee.getId());
		
		MessageService mservice=new MessageService();
		mservice.save(m);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileTypeService service=new FileTypeService();
		List<FileType> fileTypeList=service.findAll();
		HttpSession session = request.getSession();
		session.setAttribute("fileTypeList", fileTypeList);
		request.getRequestDispatcher("/WEB-INF/views/file.jsp").forward(request, response);
	}
}
