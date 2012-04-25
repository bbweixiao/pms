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
	<title>项目管理系统首页</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>
	<div class="container">
		<a href="createproject.jspx"><button class="btn btn-success" style="margin-top:20px">
		新建项目
		</button></a>
		<a href="#myModal02" class="btn btn-success" data-toggle="modal" style="margin-top:20px;margin-left:20px">
		添加成员
		</a>
	</div>
	<div class="container con" style="margin-top:20px">
	<c:if test="${fn:length(sessionScope.epList) == 0 }">
		<div class="content div2" style="width:870px">
			<div class="alert alert-block warn">
				还没有任何项目！您可以<a href="createproject.jspx">新建项目</a>
			</div>
		</div>
	</c:if>
		<c:forEach items="${sessionScope.epList}" var="ep" varStatus="e">
			<div class="content div2" style="width:870px">
				<h3>
					<a href="project.jspx?pid=${ep.projectid}">${ep.project.pname}</a>
					<a title="删除" href="main.jspx?m=deleteProject&pid=${ep.projectid}" class="update">
						<i class="icon-trash"></i>
					</a>
					<a title="编辑" href="editproject.jspx?pid=${ep.projectid}"  class="update">
						<i class="icon-pencil"></i>
					</a>
				</h3>
				<hr />
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;
					${ep.project.pdesc}
				</p><br/>
				<div class="time">
					创建时间：${ep.project.pcreatetime}
				</div>
				<div class="attribute">
					负责人：${ep.employee.username}
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="modal hide fade in" id="myModal02" style="display:none;">
		<form  action="employee.jspx?m=new" id="newEmployeeForm" class="form-horizontal" method="post">
			<div class="modal-header">
				<a data-dismiss="modal" class="close">×</a>
				
					<fieldset>
					<br><br>
					  <div class="control-group">
						<label for="username" class="control-label" >输入用户名</label>
						<div class="controls">
						  <input type="text" id="username" class="span3" name="username">&nbsp;
						  <span id="ms1" style="color:red"></span>
						</div>
					  </div>
					  <div class="control-group">
						<label for="pw1" class="control-label" >请输入密码</label>
						<div class="controls">
						  <input type="password" id="pw1" class="span3" name="pw1">&nbsp;
						  <span id="ms2" style="color:red"></span>
						</div>
					  </div>
					  <div class="control-group">
						<label for="pw2" class="control-label" >请确认密码</label>
						<div class="controls">
						  <input type="password" id="pw2" class="span3" name="pw2">&nbsp;
						  <span id="ms3" style="color:red"></span>
						</div>
					  </div>
					</fieldset>
			</div>
			<div class="modal-footer">
				<input type="button" id="newEmployee" class="btn btn-primary" value="确定"/>
				<a data-dismiss="modal" class="btn" href="">取消</a>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#username").blur(function(){
				var username = $(this).val();
				if(username == ""){
					$("#ms1").text("用户名不能为空");
				}else{
					$.get("employee.jspx",{m:"test",username:username},function(result){
						if(result == "ok"){
							$("#ms1").text("√");
						}else{
							$("#ms1").text("该用户名已存在！");
						}
					});
				}
			});
			$("#pw1").blur(function(){
				var pw1 = $(this).val();
				if(pw1 == ""){
					$("#ms2").text("密码不能为空");
				}else{
					$("#ms2").text("");
				}
			});
			$("#pw2").blur(function(){
				var pw2 = $(this).val();
				if(pw2 == ""){
					$("#ms3").text("密码不能为空");
				}else{
					var pw1 = $("#pw1").val();
					if(pw1 != pw2){
						$("#ms3").text("两次输入密码不一致！");
					}else{
						$("#ms3").text("√");
					}
				}
			});
			$("#newEmployee").click(function(){
				$("#username").blur();
				$("#pw1").blur();
				$("#pw2").blur();
				if(($("#username").val() != "") && ($("#pw1").val() != "") && ($("#pw2").val() != "") && ($("#pw1").val() == $("#pw2").val())){
					$("#newEmployeeForm").submit();
				}
			});
		});
	</script>
	
</body>
</html>