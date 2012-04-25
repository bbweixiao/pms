<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<title>创建项目</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

	<div class="container con" style="padding-bottom:20px">
		
		<div class="content" style="width:870px;">
			<form  id="myform" class="form-horizontal" action="main.jspx?m=addProject" method="post">
				<fieldset>
				  <legend>创建新项目</legend>
				  <div class="control-group" >
					<label for="pname" class="control-label" >项目名称</label>
					<div class="controls">
					 <input type="text" name="pname" id="pname" class="span4" placeholder="请输入项目名称">
					  
					</div>
				  </div>
				  
				  <br><br>
				  
				  <div class="control-group">
					<label for="textarea" class="control-label">项目描述</label>
					<div class="controls">
					  <textarea rows="6" name="pdesc" id="pdesc" class="span6" placeholder="请输入描述内容"></textarea>
					</div>
				  </div>
				  <br>
				  <div class="control-group">
					<label for="manager" class="control-label">负责人</label>
					<div class="controls">
					  <select  name="manager" id="manager">
						<option value="">--请选择--</option>
						<c:forEach items="${sessionScope.employeeList}" var="emp" varStatus="e">
						<option value="${emp.id}">${emp.username}</option>
						</c:forEach>
					  </select>
					</div>
				  </div>
				  <br>
				  <div class="control-group">
					<label for="multiSelect" class="control-label">项目成员</label>
					<div class="controls">
						<c:forEach items="${sessionScope.employeeList}" var="emp" varStatus="e">
		                <div class="box form-inline">
		                	<label class="checkbox">
		                		<input type="checkbox" name="staff" value="${emp.id}">
		                		${emp.username}
		                	</label>
		                </div>
						</c:forEach>
					</div>
				  </div>
				  <br>
				  <div class="div1">
					<input class="btn btn-primary" id="psub" type="button" value="提交">
					<a href="main.jspx?m=login"><button class="btn">取消</button></a>
				  </div>
				</fieldset>
			</form>
					<script type="text/javascript">
			$(document).ready(function(){
				$("#psub").click(function(){
					if(($("#pname").val() != "") && ($("#pdesc").val() != "")&&($("#manager").val()!= "")){
						$("#myform").submit();
					}
				});
			});
		</script>
			
		</div>
	</div>

</body>
</html>