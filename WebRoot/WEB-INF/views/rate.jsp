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
	<title>任务进度</title>
</head>
<body>
	
	<%@ include file="include/top.jsp" %>
	
	<div class="container con" style="margin-top:108px">
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
						<a href="task.jspx?m=login&gid=${sessionScope.goal.id}" style="color:#000">任务</a>
						&gt;
						<c:out value="${sessionScope.task.tname}"/>
					</h3>
					<hr/ style="margin-left:-15px;width:740px">
					<h3 style="font-size:16px;color:#25304c">
						<c:out value="${sessionScope.task.tname}"/>
						<a title="删除" href="task.jspx?m=deleteTask&tid=${task.id}"  class="update">
							<i class="icon-trash"></i>
						</a>
						<a title="编辑" href="edittask.jspx?tid=${task.id}"  class="update">
							<i class="icon-pencil"></i>
						</a>
						<c:choose>
							<c:when test="${sessionScope.task.state eq '新任务'}">
								<span class="state" style="color:#00f"><c:out value="${sessionScope.task.state}"/></span>
							</c:when>
							<c:when test="${sessionScope.task.state eq '进行中'}">
								<span class="state" style="color:#f00"><c:out value="${sessionScope.task.state}"/></span>
							</c:when>
							<c:when test="${sessionScope.task.state eq '已完成'}">
								<span class="state" style="color:#0f0"><c:out value="${sessionScope.task.state}"/></span>
							</c:when>
						</c:choose>
					</h3>
					<hr />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<c:out value="${sessionScope.task.tdesc}"/>
					</p>
					<br/>
					<div class="time">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建时间：<c:out value="${sessionScope.task.tcreatetime}"/><br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：<c:out value="${sessionScope.task.begintime}"/><br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;截止时间：<c:out value="${sessionScope.task.endtime}"/><br/>
					</div>
					<div class="attribute">
						目　标：<c:out value="${sessionScope.goal.gname}"/>
					</div>
					<div class="attribute">
						负责人：<c:out value="${sessionScope.task.employee.username}"/>
					</div>
					<div class="attribute">
						级　别：<c:out value="${sessionScope.task.level}"/>
					</div>
					<div class="row attribute">
						<div class="span2" style="width:80px">完成度：</div>
						<div class="span6">
							<div class="progress progress-info progress-striped active">
								<div class="bar" style="width:${sessionScope.task.rate}%"></div>
							</div>
						</div>
						<div class="span1">
							<span><c:out value="${sessionScope.task.rate}%"></c:out></span>
						</div>
					</div>
					<div id="change">
					<a class="btn btn-primary" href="#myModal02"  data-toggle="modal">修改</a>
					</div>
					<div class="modal hide fade in" id="myModal02" style="display:none;">
						<form action="rate.jspx?m=update" method="post">
							<div class="modal-header">
								<a data-dismiss="modal" class="close">×</a>
								<h3>请输入任务进度</h3>
							</div>
							<div class="modal-body">
								<div style="padding-left:180px">
									<input type="text" name="rate" class="span2"/>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">确定</button>
								<a data-dismiss="modal" class="btn">取消</a>
							</div>
						</form>
					</div>
				</div>
			</div>
	    </div>
    </div>
</body>
</html>