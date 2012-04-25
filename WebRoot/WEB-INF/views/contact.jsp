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
	<title>任务列表</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".contact").click(function(){
				var id = $(this).attr("rel");
				$.get("contact.jspx", {m:"getDesc",id:id},function(result){
					jQuery("#show").text("");
					var cid = result.id;
   					var name = result.name;
   					var companyname = result.companyname;
   					var mobile = result.mobile;
   					var tel = result.tel;
   					var email = result.email;
   					var address = result.address;
   					var website = result.website;
   					var weibo = result.weibo;
   					var content = result.content;
    				$("<h3>").attr("style","color:#f00").text(name).appendTo($("#show"));
    				$("<p class=\"attribute\">" + "&nbsp;&nbsp;&nbsp;&nbsp;" + companyname + "</p>").appendTo($("#show"));
    				$("<div class=\"attribute\">" + "手机&nbsp;&nbsp;" + mobile + "</div>").appendTo($("#show"));
    				$("<div class=\"attribute\">" + "固话&nbsp;&nbsp;" + tel + "</div>").appendTo($("#show"));
    				$("<div class=\"attribute\">" + "邮箱&nbsp;&nbsp;" + email + "</div>").appendTo($("#show"));
    				$("<div class=\"attribute\">" + "地址&nbsp;&nbsp;" + address + "</div>").appendTo($("#show"));
    				$("<div class=\"attribute\">" + "主页&nbsp;&nbsp;" + "<a href=\"" + website +"\">" + website + "</a></div>").appendTo($("#show"));
    				$("<div class=\"attribute\">" + "微博&nbsp;&nbsp;" + "<a href=\"" + weibo.replace("@","") + "\">" + weibo.substring(weibo.indexOf("@")) + "</a></div>").appendTo($("#show"));
    				$("<h4 class=\"attribute\">").text("简介").appendTo($("#show"));
    				$("<p class=\"attribute\">" + "&nbsp;&nbsp;&nbsp;&nbsp;" + content + "</p>").appendTo($("#show"));
    				$("<br/>").appendTo($("#show"));
    				$("<a>").attr("href","contactnote.jspx?m=login&cid=" + cid).text("查看所有记录").appendTo($("#show"));
    				$("<hr/>").appendTo($("#show"));
    				$("<a href=\"editcontact.jspx?m=login&id=" + cid + "\" class=\"btn btn-primary\"><i class=\"icon-pencil icon-white\" style=\"margin-top:3px\"></i>" + "编辑" + "</a>").appendTo($("#show"));
    				$("<a href=\"contact.jspx?m=delete&cid=" + cid + "\" style=\"margin-left:5px\" class=\"btn btn-danger\"><i class=\"icon-trash icon-white\" style=\"margin-top:3px\"></i>" + "删除" + "</a>").appendTo($("#show"));
				});
			});
		});
	</script>
</head>
<body>
	<%@ include file="include/top.jsp" %>

 	<div class="container con">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
				<jsp:include page="include/tab.jsp">
					<jsp:param value="contact" name="tag"/>
				</jsp:include>
				</div>
	   		</div>
			<div class="span9">
				<div class="content div2">
					<h4>
						<i class="icon-user" style="margin:3px"></i>联系人
					</h4>
					<hr>
					<c:choose>
						<c:when test="${fn:length(sessionScope.contactList) == 0}">
							<div class="alert alert-block warn">
								还没有任何联系人！您可以
								<a class="btn btn-success" href="newcontact.jspx">
									<i class="icon-plus"></i>
									添加联系人
								</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="tabbable">
								<div class="row">
									<div class="span3">
										<a class="btn btn-success span2" href="newcontact.jspx">
											<i class="icon-plus"></i>
											添加联系人
										</a>
										<div class="clear"></div>
										<div id="contact">
										<ul style="margin:15px;font-size:14px" class="unstyled">
											<c:forEach items="${sessionScope.contactList}" var="contact" varStatus="c">
												<li>
													<a class="contact" href="javascript:void(0)" rel="${contact.id}"><i class="icon-list-alt" style="margin:4px"></i>${contact.name}</a>
												</li>
											</c:forEach>
										</ul>
										</div>
									</div>
									<div class="span6">
										<div style="border-left:1px solid #fff;padding-left:15px">
											<div id="show" style="font-size:14px">
												<c:forEach items="${sessionScope.contactList}" var="contact" varStatus="c" begin="0" end="0">
													<h3 style="color:#f00"><c:out value="${contact.name}"></c:out> </h3>
													<p class="attribute">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${contact.companyname}"></c:out></p>
													<div class="attribute">手机&nbsp;&nbsp;<c:out value="${contact.mobile}"></c:out></div>
													<div class="attribute">固话&nbsp;&nbsp;<c:out value="${contact.tel}"></c:out></div>
													<div class="attribute">邮箱&nbsp;&nbsp;<c:out value="${contact.email}"></c:out></div>
													<div class="attribute">地址&nbsp;&nbsp;<c:out value="${contact.address}"></c:out></div>
													<div class="attribute">主页&nbsp;&nbsp;<a href="${contact.website}"><c:out value="${contact.website}"></c:out></a></div>
													<div class="attribute">微博&nbsp;&nbsp;<a href="${fn:replace(contact.weibo, '@','')}"><c:out value="${fn:substring(contact.weibo, fn:indexOf(contact.weibo,'@'),fn:length(contact.weibo))}"></c:out></a>
													</div>
													<h4 class="attribute">简介</h4>
													<p class="attribute">
														&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${contact.content}"></c:out>
													</p><br/>
													<a href="contactnote.jspx?m=login&cid=${contact.id}">查看所有记录</a>
													<hr/>
													<a href="editcontact.jspx?id=${contact.id}" class="btn btn-primary"><i class="icon-pencil icon-white" style="margin-top:3px">
														</i>编辑</a><a href="contact.jspx?m=delete&cid=${contact.id}" style="margin-left:5px" class="btn btn-danger">
														<i class="icon-trash icon-white" style="margin-top:3px"></i>删除</a>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
	    </div>
    </div>
</body>
</html>