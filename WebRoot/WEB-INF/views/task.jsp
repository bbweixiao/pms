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
					<jsp:param value="goal" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
					<h3>
						<a href="main.jspx?m=login" style="color:#000"><i class="icon-th-list" style="margin:6px"></i>项目</a>
						&gt;
						<a href="goal.jspx?m=login" style="color:#000">目标</a>
						&gt;
						任务
					</h3>
					
					<c:choose>
						<c:when test="${fn:length(sessionScope.taskList) == 0}">
							<hr/>
							<div class="alert alert-block warn">
								还没有任何任务！您可以<a href="createtask.jspx">新建任务</a>
							</div>
						</c:when>
							<c:otherwise>
							<div class="row" style="margin-top:20px">
								<div class="span2" style="width:100px;font-size:16px;color:#00f">目标完成度：</div>
								<div class="span6">
									<div class="progress progress-danger progress-striped active">
										<div class="bar" style="width:${requestScope.rate}%"></div>
									</div>
								</div>
								<div class="span1">
									<span><c:out value="${requestScope.rate}%"></c:out></span>
								</div>
							</div>
							<hr style="margin-left:-15px;width:740px"/>
							<a class="btn btn-success" href="createtask.jspx">
								<i class="icon-plus"></i>
								<span>新建任务</span>
							</a>
						</c:otherwise>
					</c:choose>
					<c:forEach items="${sessionScope.taskList}" var="task" varStatus="t">
						<blockquote style="margin:0;margin-top:20px;position: relative;">
							<h4>
								<a href="rate.jspx?m=login&tid=${task.id}">
									<c:out value="${t.count}、${task.tname}"/>
								</a>
								&nbsp;&nbsp;[<c:out value="${task.employee.username}"></c:out>]
								<a title="删除" href="task.jspx?m=deleteTask&tid=${task.id}"  class="update">
									<i class="icon-trash"></i>
								</a>
								<a title="编辑" href="edittask.jspx?tid=${task.id}"  class="update">
									<i class="icon-pencil"></i>
								</a>
								<span>
								<c:choose>
									<c:when test="${task.state eq '新任务'}">
										<span class="state" style="color:#00f"><c:out value="${task.state}"/></span>
									</c:when>
									<c:when test="${task.state eq '进行中'}">
										<span class="state" style="color:#f00"><c:out value="${task.state}"/></span>
									</c:when>
									<c:when test="${task.state eq '已完成'}">
										<span class="state" style="color:#0f0"><c:out value="${task.state}"/></span>
									</c:when>
								</c:choose>
								</span>
							</h4>
							<hr />
							<p style="font-size:14px">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<c:out value="${task.tdesc}"/>
							</p>
							<br/>
							<div class="row" style="padding-top:10px">
								<div class="span2" style="width:80px">完成度：</div>
								<div class="span4">
									<div class="progress progress-info progress-striped active">
										<div class="bar" style="width:${task.rate}%"></div>
									</div>
								</div>
								<div class="span1">
									<span><c:out value="${task.rate}%"></c:out></span>
								</div>
							</div>
						</blockquote>
					</c:forEach>
				</div>
			</div>
	    </div>
    </div>
</body>
</html>