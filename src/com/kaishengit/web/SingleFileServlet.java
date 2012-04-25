package com.kaishengit.web;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.kaishengit.entity.File;
import com.kaishengit.entity.FileType;
import com.kaishengit.service.FileTypeService;
import com.kaishengit.service.SinggleFileService;

public class SingleFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	SinggleFileService service=new SinggleFileService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String m = request.getParameter("m");
		System.out.print(m);
		if("login".equals(m) || m == null){
			login(request, response);
		}else if("delete".equals(m)){
			delete(request,response);
			HttpSession session = request.getSession();
			String fileTypeid=(String) session.getAttribute("fileTypeid");
			
			response.sendRedirect("singlefile.jspx?m=login&fileTypeid="+fileTypeid);
		 }else{
		
			response.sendError(404, "²ÎÊý´íÎó£¡");
		 }
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String fileid=request.getParameter("fileid");
		
		File file=service.findFileById(fileid);
		String filename=file.getFilename();
		java.io.File f=new java.io.File("D:/upload/"+filename);
		f.delete();
		
		HttpSession session = request.getSession();
		String fileTypeid=(String) session.getAttribute("fileTypeid");
		FileTypeService ftser=new FileTypeService();
		FileType ft=ftser.findByid(fileTypeid);
		Long filetypesize=ft.getFiletypesize()-file.getFilesize();
		ftser.updateByid(filetypesize,fileTypeid);
		service.delete(fileid);
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileTypeid=request.getParameter("fileTypeid");
		
		List<File> fileList=service.findFilesByFileTypeid(fileTypeid);
		HttpSession session = request.getSession();
		session.setAttribute("fileTypeid", fileTypeid);
		session.setAttribute("fileList", fileList);
		request.getRequestDispatcher("/WEB-INF/views/singlefile.jsp").forward(request, response);
	}
}
