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
	<title>添加联系人</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

	<div class="container con" style="padding-bottom:20px">
		
		<div class="content" style="width:870px;">
			<form id="myform" class="form-horizontal" action="contact.jspx?m=addContact" method="post">
				<fieldset>
				  <legend>添加联系人</legend>
				  <div class="control-group" >
					<label for="name" class="control-label" >姓名</label>
					<div class="controls">
					  <input type="text" name="name" id="name" class="span5" placeholder="请输入姓名">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="companyname" class="control-label" >公司</label>
					<div class="controls">
					  <input type="text" name="companyname" id="companyname" class="span5" placeholder="请输入公司名称">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="mobile" class="control-label" >手机</label>
					<div class="controls">
					  <input type="text" name="mobile" id="mobile" class="span5" placeholder="请输入手机号">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="tel" class="control-label" >固话</label>
					<div class="controls">
					  <input type="text" name="tel" id="tel" class="span5" placeholder="请输入固话">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="email" class="control-label" >邮箱</label>
					<div class="controls">
					  <input type="text" name="email" id="email" class="span5" placeholder="请输入邮箱">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="address" class="control-label" >地址</label>
					<div class="controls">
					  <input type="text" name="address" id="address" class="span5" placeholder="请输入地址">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="website" class="control-label" >主页</label>
					<div class="controls">
						<input type="text" name="website" id="website" class="span5" placeholder="请输入个人主页">
						<p class="help-block">www.xxx.com</p>
					</div>
				  </div>
				  <div class="control-group" >
					<label for="weibo" class="control-label" >微博</label>
					<div class="controls">
						<select name="weibo1" id="weibo1" class="span2">
						  	<option value="">--请选择--</option>
							<option value="http://weibo.com/">新浪微博</option>
							<option value="http://t.qq.com/">腾讯微博</option>
							<option value="http://twitter.com/">Twitter</option>
						</select>
					<div class="input-prepend">
		                <span class="add-on">@</span>
		                <input type="text" size="16" name="weibo2" id="weibo2" class="span2" placeholder="请输入微博地址">
		            </div>
					</div>
				  </div>
				  <br>
				  <div class="control-group">
					<label for="content" class="control-label">简介</label>
					<div class="controls">
					  <textarea rows="4" name="content" id="content" class="span5" placeholder="请输入个人简介"></textarea>
					</div>
				  </div>
				  <br/>
				  <div class="div1" style="margin-left:-50px">
					<input  class="btn btn-primary" type="button" id="csub" value="提交">
					<a href="contact.jspx?m=login"><button class="btn">取消</button></a>
				  </div>
				</fieldset>
			</form>
					<script type="text/javascript">
			$(document).ready(function(){
				$("#csub").click(function(){
					if(($("#name").val() != "") 
					 && ($("#companyname").val() != "")
					 && ($("#mobile").val() != "") 
					 && ($("#email").val() != "")
					 && ($("#weibo1").val() != "") 
					 && ($("#weibo2").val() != "")
					 ){
						$("#myform").submit();
					}
				});
			});
		</script>
			
		</div>
	</div>

</body>
</html>
