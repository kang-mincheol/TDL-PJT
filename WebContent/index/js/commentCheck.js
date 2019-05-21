jQuery.noConflict();
function comment(){
	alert("로그인이 필요합니다.")
	location.href="#"
}

function textCheck(){
		if($('#TP_title').val()==""){	
			$("#titletResult").show();
			$("#TP_title").focus();
		}else{
				$("#titletResult").hide();	
		}
		if($('#TP_content').val()==""){	
			$("#contentResult").show();
			$("#TP_content").focus();
		}else{
				$("#contentResult").hide();
			}
		
}  

function master(){
	var id = $('#TU_id').val()
	if(id.substring(0,3)=="tm$"){
		alert("관리자 아이디 일치")
		$("#login-form").attr('action',"/TDL/index/TDL_MASTER/TMLogin.do");
	}else{}
}
