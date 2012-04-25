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
	<title>想法</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

 	<div class="container con">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
				<jsp:include page="include/tab.jsp">
					<jsp:param value="idea" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
				<h4>
					<i class="icon-fire" style="margin:3px"></i>想法
					<a href="newidea.jspx" style="float:right">
						<i class="icon-plus"></i>
						<span>发表新想法</span>
					</a>
				</h4>
				<hr>
				<c:if test="${fn:length(sessionScope.ideaList) == 0 }">
					<div class="alert alert-block warn">
						还没有任何idea！您可以<a href="newidea.jspx">发表新想法</a>
					</div>
				</c:if>
				<c:forEach items="${sessionScope.ideaList}" var="idea" varStatus="i">
						<blockquote style="margin:0;margin-top:20px">
							<h3>
							<a href="ideashow.jspx?m=login&id=${idea.id}">${idea.title}</a>
							<a title="删除" href="idea.jspx?m=deleteIdea&id=${idea.id}" class="update">
								<i class="icon-trash"></i>
							</a>
							</h3>
							<hr />
							<p style="font-size:14px">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<c:out value="${idea.content}"></c:out>
							</p>
							<br/>
							<small style="font-size:12px"><c:out value="${idea.employee.username}"></c:out><c:out value="${idea.time}"></c:out> </small>
						</blockquote>
				</c:forEach>
			</div>
			</div>
	    </div>
    </div>
</body>
</html>