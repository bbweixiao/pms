$(doucment).ready(function(){
	$("#pwold").blur(function(){
		var oldpw = $(this).val();
		if(oldPw == ""){
			$("#msg1").text("���벻��Ϊ��");
		}
		$.get("repassword.jspx",{m:"test",pwold:pwold},function(result){
			if(result == "error"){
				$("#msg1").show();
			}else{
				$("#msg1").hide();
			}
		});
	});
});