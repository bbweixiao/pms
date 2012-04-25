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
	<title>目标列表</title>
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
					目标
					<a class="btn btn-success" href="creategoal.jspx" style="float:right">
						<i class="icon-plus"></i>
						<span>新建目标</span>
					</a>
				</h3>
			</div>
			<c:if test="${fn:length(sessionScope.goalList) == 0 }">
				<div class="content div2">
					<div class="alert alert-block warn">
						还没有任何目标！您可以<a href="creategoal.jspx">新建目标</a>
					</div>
				</div>
			</c:if>
				<c:forEach items="${sessionScope.goalList}" var="goal" varStatus="g">
					<div class="content div2">
						<h3 style="font-size:16px">
							<a href="task.jspx?m=login&gid=${goal.id}"><c:out value="${g.count}、${goal.gname}"></c:out></a>
							<a title="删除" href="goal.jspx?m=deleteGoal&gid=${goal.id}" class="update">
								<i class="icon-trash"></i>
							</a>
							<a title="编辑" href="editgoal.jspx?m=login&gid=${goal.id}" class="update">
								<i class="icon-pencil"></i>
							</a>
						</h3>
						<hr />
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;
							${goal.gdesc}
						</p>
						<br/>
						<div class="time">
							创建时间：${goal.gcreatetime}
						</div>
					</div>
				</c:forEach>
			</div>
	    </div>
    </div>
</body>
</html>