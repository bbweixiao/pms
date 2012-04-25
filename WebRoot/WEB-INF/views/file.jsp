<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					
					<i class="icon-folder-open" style="margin:3px"></i>文件夹列表
					
					<a class="btn btn-success" href="newfolder.jspx" style="float:right">
						<i class="icon-plus" style="margin:2px"></i>新建文件夹
					</a>
				</h4>
				<hr>
				<c:choose>
					<c:when test="${fn:length(sessionScope.fileTypeList) == 0 }">
						<div class="alert alert-block warn">
							还没有任何文件夹！您可以<a href="newfolder.jspx">创建新文件夹</a>
						</div>
					</c:when>
					<c:otherwise >
						<table class="table" style="font-size:14px">
							<thead>
								<tr>
									<th width="30px"></th>
									<th width="60%">名称</th>
									<th width="20%">发布时间</th>
									<th>创建人</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sessionScope.fileTypeList}" var="fileType" varStatus="f">
								<tr>
									<td>
										<img src="img/folder.png">
									</td>
									<td><a href="singlefile.jspx?m=login&fileTypeid=${fileType.id}">${fileType.ftname}</a></td>
									<td>${fileType.time}</td>
									<td>${fileType.employee.username }</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>

			</div>
			</div>
	    </div>
    </div>
</body>
</html>