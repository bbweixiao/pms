<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span2">
	<div  class="tab" style="margin-left:35px">
	  <div class="sidebar-nav" style="width:110px;margin:0">
		<ul class="nav nav-list">
		  <li class="nav-header"><h3>我的任务</h3></li>
		  <li class="divider" style="margin-left:-30px;width:140px"></li>
		  <c:if test="${fn:length(sessionScope.ptaskList) == 0 }">
				<li><a href=""></a></li>
				<li Style="width:100px;height:30px;line-height:30px; ">没有任何任务！</li>
				<li><a href=""></a></li>
			</c:if>
			<c:forEach items="${sessionScope.ptaskList}" var="task" varStatus="t">
				<li Style="width:90px;border-bottom:1px #000 dotted  ;margin-left:0px; ">
					<a href="rate.jspx?m=side&tid=${task.id}&gid=${task.goalid}">
					<c:out value="${t.count}、${task.tname}"></c:out>
					</a>
				</li>
			
			</c:forEach>
		 
		</ul>
	  </div>
	</div>
</div>
