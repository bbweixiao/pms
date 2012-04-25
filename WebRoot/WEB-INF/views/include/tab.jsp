<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="span2">
	
	<div class="tab">
	  <div class="sidebar-nav" style="width:110px;margin:0">
		<ul class="nav nav-list">
		  <li class="nav-header"><h5><a href="main.jspx">凯盛总部项目</a></h5></li>
		  <li class="divider" style="margin-left:-30px;width:140px"></li>
		  <li class="${param.tag == 'new' ? 'active' : ''}"><a href="project.jspx?pid=${sessionScope.project.id}">最新动态</a></li>
		  <li class="${param.tag == 'goal' ? 'active' : '' }"><a href="goal.jspx?m=login">目标</a></li>
		  <li class="${param.tag == 'document' ? 'active' : '' }"><a href="document.jspx?m=login">资料库</a></li>
		  <li class="${param.tag == 'file' ? 'active' : '' }"><a href="filetype.jspx?m=login">文件共享</a></li>
		  <li class="${param.tag == 'contact' ? 'active' : '' }"><a href="contact.jspx?m=login">联系人</a></li>
		  <li class="${param.tag == 'idea' ? 'active' : '' }"><a href="idea.jspx?m=login">想法</a></li>
		  <li class="${param.tag == 'bug' ? 'active' : '' }"><a href="">bug</a></li>
		  <li class="${param.tag == '' ? 'active' : '' }"><a href="">项目设置</a></li>
		</ul>
	  </div>
	</div>
</div>
