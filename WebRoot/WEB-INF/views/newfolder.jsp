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
	<title>分享新资料</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

	<div class="container con" style="padding-bottom:20px">
		
		<div class="content" style="width:870px;">
			<form  id="myform" class="form-horizontal" action="filetype.jspx?m=addFolder" method="post">
				<fieldset>
				  <legend>分享新资料</legend>
				  <div class="control-group" >
					<label for="input01" class="control-label" >文件夹名称</label>
					<div class="controls">
					  <input type="text" name="ftname" id="input01" class="span4" placeholder="请输入名称">
					  
					</div>
				  </div>
				  
				  <br><br>
				  
				  <div class="control-group">
					<label for="textarea" class="control-label">描述</label>
					<div class="controls">
					 <textarea rows="6" name="fdesc" id="fdesc" class="span6" placeholder="请输入描述内容"></textarea>
					</div>
				  </div>
				  <br/>
				  <div class="div1">
					<input class="btn btn-primary" type="button" id="ftsub" value="保存">
					<a href="filetype.jspx?m=login"><button class="btn">返回</button></a>
				  </div>
				</fieldset>
			</form>
			
			
		</div>
	</div>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#ftsub").click(function(){
					if(($("#input01").val() != "") && ($("#fdesc").val() != "")){
						$("#myform").submit();
					}
				});
			});
		</script>

</body>
</html>
