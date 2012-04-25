package com.kaishengit.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
		String name = request.getParameter("name");
		
		if(name == null || "".equals(name)) {
			throw new RuntimeException("必须要指定文件名称");
		} else {
			
			File file = new File( "D:/upload/"+ name);
			
			if(!file.exists()) {
				response.sendError(404,"你要找的文件不存在！");
			} else {
				
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				
				byte[] buffer = new byte[1024];
				
				response.setContentType("application/octet-stream");
				
				String encodedfileName = new String(name.getBytes("UTF-8"), "ISO8859-1");
				response.setHeader("Content-Disposition", "attachment; name=\"" + encodedfileName + "\"");
				
				OutputStream os = response.getOutputStream(); //获取响应输出流
				
				int len = -1;
				while((len = bis.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				bis.close();
			}
		}
	}
}
