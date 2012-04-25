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
	<script type="text/javascript">
		$(document).ready(function(){
			setInterval(function(){
				$.get("message.jspx",{m:"showMsg"},function(result){
						$("#showMsg").text("");
					if(result == null){
						$("#showMsg").appens($("<div class=\"content div2\" style=\"width:530pc\"><div class=\"alert alert-block warn\">还没有任何信息</div></div>"));
					}else{
						for(var i = 0; i < result.length; i++){
							if(result[i].link == ""){
								$("<blockquote>").attr("style", "margin:0;margin-top:20px;font-size:16px").append(
								$("<p>").text(result[i].employee.username + result[i].content)).append(
								$("<small>").attr("style","font-size:12px").text(result[i].mcreatetime)).appendTo($("#showMsg"));
							}else{
								$("<blockquote>").attr("style", "margin:0;margin-top:20px;font-size:16px").append(
								$("<p>").text(result[i].employee.username + result[i].content).append(
								$("<span>").text("......")).append(
								$("<a>").attr("style","font-size:13px").attr("href",result[i].link).text("详细信息"))).append(
								$("<small>").attr("style","font-size:12px").text(result[i].mcreatetime)).appendTo($("#showMsg"));
							}
						}
					}
				});
			},5000);
		});
	</script>
</head>
<body>
	<%@ include file="include/top.jsp" %>

 	<div class="container con">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
				<jsp:include page="include/tab.jsp">
					<jsp:param value="new" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span7">
				<div class="content div2 help-inline" style="width:545px;font-size:16px" >
					<i class="icon-th" style="margin-top:4px"></i>
						你在想什么？
					<hr/>
					<form class="form-horizontal" action="message.jspx?m=release" method="post" style="margin-bottom:0">
						<textarea rows="3" name="message" style="width:515px;margin-left:10px" id="textarea" class="input-xlarge"></textarea>
						<br/> <br/>
						<button class="btn btn-primary" type="submit" style="margin-left:450px">发布</button>
					</form>
				</div>
				<div class="content div2 help-inline" style="width:545px;font-size:16px" >
					<i class="icon-th" style="margin-top:4px"></i>
						信息流
					<hr/>
					<div id="showMsg" style="font-size:10px">
						<c:if test="${fn:length(sessionScope.list) == 0 }">
							<div class="content div2" style="width:530px">
								<div class="alert alert-block warn">
									还没有任何信息 
								</div>
							</div>
						</c:if>
						<c:forEach items="${sessionScope.list}" var="mes" varStatus="e">
							<blockquote style="margin:0;margin-top:20px">
								<p>
								<c:out value="${mes.employee.username}${mes.content}"></c:out><c:if test="${mes.link!=''}" var="res1" scope="page">......<a style="font-size:13px;"href="${mes.link}">详细信息</a></c:if>
								</p>
								<small style="font-size:12px">${mes.time }</small>
							</blockquote>
						</c:forEach>
					</div>
				</div>
			</div>
			
			<%@ include file="include/side.jsp" %>
			
	    </div>
    </div>
</body>
</html>