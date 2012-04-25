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
	<style type="text/css">
		.content{
			width:870px;
			height:620px;
			padding-left:15px;
		}
	</style>
	<title>添加任务</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

	<div class="container con" style="padding-bottom:20px">
		<div class="content">
			<form class="form-horizontal" action="task.jspx?m=editTask" method="post">
				<fieldset>
				  <legend>添加任务</legend>
				  <div class="control-group" >
					<label for="tname" class="control-label" >任务名称</label>
					<div class="controls">
					  <span id="tname" class="span5 uneditable-input">${sessionScope.task.tname}</span>
					</div>
				  </div><br>
				  
				  <div class="control-group">
					<label for="textarea" class="control-label">任务说明</label>
					<div class="controls">
					  <textarea rows="6" name="tdesc" id="textarea" class="span6"><c:out value="${sessionScope.task.tdesc}"></c:out></textarea>
					</div>
				  </div><br>
				  
				  <div class="control-group" >
					<label for="begintime" class="control-label" >开始时间</label>
					<div class="controls">
					  <input type="text" name="begintime" id="begintime" class="span4 focused" value="${sessionScope.task.begintime}">
					  </div>
				  </div> <br>
				  
				  <div class="control-group" >
					<label for="endtime" class="control-label" >结束时间</label>
					<div class="controls">
					  <input type="text" name="endtime" id="endtime" class="span4 focused" value="${sessionScope.task.endtime}">
					  </div>
				  </div> <br>
				  <div class="control-group">
					<label for="level" class="control-label">任务级别</label>
					<div class="controls">
					  <select name="level" id="level">
						<option ${sessionScope.task.level eq '紧急重要'?'selected="selected"':''} value="紧急重要">紧急重要</option>
						<option ${sessionScope.task.level eq '紧急不重要'?'selected="selected"':''} value="紧急不重要">紧急不重要</option>
						<option ${sessionScope.task.level eq '重要不紧急'?'selected="selected"':''} value="重要不紧急">重要不紧急</option>
						<option ${sessionScope.task.level eq '不紧急不重要'?'selected="selected"':''} value="不紧急不重要">不紧急不重要</option>
					  </select>
					</div>
				  </div>
				  
				  <div class="control-group">
					<label for="taskManager" class="control-label">负责人</label>
					<div class="controls">
					   <span id="tname" class="span5 uneditable-input">${sessionScope.task.employee.username}</span>
					</div>
				  </div>
				</fieldset>
				 <div class="div1">
					<button class="btn btn-primary" type="submit">提交</button>
					<a href="task.jspx?m=login&gid=${sessionScope.goal.id}"><button class="btn">取消</button></a>
				 </div>
			</form>
		</div>
	</div>		
	
</body>
</html>