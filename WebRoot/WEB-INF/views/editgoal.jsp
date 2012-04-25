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
			<form class="form-horizontal" action="goal.jspx?m=editGoal" method="post">
				<fieldset>
				  <legend>创建新目标</legend>
				  <div class="control-group" >
					<label for="gname" class="control-label" >目标名称</label>
					<div class="controls">
					  <span id="gname" class="span5 uneditable-input">${sessionScope.goal.gname}</span>
					</div>
				  </div>
				  
				  <br><br>
				  
				  <div class="control-group">
					<label for="textarea" class="control-label">目标描述</label>
					<div class="controls">
					  <textarea rows="6" name="gdesc" id="textarea" class="span6"><c:out value="${sessionScope.goal.gdesc}"></c:out></textarea>
					</div>
				  </div>
				  <br/>
				  <div class="div1">
					<button class="btn btn-primary" type="submit">提交</button>
					<a href="goal.jspx?m=login"><button class="btn">取消</button></a>
				  </div>
				</fieldset>
			</form>
			
			
		</div>
	</div>

</body>
</html>
