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
					<jsp:param value="project" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
				<h4>
					<a href="document.jspx?m=login" style="color:#000">
						<i class="icon-folder-open" style="margin:3px"></i>资料</a>
					&gt;
					${sessionScope.document.title}
				</h4>
				<hr>
				<h3>
					<a href="#">${document.title}</a>
					<small style="font-size:14px">&nbsp;&nbsp;&nbsp;<c:out value="${document.employee.username}${document.time}"></c:out></small>
					<a title="删除"href="document.jspx?m=delete&id=${document.id}" class="update">
						<i class="icon-trash"></i>
					</a>
					<a title="编辑" href="documentshow.jspx?m=edit&id=${document.id}" class="update">
						<i class="icon-pencil"></i>
					</a>
				</h3>
				<br/>
				<p style="font-size:14px">
					&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${document.content}"></c:out>
				</p>
				<br/>
				<a href="document.jspx?m=login"><i class="icon-arrow-left" style="margin:4px"></i>返回</a>
			</div>
			</div>
	    </div>
    </div>
</body>
</html>