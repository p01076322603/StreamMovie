$(function() {
	
	function loginCheck() {
		
		$(".log_ajax_result").text("");
		if ($.trim($(".login_id").val()) == "") {
			$(".log_ajax_result").text("아이디를 입력해주세요");
			return false;
		}
		if ($.trim($(".login_pwd").val()) == "") {
			$(".log_ajax_result").text("비밀번호를 입력해주세요");
			return false;
		}
		
		var loginId = $(".login_id").val();
		var loginPwd = $(".login_pwd").val();
		$.ajax({
			url: "loginCheck",
			type : "POST",
			data : "loginId=" + loginId + "&loginPwd=" + loginPwd,
			dataType : "json",
			success: function(data) {
				if (data.result == 0) $(".log_ajax_result").text("아이디가 일치하지 않습니다");
				if (data.result == 1) $(".log_ajax_result").text("비밀번호가 일치하지 않습니다");
				if (data.result == 2) {
					 $(document).ajaxStop(function(){
    					window.location.reload();
					 });
			    } 
			},
			fail: function() {
				alert("시스템 에러");
			}
		});
	
	}
	
	$(".modal_submit").click(function() {
		loginCheck();
	});
	
	$(".login_id, .login_pwd").on('keypress',function(e) {
    	if(e.which == 13) {
        	loginCheck();
    	}
	});
	
});

const loginButton = document.querySelector(".js-login");
const mobileLoginButton1 = document.querySelector(".mobile_account_logout1");
const mobileLoginButton2 = document.querySelector(".mobile_account_logout2");
const dimmedBackground = document.querySelector("#modal_dimmed");
const loginModal = document.querySelector("#modal");

function toggleModal() {
	loginModal.classList.toggle("modal_hide");
}

function init() {
	loginButton.addEventListener("click", toggleModal);
	mobileLoginButton1.addEventListener("click", toggleModal);
	mobileLoginButton2.addEventListener("click", toggleModal);
	dimmedBackground.addEventListener("click", toggleModal);
}