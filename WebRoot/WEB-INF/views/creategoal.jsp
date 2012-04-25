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
			<form id="myform" class="form-horizontal" action="goal.jspx?m=addGoal" method="post">
				<fieldset>
				  <legend>创建新目标</legend>
				  <div class="control-group" >
					<label for="gname" class="control-label" >目标名称</label>
					<div class="controls">
					  <input type="text" name="gname" id="gname" class="span4" placeholder="请输入目标名称">
					  
					</div>
				  </div>
				  
				  <br><br>
				  
				  <div class="control-group">
					<label for="textarea" class="control-label">目标描述</label>
					<div class="controls">
					 <textarea rows="6" name="gdesc" id="gdesc" class="span6" placeholder="请输入描述内容"></textarea>
					</div>
				  </div>
				  <br/>
				  <div class="div1">
					<input class="btn btn-primary" type="button" value="提交" id="gsub" >
					<a href="goal.jspx?m=login"><button class="btn">取消</button></a>
				  </div>
				</fieldset>
			</form>
			
			
		</div>
	</div>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#gsub").click(function(){
					if(($("#gname").val() != "") && ($("#gdesc").val() != "")){
						$("#myform").submit();
					}
				});
			});
		</script>
			

</body>
</html>
