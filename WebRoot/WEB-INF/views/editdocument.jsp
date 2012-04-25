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
			<form class="form-horizontal" action="document.jspx?m=edit&id=${document.id}" method="post">
				<fieldset>
				  <legend>分享新资料</legend>
				  <div class="control-group" >
					<label for="title" class="control-label" >标题</label>
					<div class="controls">
					  <input type="text" name="title" id="title" class="span4 focused" value="${document.title }" >
					  
					</div>
				  </div>
				  
				  <br><br>
				  
				  <div class="control-group">
					<label for="textarea" class="control-label">内容</label>
					<div class="controls">
					  <textarea rows="8" name="content" id="textarea" class="span6"><c:out value="${document.content}"></c:out></textarea>
					</div>
				  </div>
				  <br/>
				  <div class="div1">
					<button class="btn btn-primary" type="submit">保存</button>
					<a href="document.jspx?m=login"><button class="btn">返回</button></a>
				  </div>
				</fieldset>
			</form>
			
			
		</div>
	</div>

</body>
</html>
