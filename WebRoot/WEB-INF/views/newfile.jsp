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
	<title>上传文件</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

	<div class="container con" style="padding-bottom:20px">
		
		<div class="content" style="width:870px;">
			<form  id="myform" class="form-horizontal" action="upload.jspx" method="post" enctype="multipart/form-data">
				<fieldset>
				  <legend>分享新文件</legend>
				  <div class="control-group" >
					<label for="filename" class="control-label" >文件名称</label>
					<div class="controls">
					  <input type="text" name="fname" id="fname" class="span4" placeholder="请输入文件名称">
					  
					</div>
				  </div>
				  <div class="control-group">
		         	 <label for="upload" class="control-label"></label>
		          	 <div class="controls">
		          		<input type="file" id="upload" name="myfile" class="span5">
		         	 </div>
		          </div>
				  <div style="margin-left:250px">
					<input class="btn btn-primary" type="button" id="fsub" value="确定">
					<a href="singlefile.jspx?m=login&fileTypeid=${sessionScope.fileType.id }"><button class="btn">取消</button></a>
				  </div>
				</fieldset>
			</form>
			
			
		</div>
	</div>
				<script type="text/javascript">
			$(document).ready(function(){
				$("#fsub").click(function(){
					if(($("#fname").val() != "") && ($("#myfile").val() != "")){
						$("#myform").submit();
					}
				});
			});
		</script>

</body>
</html>
