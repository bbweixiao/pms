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
	<title>编辑联系人</title>
</head>
<body>
	<%@ include file="include/top.jsp" %>

	<div class="container con" style="padding-bottom:20px">
		
		<div class="content" style="width:870px;">
			<form class="form-horizontal" action="contact.jspx?m=editContact" method="post">
				<fieldset>
				  <legend>编辑联系人</legend>
				  <div class="control-group" >
					<label for="name" class="control-label" >姓名</label>
					<div class="controls">
					  <span id="name" class="span5 uneditable-input">${sessionScope.contact.name}</span>
					</div>
				  </div>
				  <div class="control-group" >
					<label for="companyname" class="control-label" >公司</label>
					<div class="controls">
					  <input type="text" name="companyname" id="companyname" class="span5 focused" value="${sessionScope.contact.companyname}">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="mobile" class="control-label" >手机</label>
					<div class="controls">
					  <input type="text" name="mobile" id="mobile" class="span5 focused" value="${sessionScope.contact.mobile}">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="tel" class="control-label" >固话</label>
					<div class="controls">
					  <input type="text" name="tel" id="tel" class="span5 focused" value="${sessionScope.contact.tel}">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="email" class="control-label" >邮箱</label>
					<div class="controls">
					  <input type="text" name="email" id="email" class="span5 focused" value="${sessionScope.contact.email}">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="address" class="control-label" >地址</label>
					<div class="controls">
					  <input type="text" name="address" id="address" class="span5 focused" value="${sessionScope.contact.address}">
					  
					</div>
				  </div>
				  <div class="control-group" >
					<label for="website" class="control-label" >主页</label>
					<div class="controls">
						<input type="text" name="website" id="website" class="span5 focused" value="${sessionScope.contact.website}">
						<p class="help-block">www.xxx.com</p>
					</div>
				  </div>
				  <div class="control-group" >
					<label for="weibo1" class="control-label">微博</label>
					${fn:substring(sessionScope.contact.weibo, 7,fn:indexOf(sessionScope.contact.weibo,'@')-1)eq'weibo.com'?'selected="selected"':''}
					<div class="controls">
						<select name="weibo1" id="weibo1" class="span2">
							<option>--请选择--</option>
							<option ${fn:substring(sessionScope.contact.weibo, 7,fn:indexOf(sessionScope.contact.weibo,'@')-1)eq'weibo.com'?'selected="selected"':''} value="http://weibo.com/">新浪微博</option>
							<option ${fn:substring(sessionScope.contact.weibo, 7,fn:indexOf(sessionScope.contact.weibo,'@')-1)eq't.qq.com'?'selected="selected"':''} value="http://t.qq.com/">腾讯微博</option>
							<option ${fn:substring(sessionScope.contact.weibo, 7,fn:indexOf(sessionScope.contact.weibo,'@')-1)eq'twitter.com'?'selected="selected"':''} value="http://twitter.com/">Twitter</option>
						</select>
					<div class="input-prepend">
		                <span class="weibo2">@</span>
		                <input type="text" size="16" id="weibo2" name="weibo2" class="span2 focused" value="${fn:substring(sessionScope.contact.weibo, fn:indexOf(sessionScope.contact.weibo,'@')+1,fn:length(sessionScope.contact.weibo))}">
		            </div>
					</div>
				  </div>
				  <br>
				  <div class="control-group">
					<label for="textarea" class="control-label">简介</label>
					<div class="controls">
					  <textarea rows="4" name="content" id="textarea" class="span5"><c:out value="${sessionScope.contact.content}"></c:out></textarea>
					</div>
				  </div>
				  <br/>
				  <div class="div1" style="margin-left:-100px">
					<button class="btn btn-primary" type="submit">提交</button>
					<a href="contact.jspx?m=login"><button class="btn">取消</button></a>
				  </div>
				</fieldset>
			</form>
			
			
		</div>
	</div>

</body>
</html>
