<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>项目管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<style type="text/css">
		body{
			padding-top:40px;
			background-image:url(img/1.jpg);
			background-position:0px 40px;
		}
		h3{
			font-size:20px;
			color:yellow;
		}
		#myform{
			color:#fff;
			padding-top:120px;
			padding-left:200px;
			margin-top:50px;
			background-color:#106699;
			width:500px;
			height:350px;
			margin-left:auto;
			margin-right:auto;
		}
	</style>
  </head>
  
  <body>
    <div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<div class="brand" style="margin-left:150px">
				项目管理系统
				</div>
			</div>
		</div>
    </div>
	<div class="container con">
		<form id="myform" class="well form-inline" action="index.jspx?m=login" method="post">
		<h3>系统登录</h3>
		<br/>
			<c:if test="${state eq'10001'}" var="res1" scope="page">
				<span style="color:red ;font-size:16px;height:25px;line-height:25px;padding-left:50px;">用户名或密码错误！</span>
			</c:if><br />
			用户名：<input id="username" type="text" name="username" placeholder="请输入用户名"><br /><br />
			密　码：<input id="password" type="password" name="password" placeholder="请输入密码"><br/><br /><br /><br />
			<input type="button" class="btn btn-primary" value="登录系统">
		</form>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".btn").click(function(){
					if(($("#username").val() != "") && ($("#password").val() != "")){
						$("#myform").submit();
					}
				});
			});
		</script>
	</div>
	<div class="navbar navbar-fixed-bottom">
		<div class="navbar-inner" style="height:50px;">
			<div class="container">
				<div class="brand" style="margin-left:600px">
				<span style="line-height:30px">焦作市凯盛信息技术有限公司</span>
				</div>
			</div>
		</div>
    </div>
  </body>
</html>
