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
	<title>资料库</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

 	<div class="container con">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
				<jsp:include page="include/tab.jsp">
					<jsp:param value="document" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
				<h4>
					
					<i class="icon-folder-open" style="margin:3px"></i>资料
					
					<a href="newdocument.jspx" style="float:right">
						<i class="icon-plus" style="margin-top:2px"></i>
						<span>分享新资料</span>
					</a>
				</h4>
				<hr>
			
				<c:if test="${fn:length(sessionScope.documentList) == 0 }">
					<div class="alert alert-block warn">
						还没有任何资料！您可以<a href="newdocument.jspx">分享新资料</a>
					</div>
					
				</c:if>
				<c:forEach items="${sessionScope.documentList}" var="document" varStatus="d">
					<blockquote style="margin:0;margin-top:20px">
						<h3>
						<a href="documentshow.jspx?m=login&did=${document.id}">${document.title}</a>
						<a title="删除" href="document.jspx?m=delete&id=${document.id}" class="update">
							<i class="icon-trash"></i>
						</a>
						<a title="编辑" href="documentshow.jspx?m=edit&id=${document.id}" class="update">
							<i class="icon-pencil"></i>
						</a>
						</h3>
						<hr />
						<p style="font-size:14px">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<c:out value="${fn:substring(document.content, 0, 220)}........" />
						<a href="documentshow.jspx?m=login&did=${document.id}">详细信息</a>
						</p>
						<br/>
						<small style="font-size:12px">${document.employee.username} 发布于 
						${document.time}
						</small>
					</blockquote>
				</c:forEach>
					
			</div>
			</div>
	    </div>
    </div>
</body>
</html>