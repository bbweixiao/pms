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
					<jsp:param value="idea" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
					<h4>
						<a href="idea.jspx?m=login" style="color:#000">
							<i class="icon-fire" style="margin:3px"></i>想法</a>
							&gt;
							${sessionScope.idea.title}
					</h4>
					<hr>
					<h3>
						${sessionScope.idea.title}
						<small style="font-size:14px">&nbsp;&nbsp;&nbsp;<c:out value="${sessionScope.idea.employee.username}${sessionScope.idea.time}"></c:out></small>
						<a title="删除" href="idea.jspx?m=deleteIdea&id=${sessionScope.idea.id}" class="update">
							<i class="icon-trash"></i>
						</a>
					</h3>
					<br/>
					<p style="font-size:14px">
						&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.idea.content}
					</p>
					<br/>
					<form action="ideashow.jspx?m=addIdeaComment" method="post">
						<h4>评论</h4>
						<textarea rows="4" name="content" class="span7"></textarea>
						<div style="margin-left:480px">
							<button class="btn" type="submit">发布</button>
						</div>
					</form>
					<hr>
					<c:if test="${fn:length(sessionScope.ideaCommentList) == 0}">
						<div class="alert alert-block warn">
							还没有任何评论！
						</div>
					</c:if>
					<c:forEach items="${sessionScope.ideaCommentList}" var="ideaComment" varStatus="i">
						<blockquote style="margin:0;margin-top:20px">
							<p style="font-size:14px">
								&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${ideaComment.content}"></c:out>
							</p>
							<br/>
							<small style="font-size:12px"><c:out value="${ideaComment.employee.username}${ideaComment.time}"></c:out>
								<a title="删除" href="ideashow.jspx?m=deleteIC&icid=${ideaComment.id}" style="float:right;margin-right:10px">
									<i class="icon-trash"></i>
								</a>
							</small>
						</blockquote>
					</c:forEach>
						<br/>
						<a href="idea.jspx?m=login" style="font-size:14px">
							<i class="icon-arrow-left" style="margin-top:3px"></i>返回想法列表
						</a>
				</div>
			</div>
	    </div>
    </div>
</body>
</html>