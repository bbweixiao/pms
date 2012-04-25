<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<title>任务列表</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

 	<div class="container con">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
				<jsp:include page="include/tab.jsp">
					<jsp:param value="file" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
				<h4>
					
					<a href="filetype.jspx?m=login" style="color:#000">
						<i class="icon-folder-open" style="margin:3px"></i>文件共享</a>
					&gt;
					<c:out value="${sessionScope.fileType.ftname}"></c:out>
					<a title="删除" href="filetype.jspx?m=delete&filetypeid=${sessionScope.fileType.id}" style="float:right;margin-right:10px">
						<i class="icon-trash"></i>
					</a>
					<a style="margin-top:-5px" class="btn btn-success update" href="newfile.jspx"><i class="icon-white  icon-share"></i>上传文件</a>
				</h4>
				<hr>
				<c:choose>
					<c:when test="${fn:length(sessionScope.fileList) == 0 }">
						<div class="alert alert-block warn">
							还没有任何文件！您可以<a href="newfile.jspx">上传文件</a>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${sessionScope.fileList}" var="file" varStatus="f">
							<div class="file">
								<div class="span1">
									<a href="">
										<c:choose>
											<c:when test="${file.suffix=='.doc'}">
											  <img src="img/doc.png">
											</c:when>
											<c:when test="${file.suffix=='.pdf'}">
											  <img src="img/pdf.png">
											</c:when>
											<c:when test="${file.suffix=='.jpg'||file.suffix=='.JPG'}">
											  <img src="img/jpg.png">
											</c:when>
											<c:when test="${file.suffix=='.rar'}">
											  <img src="img/rar.png">
											</c:when>
											<c:when test="${file.suffix=='.txt'}">
											  <img src="img/txt.png">
											</c:when>
											<c:otherwise>
												<img src="img/other.png">
											</c:otherwise>
										</c:choose>
									</a>
								</div>
								<div class="span3" style="font-size:14px">
									<a href="download.jspx?name=${file.filename}">${file.fname}</a><br/>
									<span>
										<c:choose>
											<c:when test="${file.filesize<1024}">
												${file.filesize}B
											</c:when>
											<c:when test="${file.filesize<1024*1024}">
												<fmt:formatNumber type='number' value='${file.filesize/1024}' maxFractionDigits="0"></fmt:formatNumber>&nbsp;KB
											</c:when>
											<c:when test="${file.filesize<1024*1024*1024}">
												<fmt:formatNumber type='number' value='${file.filesize/(1024*1024)}' maxFractionDigits="1"></fmt:formatNumber>&nbsp;MB
											</c:when>
											<c:otherwise>
												<fmt:formatNumber type='number' value='${file.filesize/(1024*1024*1024)}' maxFractionDigits="1"></fmt:formatNumber>&nbsp;GB
											</c:otherwise>
										</c:choose>
										&nbsp;&nbsp;</span>
									<span>${file.employee.username}</span><br/>
									<a title="删除" href="singlefile.jspx?m=delete&fileid=${file.id}">
										<i class="icon-trash"></i>
									</a>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
					<div class="clear"></div>
			</div>
			</div>
	    </div>
    </div>
</body>
</html>