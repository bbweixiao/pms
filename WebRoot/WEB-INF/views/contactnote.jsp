<%@page import="com.kaishengit.util.DateUtil"%>
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
	<title>记录</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>
	
 	<div class="container con">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
				<jsp:include page="include/tab.jsp">
					<jsp:param value="contact" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
					<h4>
						<a href="contact.jspx" style="color:#000"><i class="icon-list-alt" style="margin:3px"></i>联系人</a>&gt;<c:out value="${sessionScope.contact.name}"></c:out>
						<a class="btn btn-success" style="float:right" href="#addCN" data-toggle="modal">
							<i class="icon-plus" style="margin:2px"></i>
							添加记录
						</a>
					</h4>
					<hr/>
					<c:if test="${fn:length(sessionScope.contactNoteList) == 0}">
						<div class="alert alert-block warn">
							还没有任何记录！您可以<a href="#addCN" data-toggle="modal"">添加记录</a>
						</div>
					</c:if>
					<c:forEach items="${sessionScope.contactNoteList}" var="contactNote" varStatus="c">
						<blockquote style="margin:0;margin-top:20px">
							<p style="font-size:14px">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<c:out value="${contactNote.content}"></c:out>
							</p>
							<br/>
							<small style="font-size:12px">
								<c:out value="${contactNote.employee.username}"></c:out><c:out value="${contactNote.time}"></c:out></small>
						</blockquote>
						<br/>
					</c:forEach>
					<a href="contact.jspx?m=login"><i class="icon-arrow-left" style="margin:4px"></i>返回</a>
				</div>
			</div>
	    </div>
    </div>
    <div class="modal hide fade in" id="addCN" style="display:none;">
		<form  action="contactnote.jspx?m=addCN" class="form-horizontal" method="post">
			<div class="modal-header">
			请输入记录
				<a data-dismiss="modal" class="close">×</a>
				<br><br>
				  <div class="control-group">
					<br/>
					<div>
					  <textarea rows="4" name="content" style="margin-left:35px" class="span6"></textarea>
					</div>
				  </div>
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-primary" />
				<a data-dismiss="modal" class="btn" href="">取消</a>
			</div>
		</form>
	</div>
</body>
</html>