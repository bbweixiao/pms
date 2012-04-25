package com.kaishengit.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.File;
import com.kaishengit.entity.FileType;
import com.kaishengit.entity.Message;
import com.kaishengit.entity.Project;
import com.kaishengit.service.FileTypeService;
import com.kaishengit.service.MessageService;
import com.kaishengit.service.SinggleFileService;
import com.kaishengit.util.DateUtil;
import com.kaishengit.util.PKUtil;

public class UploadServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String fileTypeid=(String) session.getAttribute("fileTypeid");
		Employee employee=(Employee) session.getAttribute("employee");
		
		
		java.io.File file = new java.io.File("D:/upload");
		java.io.File tempFile = new java.io.File("C:/temp");
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		if(!tempFile.exists()) {
			tempFile.mkdir();
		}
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			DiskFileItemFactory dif = new DiskFileItemFactory();
			dif.setSizeThreshold(1024);
			dif.setRepository(tempFile);
			
			ServletFileUpload servletFileUpload = new ServletFileUpload(dif);
			servletFileUpload.setSizeMax(1024*1024*10);
			String value="noname";
			
			try {
				List<FileItem> list = servletFileUpload.parseRequest(request);				
				for(FileItem item : list) {
					if(item.isFormField()) {
						String fname=item.getString();
						if(!("".equals(fname))){
							 value=fname;
						}
					} else {
						System.out.println("value2:" + value);
					
						
						String str = item.getName().substring(item.getName().lastIndexOf("."));
						
						UUID uuid = UUID.randomUUID();
						String randomName = uuid.toString() + str;
						
						request.setAttribute("fileName", randomName);
						
						item.write(new java.io.File(file,randomName));
						
						File f=new File();
						f.setId(PKUtil.getId());
						f.setFname(value);
						f.setFilename(randomName);
						f.setFilesize(item.getSize());
						f.setFcreatetime(DateUtil.getNow());
						f.setFileTypeid(fileTypeid);
						f.setEmployeeid(employee.getId());
						
						Project project=(Project) session.getAttribute("project");
						Message m=new Message();
						m.setId(PKUtil.getId());
						m.setContent("上传了新资料："+item.getName());
						m.setLink("singlefile.jspx?m=login&fileTypeid="+fileTypeid);
						m.setMcreatetime(DateUtil.getNow());
						m.setProjectid(project.getId());
						m.setEmployeeid(employee.getId());
						MessageService mservice=new MessageService();
						mservice.save(m);
						
						SinggleFileService service=new SinggleFileService();
						service.save(f);
						FileTypeService ftser=new FileTypeService();
						FileType ft=ftser.findByid(fileTypeid);
						Long filetypesize=ft.getFiletypesize()+item.getSize();
						ftser.updateByid(filetypesize,fileTypeid);
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else {
			System.out.println("参数错误！");
		}
		response.sendRedirect("singlefile.jspx?m=login&fileTypeid="+fileTypeid);

	}

}
