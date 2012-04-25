<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="main.jspx">项目管理系统</a>
				<div class="nav-collapse">
					<ul class="nav pull-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									${sessionScope.employee.username }
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a  href="#myModal" data-toggle="modal">修改密码</a>
								</li>
								<li>
									<a href="exit.jspx">安全退出</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
    </div>
 
   
	<div class="modal hide fade in" id="myModal" style="display:none;">
		<form  action="repassword.jspx?m=repassword" id="repwform" class="form-horizontal" method="post">
			<div class="modal-header">
				<a data-dismiss="modal" class="close">×</a>
				
					<fieldset>
					<br><br>
					  <div class="control-group">
						<label for="pwold" class="control-label" >输入原密码</label>
						<div class="controls">
						  <input type="password" id="pwold" class="span3" name="pwold">&nbsp;
						  <span id="msg1" style="color:red"></span>
						</div>
					  </div>
					  <div class="control-group">
						<label for="pwnew1" class="control-label" >输入新密码</label>
						<div class="controls">
						  <input type="password" id="pwnew1" class="span3" name="pwnew1">&nbsp;
						  <span id="msg2" style="color:red"></span>
						</div>
					  </div>
					  <div class="control-group">
						<label for="pwnew2" class="control-label" >确认新密码</label>
						<div class="controls">
						  <input type="password" id="pwnew2" class="span3" name="pwnew2">&nbsp;
						  <span id="msg3" style="color:red"></span>
						</div>
					  </div>
					</fieldset>
			</div>
			<div class="modal-footer">
				<input type="button" id="repw" class="btn btn-primary" value="确定"/>
				<a data-dismiss="modal" class="btn" href="">取消</a>
			</div>
		</form>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#pwold").blur(function(){
					var pwold = $(this).val();
					if(pwold == ""){
						$("#msg1").text("密码不能为空");
					}else{
						$.get("repassword.jspx",{m:"test",pwold:pwold},function(result){
							if(result == "ok"){
								$("#msg1").text("√");
							}else{
								$("#msg1").text("密码输入错误！");
							}
						});
					}
				});
				$("#pwnew1").blur(function(){
					var pwnew1 = $(this).val();
					if(pwnew1 == ""){
						$("#msg2").text("密码不能为空");
					}else{
						$("#msg2").text("");
					}
				});
				$("#pwnew2").blur(function(){
					var pwnew2 = $(this).val();
					if(pwnew2 == ""){
						$("#msg3").text("密码不能为空");
					}else{
						var pwnew1 = $("#pwnew1").val();
						if(pwnew1 != pwnew2){
							$("#msg3").text("两次输入密码不一致！");
						}else{
							$("#msg3").text("√");
						}
					}
				});
				$("#repw").click(function(){
					$("#pwold").blur();
					$("#pwnew1").blur();
					$("#pwnew2").blur();
					if(($("#pwold").val() != "") && ($("#pwnew1").val() != "") && ($("#pwnew2").val() != "") && ($("#pwnew1").val() == $("#pwnew2").val())){
						$("#repwform").submit();
					}
				});
			});
		</script>
	</div>
